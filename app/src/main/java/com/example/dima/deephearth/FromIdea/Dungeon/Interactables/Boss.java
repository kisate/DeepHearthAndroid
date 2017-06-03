package com.example.dima.deephearth.FromIdea.Dungeon.Interactables;

import com.example.dima.deephearth.DungeonEventHandler;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.BossEvent;
import com.example.dima.deephearth.FromIdea.Dungeon.Interactable;
import com.example.dima.deephearth.FromIdea.Dungeon.InteractableTypes;
import com.example.dima.deephearth.FromIdea.Player;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 29.05.2017.
 */

public class Boss extends Interactable {

    public Player enemy;

    public Boss(DungeonEventHandler handler) {
        super(handler);
        type = InteractableTypes.Boss;
        image = R.drawable.room_enemy;
    }

    public Boss (DungeonEventHandler handler, Player enemy) {
        this(handler);
        this.enemy = enemy;
        events.add(new BossEvent(handler,enemy));
    }
}
