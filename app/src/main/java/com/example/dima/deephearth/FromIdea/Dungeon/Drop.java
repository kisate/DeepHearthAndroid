package com.example.dima.deephearth.FromIdea.Dungeon;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by Dima on 14.05.2017.
 */

public class Drop extends LinkedList<Droppable> implements Droppable, Serializable {
    @Override
    public String getName() {

        String name = "";

        for (Droppable d :
                this) {
            name+=d.getName();

            if (indexOf(d) < size()-1) name+= ", ";
        }

        return name;
    }
}
