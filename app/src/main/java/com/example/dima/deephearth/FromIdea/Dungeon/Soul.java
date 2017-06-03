package com.example.dima.deephearth.FromIdea.Dungeon;

import java.io.Serializable;

/**
 * Created by Dima on 09.05.2017.
 */

public class Soul implements Droppable, Serializable{
    public SoulSizes size;
    public Soul(SoulSizes size) {
        this.size = size;
    }

    public int getValue() {
        return size.getValue();
    }

    @Override
    public String getName() {
        return size.toString() + " soul";
    }
}
