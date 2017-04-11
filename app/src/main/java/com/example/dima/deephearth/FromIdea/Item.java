package com.example.dima.deephearth.FromIdea;

import java.io.Serializable;

/**
 * Created by Dima on 23.02.2017.
 */
public abstract class Item implements Serializable {
    public String name, description;

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
