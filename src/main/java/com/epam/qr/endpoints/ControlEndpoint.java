package com.epam.qr.endpoints;

import com.epam.qr.common.MessageTypes;
import com.epam.qr.helpers.JsonHelper;
import com.epam.qr.helpers.ResponseHelper;
import com.epam.qr.interfaces.EventListener;
import com.epam.qr.messages.responces.GenericResponse;
import com.epam.qr.util.EventBus;
import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/control")
public class ControlEndpoint extends ResponseEndpoint {
    public static final String WEB_LISTENER = "WebListener";
    private EventListener eventListener = new QrListener();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("[ControlEndpoint] Connection opened : " + session.getId());
        this.session = session;
        EventBus.subscribe(WEB_LISTENER, eventListener);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("[ControlEndpoint] Connection closed : " + session.getId());
        this.session = null;
        EventBus.unSubscribe(WEB_LISTENER);
    }

    @OnError
    public void onError(Throwable error) {
        this.session = null;
        deviceId = null;
        System.err.println("[ControlEndpoint] Error with : " + error.getMessage());
        EventBus.unSubscribe(WEB_LISTENER);
    }

    private class QrListener implements EventListener {

        @Override
        public void onQrRecognized(String message) {
            System.out.println("[QrListener] onQrRecognized : " + message);
            if(session != null) {
                try {
                    GenericResponse response = ResponseHelper.createResponse(MessageTypes.QR_RECOGNIZED, message);
                    session.getBasicRemote().getSendWriter().write(JsonHelper.toJson(response));
                } catch(IOException e) {
                    System.err.println("[QrListener] error: " + e.getMessage());
                }
            } else {
                System.err.println("[QrListener] session is null");
            }
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        try {


        } catch(Exception e) {
            System.err.println("[ControlEndpoint] error : " + e.getMessage());
            sendResponse(e);
        }
    }
}
