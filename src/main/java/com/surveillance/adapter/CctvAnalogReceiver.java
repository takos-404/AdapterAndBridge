package com.surveillance.adapter;

// Incompatible Class (Сторонняя библиотека, которую нельзя менять)
// Другое имя метода, другие параметры, возвращает коды ошибок вместо исключений.
public class CctvAnalogReceiver {
    public CctvStatus readRawBuffer(int bufferSize) {
        CctvStatus status = new CctvStatus();
        // В реальности здесь было бы чтение с железа
        status.errorCode = 0;
        status.buffer = new byte[bufferSize];
        return status;
    }

    public void terminateConnection() {
        System.out.println("[CCTV] Analog receiver terminated.");
    }
}