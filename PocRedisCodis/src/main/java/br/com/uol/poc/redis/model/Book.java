package br.com.uol.poc.redis.model;

import java.io.Serializable;

/**
 * Created by vrx_hora on 20/01/16.
 */
public class Book implements Serializable {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
