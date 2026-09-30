package com.surveillance.abstraction;

import com.surveillance.implementor.VideoSource;

// Abstraction Role
public abstract class StreamProcessor {
    protected VideoSource videoSource; // Bridge link

    public StreamProcessor(VideoSource videoSource) {
        this.videoSource = videoSource;
    }

    public abstract void process();
}