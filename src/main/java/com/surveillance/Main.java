package com.surveillance;

import com.surveillance.abstraction.StreamProcessor;
import com.surveillance.abstraction.MotionDetectionProcessor;
import com.surveillance.factory.VideoSourceFactory;
import com.surveillance.implementor.VideoSource;

public class Main {
    public static void main(String[] args) {
        // Динамический ввод (например, из файла конфигурации)
        String[] configUrls = {"ip://192.168.1.10", "analog://channel_1"};

        for (String url : configUrls) {
            // Динамический выбор реализации (Dynamic implementor selection)
            VideoSource source = VideoSourceFactory.createSource(url);

            // Использование абстракции
            StreamProcessor processor = new MotionDetectionProcessor(source);
            processor.process();
            System.out.println();
        }
    }
}