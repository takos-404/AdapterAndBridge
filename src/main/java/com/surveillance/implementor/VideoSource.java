package com.surveillance.implementor;

import com.surveillance.exception.StreamLostException;

// Implementor Role
public interface VideoSource {
    byte[] fetchFrame() throws StreamLostException;
    void closeStream();
}