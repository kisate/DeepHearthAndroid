package com.example.dima.deephearth.FromIdea.Dungeon;

/**
 * Created by Dima on 09.05.2017.
 */

public class Soul implements Droppable{
    public SoulSizes size;
    public Soul(SoulSizes size) {
        this.size = size;
    }

    public int getValue() {
        return size.getValue();
    }
}
