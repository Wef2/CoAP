package com.mcl.controller;

import com.mcl.domain.Item;
import com.mcl.domain.Node;
import com.mcl.listener.ResultListener;
import com.mcl.repository.ItemRepository;
import com.mcl.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by Kim on 2016-04-02.
 */
@Component
public class MessageController {

    @Autowired
    NodeRepository nodeRepository;
    @Autowired
    ItemRepository itemRepository;

    private ArrayList<ResultListener> listeners = new ArrayList<>();

    public void addListener(ResultListener listener) {
        listeners.add(listener);
    }

    public void registerNode(Node node) {
        String msg;
        if (!nodeRepository.exists(node.getId())) {
            nodeRepository.save(node);
            msg = "Register Success";
        } else {
            msg = "Already Registered";
        }
        notifyResult(msg + " : " + node.toString());
    }

    public void registerItem(Item item) {
        String msg;
        if (!itemRepository.exists(item.getId())) {
            itemRepository.save(item);
            msg = "Register Success";
        } else {
            msg = "Already Registered";
        }
        notifyResult(msg + " : " + item.toString());
    }

    public void notifyResult(String msg) {
        for (ResultListener listener : listeners) {
            listener.onResult(msg);
        }
    }
}