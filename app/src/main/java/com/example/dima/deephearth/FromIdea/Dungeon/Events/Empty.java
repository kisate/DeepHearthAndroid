package com.example.dima.deephearth.FromIdea.Dungeon.Events;

import com.example.dima.deephearth.DungeonEventHandler;
import com.example.dima.deephearth.FromIdea.Dungeon.Event;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.Choices.LeaveChoice;

/**
 * Created by Dima on 14.05.2017.
 */

public class Empty extends Event {

    public Empty(DungeonEventHandler handler, String name, String description) {
        super(handler, name, description);
    }

    public Empty(DungeonEventHandler handler) {
        this(handler, "Empty", "Nothing special here");
        choices.add(new LeaveChoice(handler));
    }
}
