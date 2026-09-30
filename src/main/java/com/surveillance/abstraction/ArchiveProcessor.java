package com.surveillance.abstraction;

import com.surveillance.implementor.VideoSource;
import com.surveillance.exception.StreamLostException;

// Refined Abstraction 2
public class ArchiveProcessor extends StreamProcessor {
    public ArchiveProcessor(VideoSource videoSource) {
        super(videoSource);
    }

    @Override
    public void process() {
        System.out.println("--- Starting Archiving Process ---");
        try {
            byte[] frame = videoSource.fetchFrame();
            System.out.println("Compressing and saving " + frame.length + " bytes to disk.");
        } catch (StreamLostException e) {
            System.out.println("Archive Error: Recording stopped. " + e.getMessage());
        }
    }
}