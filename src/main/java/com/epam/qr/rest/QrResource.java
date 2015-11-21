package com.epam.qr.rest;

import com.epam.qr.util.EventBus;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Created by Dmytro_Kovalskyi on 28.10.2015.
 */
@Path("/qr")
public class QrResource {

    @POST
    public void post(String payload) {
        System.out.println("QR code read : " + payload);
        EventBus.onEvent(payload);
    }

    @GET
    public void get(@QueryParam("data") String payload) {
        System.out.println("QR code read : " + payload);
        EventBus.onEvent(payload);
    }

    @POST
    @Path("/start")
    public void onStart(@QueryParam("data") String payload) {
        System.out.println("QR code read : " + payload);
        EventBus.onEvent(payload);
    }

}
