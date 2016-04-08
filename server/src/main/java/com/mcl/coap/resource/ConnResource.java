package com.mcl.coap.resource;

import com.mcl.controller.MessageController;
import com.mcl.domain.Item;
import com.mcl.domain.Node;
import com.mcl.listener.ResultListener;
import javafx.application.Application;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Kim on 2016-02-10.
 */

public class ConnResource extends CoapResource implements ResultListener {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

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
        log.info("Message From IoT Node");
        exchange.respond(CoAP.ResponseCode.CREATED);
        JSONObject jsonObject = new JSONObject(exchange.advanced().getRequest().getPayloadString());
        log.info("Information : " + jsonObject.toString());
        int id = jsonObject.getInt("id");
        String model = jsonObject.getString("model");
        String name = jsonObject.getString("name");
        int port = jsonObject.getInt("port");
        Node node = new Node(id, model, name, "", port);

        JSONObject itemObject = jsonObject.getJSONObject("item");
        String itemId = itemObject.getString("id");
        String type = itemObject.getString("type");
        String status = itemObject.getString("status");

        Item item = new Item(itemId, id, type, status);

        messageController.registerNode(node);
        messageController.registerItem(item);
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