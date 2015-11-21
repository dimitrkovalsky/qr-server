package com.epam.qr.messages;

import com.epam.qr.common.MessageTypes;

/**
 * Created by Dmytro_Kovalskyi on 28.10.2015.
 */
public class QRRecognizedMessage extends GenericMessage {
    private int keyCode;

    public QRRecognizedMessage() {
        super(MessageTypes.QR_RECOGNIZED);
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    @Override
    public String toString() {
        return "QRRecognizedMessage{" +
            "keyCode=" + keyCode +
            '}';
    }
}