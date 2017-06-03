package com.example.dima.deephearth.FromIdea.Dungeon.Interactables;

import com.example.dima.deephearth.DungeonEventHandler;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.ExitEvent;
import com.example.dima.deephearth.FromIdea.Dungeon.Interactable;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 27.05.2017.
 */

public class Exit extends Entity {
    public Exit(DungeonEventHandler handler) {
        super(handler);
        image = R.drawable.room_exit;
        events.add(new ExitEvent(handler));
    }
}
