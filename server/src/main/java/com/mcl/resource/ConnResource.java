package com.mcl.resource;

import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mcl.registry.Registry;
import javafx.application.Application;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Kim on 2016-02-10.
 */
public class ConnResource extends CoapResource {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private Registry registry = Registry.getInstance();
    private JsonParser parser = new JsonParser();

    public ConnResource() {
        super("");
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.respond(CoAP.ResponseCode.CREATED);
        log.info("GET");
        JsonElement jsonElement = parser.parse(exchange.advanced().getRequest().getPayloadString());
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        log.info("POST");
        log.info("Request Code", exchange.getRequestCode());
        exchange.respond(CoAP.ResponseCode.CREATED);
        System.out.println(Utils.prettyPrint(exchange.advanced().getRequest()));
        System.out.println(Utils.prettyPrint(exchange.advanced().getResponse()));
    }

    @Override
    public void handlePUT(CoapExchange exchange) {
        log.info("PUT");
    }

    @Override
    public void handleDELETE(CoapExchange exchange) {
        log.info("DELETE");
    }


}
