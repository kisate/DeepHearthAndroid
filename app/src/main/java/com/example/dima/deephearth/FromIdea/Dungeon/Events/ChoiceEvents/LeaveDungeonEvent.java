package com.example.dima.deephearth.FromIdea.Dungeon.Events.ChoiceEvents;

import com.example.dima.deephearth.DungeonEventHandler;
import com.example.dima.deephearth.FromIdea.Dungeon.Event;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.EventTypes;

/**
 * Created by Dima on 27.05.2017.
 */

public class LeaveDungeonEvent extends Event {
    public LeaveDungeonEvent(DungeonEventHandler handler, String name, String description) {
        super(handler, name, description);
    }

    public LeaveDungeonEvent(DungeonEventHandler handler) {
        super(handler, "Leave", "Leave");
        type = EventTypes.leaveDungeon;
    }
}
