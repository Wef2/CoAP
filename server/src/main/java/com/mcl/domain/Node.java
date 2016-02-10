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
    private String info;

    public Node(int id, String info){
        this.id = id;
        this.info = info;
    }

    @Override
    public String toString(){
        return String.format("Node[id=%d, info='$s']", id, info);
    }
}