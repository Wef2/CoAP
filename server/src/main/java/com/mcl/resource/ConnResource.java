package com.mcl.resource;

import javafx.application.Application;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Kim on 2016-02-10.
 */
public class ConnResource extends CoapResource {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public ConnResource() {
        super("");
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        log.info("GET");
        log.info("Source Address : " + exchange.getSourceAddress().toString());
        log.info("Source Port : " + exchange.getSourcePort());
        log.info("Payload : " + exchange.getRequestPayload());
        exchange.respond("Hello World!");
    }

    @Override
    public void handlePOST(CoapExchange exchange){
        log.info("POST");
        log.info("Request Code", exchange.getRequestCode());
    }
    @Override
    public void handlePUT(CoapExchange exchange){
        log.info("PUT");
    }
    @Override
    public void handleDELETE(CoapExchange exchange){
        log.info("DELETE");
    }

}
