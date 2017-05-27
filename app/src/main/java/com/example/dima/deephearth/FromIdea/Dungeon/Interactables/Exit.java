package com.example.dima.deephearth.FromIdea.Dungeon.Interactables;

import com.example.dima.deephearth.DungeonEventHandler;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.ExitEvent;
import com.example.dima.deephearth.FromIdea.Dungeon.Interactable;

/**
 * Created by Dima on 27.05.2017.
 */

public class Exit extends Entity {
    public Exit(DungeonEventHandler handler) {
        super(handler);
        events.add(new ExitEvent(handler));
    }
}
