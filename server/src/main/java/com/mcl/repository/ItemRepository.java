package com.mcl.repository;

import com.mcl.domain.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Kim on 2016-02-16.
 */
public interface ItemRepository extends CrudRepository<Item, String> {

    List<Item> findByNodeId(int nodeId);

    List<Item> findByType(String type);
}