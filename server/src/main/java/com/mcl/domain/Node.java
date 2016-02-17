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

    public Node(int id, String model, String name){
        this.id = id;
        this.model = model;
        this.name = name;
    }

    public Node(){

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

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}