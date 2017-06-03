package com.example.dima.deephearth.FromIdea;

import com.example.dima.deephearth.FromIdea.Dungeon.Droppable;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by Dima on 23.02.2017.
 */
public abstract class Item implements Serializable, Droppable{
    public String name, description;
    public LinkedList<Effect> effects = new LinkedList<>();
    public int icoId;
    public Unit owner;

    public Item(){}

    public void equip(Inventory inventory) {

    }

    public void unEquip(Inventory inventory) {

    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
