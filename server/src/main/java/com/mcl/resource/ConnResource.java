package com.mcl.resource;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mcl.domain.Node;
import com.mcl.controller.MessageController;
import com.mcl.listener.ResultListener;
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

public class ConnResource extends CoapResource implements ResultListener{

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private JsonParser parser = new JsonParser();

    private MessageController messageController;

    public ConnResource() {
        super("");
    }

    public void setMessageController(MessageController messageController) {
        this.messageController = messageController;
        messageController.addListener(this);
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        log.info("GET");
        exchange.respond(CoAP.ResponseCode.CREATED);
        JsonElement jsonElement = parser.parse(exchange.advanced().getRequest().getPayloadString());
        JsonObject object = jsonElement.getAsJsonObject();
        log.info("ID : " + object.get("id").toString());
        log.info("MODEL : " + object.get("model").toString());
        log.info("NAME : " + object.get("name").toString());
        log.info("PORT : " + object.get("port").toString());
        int id = object.get("id").getAsInt();
        String model = object.get("model").toString();
        String name = object.get("name").toString();
        int port = Integer.parseInt(object.get("port").toString());
        Node node = new Node(id, model, name, "", port);
        messageController.registerNode(node);
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

    @Override
    public void onResult(String msg) {
        log.info(msg);
    }
}