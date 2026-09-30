package com.surveillance.implementor;

import com.surveillance.exception.StreamLostException;

public class WebcamSource implements VideoSource {
    @Override
    public byte[] fetchFrame() throws StreamLostException {
        System.out.println("[Webcam] Fetching standard frame...");
        return new byte[]{0, 1, 0, 1};
    }

    @Override
    public void closeStream() {
        System.out.println("[Webcam] Stream stopped.");
    }
}