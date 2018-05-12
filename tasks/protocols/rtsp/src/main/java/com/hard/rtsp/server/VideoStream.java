package com.hard.rtsp.server;

import java.io.FileInputStream;

public class VideoStream {
    private FileInputStream fileInputStream; //video file
    private int frame_nb; //current frame nb

    public VideoStream(String fileName) throws Exception {
        //init variables
        fileInputStream = new FileInputStream(fileName);
        frame_nb = 0;
    }

    /**
     * returns the next frame as an array of byte and the size of the frame
     *
     * @param frame
     * @return
     * @throws Exception
     */
    public int getNextFrame(byte[] frame) throws Exception {
        int length = 0;
        String length_string;
        byte[] frame_length = new byte[5];

        //read current frame length
        fileInputStream.read(frame_length, 0, 5);

        //transform frame_length to integer
        length_string = new String(frame_length);
        length = Integer.parseInt(length_string);

        return fileInputStream.read(frame, 0, length);
    }
}