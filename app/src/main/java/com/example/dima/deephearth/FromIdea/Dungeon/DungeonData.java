package com.example.dima.deephearth.FromIdea.Dungeon;

import android.util.Pair;

import com.example.dima.deephearth.FromIdea.Dungeon.Interactables.Boss;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by Dima on 29.05.2017.
 */

public class DungeonData implements Serializable{
    public LinkedList<Pair<Interactable, Integer>> interactables;
    public int size;
    public Boss boss;

    public DungeonData() {
    }

    public DungeonData(LinkedList<Pair<Interactable, Integer>> interactables) {
        this.interactables = interactables;
    }
}
