package com.mcl.controller;

import com.mcl.domain.Node;
import com.mcl.listener.ResultListener;
import com.mcl.repository.ItemRepository;
import com.mcl.repository.NodeRepository;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by Kim on 2016-04-02.
 */
@Component
public class MessageController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    NodeRepository nodeRepository;
    @Autowired
    ItemRepository itemRepository;

    private ArrayList<ResultListener> listeners = new ArrayList<>();

    public NodeRepository getNodeRepository() {
        return nodeRepository;
    }

    public ItemRepository getItemRepository() {
        return itemRepository;
    }

    public void addListener(ResultListener listener) {
        listeners.add(listener);
    }

    public void registerNode(Node node) {
        String msg;
        if (nodeRepository.findByName(node.getName()) == null) {
            nodeRepository.save(node);
            msg = "Success";
        } else {
            msg = "Already Registered";
        }
        notifyResult(node.toString() + " " + msg);
    }

    public void notifyResult(String msg) {
        for (ResultListener listener : listeners) {
            listener.onResult(msg);
        }
    }
}