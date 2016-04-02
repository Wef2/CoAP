package com.mcl.controller;

import com.mcl.domain.Node;
import com.mcl.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kim on 2016-02-10.
 */

@RestController
public class NodeController {

    @Autowired
    NodeRepository nodeRepository;

    @RequestMapping("/node/list")
    public Iterable<Node> nodeList() {
        return nodeRepository.findAll();
    }

    @RequestMapping("/node/{id}")
    public Node node(@PathVariable int id) {
        return nodeRepository.findOne(id);
    }

    @RequestMapping("/node/save/{node}")
    public Node save(@PathVariable Node node) {
        return nodeRepository.save(node);
    }
}
