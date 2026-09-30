package com.surveillance.exception;

// Ошибки, с которыми работает наш Bridge (Абстракции знают только о ней)
public class StreamLostException extends Exception {
    public StreamLostException(String message) {
        super(message);
    }
}