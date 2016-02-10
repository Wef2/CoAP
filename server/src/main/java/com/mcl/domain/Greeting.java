package com.mcl.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Kim on 2016-02-10.
 */
@Entity
public class Greeting {

    @Id
    private final int id;
    private final String content;

    public Greeting(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}