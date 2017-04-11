package com.example.dima.deephearth.FromIdea.HeroParams;

import java.io.Serializable;

/**
 * Created by Dima on 23.02.2017.
 */
public abstract class Feature implements Serializable {
    public String name, description;

    @Override
    public String toString() {
        return "Feature{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Feature(String name, String description) {
        this.name = name;
        this.description = description;

    }
}
