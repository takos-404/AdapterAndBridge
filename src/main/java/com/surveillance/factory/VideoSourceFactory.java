package com.surveillance.factory;

import com.surveillance.implementor.VideoSource;
import com.surveillance.implementor.IpCameraSource;
import com.surveillance.implementor.WebcamSource;
import com.surveillance.adapter.CctvAdapter;
import com.surveillance.adapter.CctvAnalogReceiver;

public class VideoSourceFactory {
    // Выбор имплементации во время выполнения на основе строки
    public static VideoSource createSource(String url) {
        if (url.startsWith("ip://")) {
            return new IpCameraSource();
        } else if (url.startsWith("usb://")) {
            return new WebcamSource();
        } else if (url.startsWith("analog://")) {
            return new CctvAdapter(new CctvAnalogReceiver(), 1024);
        }
        throw new IllegalArgumentException("Unknown protocol in URL: " + url);
    }
}