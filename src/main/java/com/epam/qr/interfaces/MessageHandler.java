package com.epam.qr.interfaces;

import com.epam.qr.messages.GenericMessage;

/**
 * Created by Dmytro_Kovalskyi on 18.07.2014.
 */
public interface MessageHandler {
    public void handle(GenericMessage message);
}
