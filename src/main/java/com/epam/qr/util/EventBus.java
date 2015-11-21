package com.epam.qr.util;

import com.epam.qr.interfaces.EventListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmytro_Kovalskyi on 28.10.2015.
 */
public class EventBus {
    private static Map<String, EventListener> listeners = new HashMap<>();

    public static void subscribe(String name, EventListener listener) {
        listeners.put(name, listener);
    }

    public static void unSubscribe(String name) {
        listeners.remove(name);
    }

    public static void onEvent(String data) {
        System.out.println("[EventBus] event retrieved send to " + listeners.size() + " listeners");
        listeners.values().forEach(l -> l.onQrRecognized(data));
    }
}
