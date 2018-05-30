package com.hard.rtsp.server;

import com.hard.rtsp.RtpPacket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.StringTokenizer;

public class Server extends JFrame implements ActionListener {
    /**
     * RTP variables
     */

    private DatagramSocket rtpSocket; //socket to be used to send and receive UDP packets
    private DatagramPacket senddp; //UDP packet containing the video frames

    private InetAddress clientIpAddress; //Client IP address
    private int rtpDestinationPort = 0; //destination port for RTP packets  (given by the RTSP Client)

    /**
     * GUI
     */

    private JLabel label;

    /**
     * Video variables
     */

    private int imageNb = 0; //image nb of the image currently transmitted
    private VideoStream video; //VideoStream object used to access video frames
    private static int MJPEG_TYPE = 26; //RTP payload type for MJPEG video
    private static int FRAME_PERIOD = 100; //Frame period of the video to stream, in ms
    private static int VIDEO_LENGTH = 500; //length of the video in frames

    private Timer timer; //timer used to send the images at the video frame rate
    private byte[] buffer; //buffer used to store the images to send to the client

    /**
     * RTSP variables
     */

    //rtsp states
    private static final int INIT = 0;
    private static final int READY = 1;
    private static final int PLAYING = 2;

    //rtsp message types
    private static final int SETUP = 3;
    private static final int PLAY = 4;
    private static final int PAUSE = 5;
    private static final int TEARDOWN = 6;

    private static int state; //RTSP Server state == INIT or READY or PLAY
    private Socket rtspSocket; //socket used to send/receive RTSP messages

    //input and output stream filters
    private static BufferedReader bufferedReader;   // rtsp BufferedReader
    private static BufferedWriter bufferedWriter;   // rtsp BufferedWriter
    private static String videoFileName; //video file requested from the client
    private static int RTSP_ID = 123456; //ID of the RTSP session
    private int rtspSequenceNumber = 0; //Sequence number of RTSP messages within the session

    private static final String CRLF = "\r\n";

