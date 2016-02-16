package com.mcl.controller;

import com.mcl.domain.Item;
import com.mcl.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kim on 2016-02-16.
 */
@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @RequestMapping("/node/{nodeId}/item_list")
    public Iterable<Item> itemList(@PathVariable int nodeId) {
        return itemRepository.findByNodeId(nodeId);
    }

}
