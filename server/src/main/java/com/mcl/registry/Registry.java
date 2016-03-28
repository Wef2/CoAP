package com.mcl.registry;

import com.mcl.domain.Node;
import com.mcl.repository.ItemRepository;
import com.mcl.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Kim on 2016-03-28.
 */
public class Registry {

    private static Registry newInstance;
    @Autowired
    NodeRepository nodeRepository;
    @Autowired
    ItemRepository itemRepository;

    private Registry() {
    }

    public static Registry getInstance() {
        if (newInstance == null) {
            newInstance = new Registry();
        }
        return newInstance;
    }

    public String addNode(int id, String model, String name, String address, int port){
        String returnValue;
        if(nodeRepository.findOne(id) == null){
            Node node = new Node(id, model, name, address, port);
            nodeRepository.save(node);
            returnValue = "Registration Success";
        }
        else{
         returnValue = "Already registered";
        }
        return returnValue;
    }
}
