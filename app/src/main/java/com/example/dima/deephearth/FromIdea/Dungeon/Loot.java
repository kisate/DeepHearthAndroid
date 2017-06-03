package com.example.dima.deephearth.FromIdea.Dungeon;

import android.util.Pair;

import com.example.dima.deephearth.FromIdea.Dungeon.DungeonDatas.MyPair;
import com.example.dima.deephearth.FromIdea.Dungeon.DungeonDatas.MyPairRandomSelector;
import com.example.dima.deephearth.RandomSelector;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by Dima on 03.06.2017.
 */

public class Loot implements Serializable{
    public LinkedList<MyPair<LinkedList<MyPair<Droppable, Integer>>, Float>> dropList = new LinkedList<>();
    public Drop loot() {
        Drop drop = new Drop();
        MyPairRandomSelector<Droppable> selector;

        for (MyPair<LinkedList<MyPair<Droppable, Integer>>, Float> pair :
                dropList) {
            if (pair.second > Math.random()) {
                selector = new MyPairRandomSelector<>(pair.first);
                drop.add(selector.getRandomElement());
            }
        }
        return drop;
    }
}
