package com.surveillance.adapter;

import com.surveillance.implementor.VideoSource;
import com.surveillance.exception.StreamLostException;

// Adapter Role
public class CctvAdapter implements VideoSource {
    private final CctvAnalogReceiver receiver;
    private final int bufferSize;

    public CctvAdapter(CctvAnalogReceiver receiver, int bufferSize) {
        this.receiver = receiver;
        this.bufferSize = bufferSize;
    }

    @Override
    public byte[] fetchFrame() throws StreamLostException {
        CctvStatus status = receiver.readRawBuffer(bufferSize);

        // Перевод несовместимой обработки ошибок в стандартную для системы
        if (status.errorCode == -1) {
            throw new StreamLostException("CCTV Adapter: Analog signal lost (Error Code -1)");
        }

        System.out.println("[Adapter] Translated analog buffer to digital frame.");
        return status.buffer;
    }

    @Override
    public void closeStream() {
        receiver.terminateConnection();
    }
}