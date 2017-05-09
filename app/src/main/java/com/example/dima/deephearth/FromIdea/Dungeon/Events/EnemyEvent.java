package com.example.dima.deephearth.FromIdea.Dungeon.Events;

import com.example.dima.deephearth.FromIdea.Dungeon.Event;
import com.example.dima.deephearth.FromIdea.Player;

/**
 * Created by Dima on 09.05.2017.
 */

public class EnemyEvent extends Event {
    public Player enemy;
    public EnemyEvent(String name, String description) {
        super(name, description);
    }

    public EnemyEvent(Player enemy) {
        super("Battle", "You have encountered an enemy");
        this.enemy = enemy;
    }
}
