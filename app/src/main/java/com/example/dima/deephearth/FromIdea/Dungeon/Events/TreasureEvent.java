package com.example.dima.deephearth.FromIdea.Dungeon.Events;

import com.example.dima.deephearth.DungeonEventHandler;
import com.example.dima.deephearth.FromIdea.Dungeon.Choice;
import com.example.dima.deephearth.FromIdea.Dungeon.Drop;
import com.example.dima.deephearth.FromIdea.Dungeon.Event;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.ChoiceEvents.TakeEvent;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.Choices.LeaveChoice;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Dima on 09.05.2017.
 */

public class TreasureEvent extends Event {

    Drop drop;
    public TreasureEvent(DungeonEventHandler handler, String name, String description) {
        super(handler, name, description);
    }

    public TreasureEvent(Drop drop, DungeonEventHandler handler) {
        this(handler, "Treasure", "You have found treasure, would you take it?");
        this.drop = drop;

        choices.add(new TakeChoice(handler));
        choices.add(new LeaveChoice(handler));

    }

    class TakeChoice extends Choice{

        public TakeChoice(DungeonEventHandler handler, String name, LinkedList<Event> events) {
            super(handler, name, events);
        }

        TakeChoice(DungeonEventHandler handler) {
            super(handler, "Take all", new LinkedList<Event>(Arrays.asList(new TakeEvent(handler, drop))));
        }
    }
}
