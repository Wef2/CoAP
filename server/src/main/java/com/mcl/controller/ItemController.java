package com.mcl.controller;

import com.mcl.domain.Item;
import com.mcl.domain.Node;
import com.mcl.repository.ItemRepository;
import com.mcl.repository.NodeRepository;
import javafx.application.Application;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.eclipse.californium.core.coap.MediaTypeRegistry.APPLICATION_JSON;

/**
 * Created by Kim on 2016-02-16.
 */
@RestController
public class ItemController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    NodeRepository nodeRepository;

    @RequestMapping("/node/{nodeId}/items")
    public Iterable<Item> items(@PathVariable int nodeId) {
        return itemRepository.findByNodeId(nodeId);
    }

    @RequestMapping("/node/item/{id}")
    public Item item(@PathVariable String id) {
        return itemRepository.findOne(id);
    }

    @RequestMapping("/node/item/{id}/{operation}")
    public String itemControl(@PathVariable String id, @PathVariable String operation) {
        log.info("Message From Client : " + "Item Id : " + id + ", Operation : " + operation);
        String msg;
        Item item = itemRepository.findOne(id);
        Node node = nodeRepository.findOne(item.getNodeId());
        CoapClient coapClient = new CoapClient("117.17.102.81:" + node.getPort());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("operation", operation);
        coapClient.put(jsonObject.toString(), APPLICATION_JSON);
        coapClient.setTimeout(5000);
        CoapResponse response = coapClient.get();
        if (response != null) {
            msg = response.getResponseText();
            itemRepository.updateStatus(id, operation);
            log.info("IoT Node Status Update : " + itemRepository.findOne(id));
        } else {
            msg = "Fail";
        }
        return msg;
    }
}