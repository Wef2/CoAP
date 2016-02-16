package com.mcl.repository;

import com.mcl.domain.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Kim on 2016-02-16.
 */
public interface ItemRepository extends CrudRepository<Item, String> {

    List<Item> findByNodeId(int nodeId);

    List<Item> findByItemType(String itemType);

    @Query("select i from Item i where i.nodeId = :nodeId AND i.itemType = :itemType")
    List<Item> findItemsByNodeIdAndItemType(@Param("nodeId") int nodeId, @Param("itemType") String itemType);
}