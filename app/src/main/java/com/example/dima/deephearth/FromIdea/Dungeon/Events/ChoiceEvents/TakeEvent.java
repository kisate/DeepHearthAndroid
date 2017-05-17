package com.example.dima.deephearth.FromIdea.Dungeon.Events.ChoiceEvents;

import com.example.dima.deephearth.DungeonEventHandler;
import com.example.dima.deephearth.FromIdea.Dungeon.Drop;
import com.example.dima.deephearth.FromIdea.Dungeon.Event;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.EventTypes;

/**
 * Created by Dima on 14.05.2017.
 */

public class TakeEvent extends Event {
    public Drop drop;
    public TakeEvent(DungeonEventHandler handler, String name, String description) {
        super(handler, name, description);
    }

    public TakeEvent(DungeonEventHandler handler, Drop drop) {
        super(handler, "Taken all", "You have taken all the loot");
        this.drop = drop;
        type = EventTypes.takeDrop;
    }
}
