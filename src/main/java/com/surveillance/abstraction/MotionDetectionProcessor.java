package com.surveillance.abstraction;

import com.surveillance.implementor.VideoSource;
import com.surveillance.exception.StreamLostException;

// Refined Abstraction 1
public class MotionDetectionProcessor extends StreamProcessor {
    public MotionDetectionProcessor(VideoSource videoSource) {
        super(videoSource);
    }

    @Override
    public void process() {
        System.out.println("--- Starting Motion Detection ---");
        try {
            byte[] frame = videoSource.fetchFrame();
            System.out.println("Analyzing " + frame.length + " bytes for movement.");
        } catch (StreamLostException e) {
            System.out.println("ALARM! Cannot detect motion: " + e.getMessage());
        }
    }
}