package com.hard.rtsp.server;

import java.io.FileInputStream;

public class VideoStream {
    private FileInputStream fileInputStream; //video file
    private int frameNumber; // current frame nb

    public VideoStream(String fileName) throws Exception {
        // init variables
        fileInputStream = new FileInputStream(fileName);
        frameNumber = 0;
    }

    /**
     * returns the next frame as an array of byte and the size of the frame
     *
     * @param frame
     * @return
     * @throws Exception
     */
    public int getNextFrame(byte[] frame) throws Exception {
        byte[] frameLength = new byte[5];

        // read current frame length
        fileInputStream.read(frameLength, 0, frameLength.length);

        // transform frame_length to integer
        String lengthStr = new String(frameLength);
        int length = Integer.parseInt(lengthStr);

        return fileInputStream.read(frame, 0, length);
    }
}