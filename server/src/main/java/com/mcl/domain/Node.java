package com.mcl.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Kim on 2016-02-10.
 */
@Entity
public class Node {

    @Id
    private int id;
    private String model;
    private String name;
    private String address;
    private int port;

    public Node(int id, String model, String name, String address, int port) {
        this.id = id;
        this.model = model;
        this.name = name;
        this.address = address;
        this.port = port;
    }

    public Node() {

    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }


    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}