    public Server() {
        //init Frame
        super("Server");

        //init Timer
        timer = new Timer(FRAME_PERIOD, this);
        timer.setInitialDelay(0);
        timer.setCoalesce(true);

        //allocate memory for the sending buffer
        buffer = new byte[15000];

        //Handler to close the main window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //stop the timer and exit
                timer.stop();
                System.exit(0);
            }
        });

        /**
         * GUI
         */

        label = new JLabel("Send frame #        ", JLabel.CENTER);
        getContentPane().add(label, BorderLayout.CENTER);
    }

    public static void main(String[] args) throws Exception {
        //create a Server object
        Server server = new Server();

        //show GUI:
        server.pack();
        server.setVisible(true);

        //get RTSP socket port from the command line
        int rtspPort = 8554;// Integer.parseInt(argv[0]);

        //Initiate TCP connection with the client for the RTSP session
        ServerSocket serverSocket = new ServerSocket(rtspPort);
        server.rtspSocket = serverSocket.accept();
        serverSocket.close();

        //Get Client IP address
        server.clientIpAddress = server.rtspSocket.getInetAddress();

        //Initiate RTSPstate
        state = INIT;

        //Set input and output stream filters:
        bufferedReader = new BufferedReader(new InputStreamReader(server.rtspSocket.getInputStream()));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(server.rtspSocket.getOutputStream()));

        //Wait for the SETUP message from the client
        int request_type;
        boolean done = false;
        while (!done) {
            request_type = server.parse_RTSP_request(); //blocking

            if (request_type == SETUP) {
                done = true;

                //update RTSP state
                state = READY;
                System.out.println("New RTSP state: READY");

                //Send response
                server.send_RTSP_response();

                //init the VideoStream object:
                server.video = new VideoStream(videoFileName);

                //init RTP socket
                server.rtpSocket = new DatagramSocket();
            }
        }

        //loop to handle RTSP requests
        while (true) {
            //parse the request
            request_type = server.parse_RTSP_request(); //blocking

            if ((request_type == PLAY) && (state == READY)) {
                //send back response
                server.send_RTSP_response();
                //start timer
                server.timer.start();
                //update state
                state = PLAYING;
                System.out.println("New RTSP state: PLAYING");
            } else if ((request_type == PAUSE) && (state == PLAYING)) {
                //send back response
                server.send_RTSP_response();
                //stop timer
                server.timer.stop();
                //update state
                state = READY;
                System.out.println("New RTSP state: READY");
            } else if (request_type == TEARDOWN) {
                //send back response
                server.send_RTSP_response();
                //stop timer
                server.timer.stop();
                //close sockets
                server.rtspSocket.close();
                server.rtpSocket.close();

                System.exit(0);
            }
        }
    }

    /**
     * Handler for timer
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //if the current image nb is less than the length of the video
        if (imageNb < VIDEO_LENGTH) {
            //update current imageNb
            imageNb++;

            try {
                //get next frame to send from the video, as well as its size
                int image_length = video.getNextFrame(buffer);
                System.out.print("\n" + image_length);
                //Builds an RtpPacket object containing the frame
                RtpPacket rtpPacket = new RtpPacket(MJPEG_TYPE, imageNb, imageNb * FRAME_PERIOD, buffer, image_length);

                //get to total length of the full rtp packet to send
                int packetLength = rtpPacket.getlength();

                //retrieve the packet bitstream and store it in an array of bytes
                byte[] packet_bits = new byte[packetLength];
                rtpPacket.getPacket(packet_bits);

                //send the packet as a DatagramPacket over the UDP socket
                senddp = new DatagramPacket(packet_bits, packetLength, clientIpAddress, rtpDestinationPort);
                System.out.print("rtpDestinationPort =" + rtpDestinationPort + "\n");

                rtpSocket.send(senddp);

                //System.out.println("Send frame #"+imagenb);
                //print the header bitstream
                rtpPacket.printHeader();

                //update GUI
                label.setText("Send frame #" + imageNb);
            } catch (Exception e) {
                System.out.println("Exception caught: " + e);
                System.exit(0);
            }
        } else {
            //if we have reached the end of the video file, stop the timer
            timer.stop();
        }
    }

    /**
     * Parse RTSP Request
     */
    private int parse_RTSP_request() {
        int request_type = -1;
        try {
            //parse request line and extract the request_type:
            String RequestLine = bufferedReader.readLine();
            //System.out.println("RTSP Server - Received from Client:");
            System.out.println(RequestLine);

            StringTokenizer tokens = new StringTokenizer(RequestLine);
            String request_type_string = tokens.nextToken();

            //convert to request_type structure:
            if ((new String(request_type_string)).compareTo("SETUP") == 0)
                request_type = SETUP;
            else if ((new String(request_type_string)).compareTo("PLAY") == 0)
                request_type = PLAY;
            else if ((new String(request_type_string)).compareTo("PAUSE") == 0)
                request_type = PAUSE;
            else if ((new String(request_type_string)).compareTo("TEARDOWN") == 0)
                request_type = TEARDOWN;

            if (request_type == SETUP) {
                //extract videoFileName from RequestLine
                videoFileName = tokens.nextToken();
            }

            //parse the SeqNumLine and extract CSeq field
            String SeqNumLine = bufferedReader.readLine();
            System.out.println(SeqNumLine);
            tokens = new StringTokenizer(SeqNumLine);
            tokens.nextToken();
            rtspSequenceNumber = Integer.parseInt(tokens.nextToken());

            //get LastLine
            String LastLine = bufferedReader.readLine();
            System.out.println(LastLine);

            if (request_type == SETUP) {
                //extract rtpDestinationPort from LastLine
                tokens = new StringTokenizer(LastLine);
                for (int i = 0; i < 3; i++)
                    tokens.nextToken(); //skip unused stuff
                rtpDestinationPort = Integer.parseInt(tokens.nextToken());
            }
            //else LastLine will be the SessionId line ... do not check for now.
        } catch (Exception ex) {
            System.out.println("Exception caught: " + ex);
            System.exit(0);
        }

        return (request_type);
    }

    /**
     * Send RTSP Response
     */
    private void send_RTSP_response() {
        try {
            bufferedWriter.write("RTSP/1.0 200 OK" + CRLF);
            bufferedWriter.write("CSeq: " + rtspSequenceNumber + CRLF);
            bufferedWriter.write("Session: " + RTSP_ID + CRLF);
            bufferedWriter.flush();
            //System.out.println("RTSP Server - Sent response to Client.");
        } catch (Exception ex) {
            System.out.println("Exception caught: " + ex);
            System.exit(0);
        }
    }
}

	
	