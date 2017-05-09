package com.example.dima.deephearth.FromIdea.Dungeon.Interactables;

import com.example.dima.deephearth.FromIdea.Dungeon.Events.EnemyEvent;
import com.example.dima.deephearth.FromIdea.Dungeon.Interactable;
import com.example.dima.deephearth.FromIdea.Dungeon.InteractableTypes;
import com.example.dima.deephearth.FromIdea.Player;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 09.05.2017.
 */

public class Enemy extends Interactable {
    public Player enemy;

    public Enemy() {
        type = InteractableTypes.Enemy;
        image = R.drawable.room_enemy;
    }

    public Enemy(Player enemy) {
        this();
        this.enemy = enemy;
        events.add(new EnemyEvent(enemy));
    }

}
