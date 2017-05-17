package com.example.dima.deephearth.FromIdea.Dungeon.Events.ChoiceEvents;

import com.example.dima.deephearth.DungeonEventHandler;
import com.example.dima.deephearth.FromIdea.Dungeon.Event;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.EventTypes;

/**
 * Created by Dima on 14.05.2017.
 */

public class LeaveEvent extends Event {
    public LeaveEvent(DungeonEventHandler handler, String name, String description) {
        super(handler, name, description);
    }

    public LeaveEvent(DungeonEventHandler handler) {
        super(handler, "You have left the room", "");
        type = EventTypes.leave;
    }
}
