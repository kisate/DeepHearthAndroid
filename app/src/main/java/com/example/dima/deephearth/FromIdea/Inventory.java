package com.example.dima.deephearth.FromIdea;

import java.util.LinkedList;

/**
 * Created by Dima on 09.05.2017.
 */

public class Inventory extends LinkedList<Item> {

    public Hero hero;
    public Inventory(Hero hero) {
        this.hero = hero;
    }

    @Override
    public boolean add(Item item) {
        item.equip(this);
        return super.add(item);
    }

    @Override
    public boolean remove(Object o) {
        Item item = (Item) o;
        ((Item) o).unEquip(this);
        return super.remove(o);
    }
}
