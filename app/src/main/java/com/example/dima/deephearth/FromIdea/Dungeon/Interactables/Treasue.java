package com.example.dima.deephearth.FromIdea.Dungeon.Interactables;

import com.example.dima.deephearth.DungeonEventHandler;
import com.example.dima.deephearth.FromIdea.Dungeon.Drop;
import com.example.dima.deephearth.FromIdea.Dungeon.Droppable;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.TreasureEvent;
import com.example.dima.deephearth.FromIdea.Dungeon.Interactable;
import com.example.dima.deephearth.FromIdea.Dungeon.InteractableTypes;
import com.example.dima.deephearth.R;

import java.util.LinkedList;

/**
 * Created by Dima on 09.05.2017.
 */

public class Treasue extends Interactable {

    Drop drop = new Drop();

    public Treasue(DungeonEventHandler handler) {
        super(handler);
        type = InteractableTypes.Treasure;
        image = R.drawable.room_treasure;
    }

    public Treasue(Drop drop, DungeonEventHandler handler) {
        this(handler);
        this.drop = drop;
        events.add(new TreasureEvent(drop, handler));
    }
}
