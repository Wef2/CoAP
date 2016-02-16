package com.mcl.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Kim on 2016-02-16.
 */
@Entity
public class Item {

    @Id
    private String id;
    private int nodeId;
    private String itemType;
    private String status;

    public Item(){

    }

    public Item(String id, int nodeId, String itemType, String status) {
        this.id = id;
        this.nodeId = nodeId;
        this.itemType = itemType;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public int getNodeId() {
        return nodeId;
    }

    public String getItemType() {
        return itemType;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", nodeId=" + nodeId +
                ", itemType='" + itemType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}