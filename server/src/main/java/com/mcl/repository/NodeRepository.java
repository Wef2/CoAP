package com.mcl.repository;

import com.mcl.domain.Node;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Kim on 2016-02-10.
 */

public interface NodeRepository extends CrudRepository<Node, Integer> {
    Node findByName(String name);
}
