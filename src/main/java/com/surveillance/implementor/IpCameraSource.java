package com.surveillance.implementor;

import com.surveillance.exception.StreamLostException;

public class IpCameraSource implements VideoSource {
    @Override
    public byte[] fetchFrame() throws StreamLostException {
        System.out.println("[IP Camera] Fetching HD frame...");
        return new byte[]{1, 1, 1, 1}; // Имитация кадра
    }

    @Override
    public void closeStream() {
        System.out.println("[IP Camera] Connection closed.");
    }
}