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
    private String type;
    private String status;

    public Item(){

    }

    public Item(String id, int nodeId, String type, String status) {
        this.id = id;
        this.nodeId = nodeId;
        this.type = type;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public int getNodeId() {
        return nodeId;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", nodeId=" + nodeId +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}