package com.surveillance.adapter;

// Структура ответа несовместимой системы (вместо Exception)
public class CctvStatus {
    public byte[] buffer;
    public int errorCode; // 0 = OK, -1 = NO_SIGNAL
}