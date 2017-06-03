package com.example.dima.deephearth.FromIdea;

import android.util.Log;
import android.widget.Toast;

import com.example.dima.deephearth.FromIdea.Dungeon.Drop;
import com.example.dima.deephearth.FromIdea.Dungeon.Droppable;
import com.example.dima.deephearth.FromIdea.Dungeon.Loot;
import com.example.dima.deephearth.FromIdea.Dungeon.Soul;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by Dima on 23.02.2017.
 */
public class Player implements Serializable {
    public Intellect intellect;
    public int mp = 90, maxMp = 90;
    public int souls = 0;
    public double lastMpCost = 10;
    public double uncoverChance = 0.5;
    public LinkedList<PlayerSkill> skills = new LinkedList<>();
    public LinkedList<Item> items = new LinkedList<>();
    public Loot loot = new Loot();


    public Player(Intellect intellect) {
        this.intellect = intellect;
    }

    public Team team = new Team(4);
    public Team deadUnits = new Team(4);

    public void collect(Drop drop) {
        for (Droppable d :
                drop) {
            if (d.getClass() == Soul.class) souls+= ((Soul) d).getValue();
            else if (d instanceof Item) {items.add((Item) d); Log.d("Debug", "Item");};
        }
    }
}
