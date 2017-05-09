package com.example.dima.deephearth;

import com.example.dima.deephearth.FromIdea.Dungeon.Event;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.EnemyEvent;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.EntityEvent;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.TreasureEvent;

import java.util.LinkedList;

/**
 * Created by Dima on 09.05.2017.
 */

public class DungeonEventHandler {
    DungeonActivity activity;

    public DungeonEventHandler(DungeonActivity activity) {
        this.activity = activity;
    }

    public void handle(LinkedList<Event> events) {
        for (Event e :
                events) {
            if (e.getClass() == EnemyEvent.class) {
                EnemyEvent ee = (EnemyEvent) e;
                activity.launchBattle(ee.enemy);
            }

            else if (e.getClass() == TreasureEvent.class) {
                TreasureEvent te = (TreasureEvent) e;
            }

            else if (e.getClass() == EntityEvent.class) {
                EntityEvent ee = (EntityEvent) e;
            }
        }
    }
}
