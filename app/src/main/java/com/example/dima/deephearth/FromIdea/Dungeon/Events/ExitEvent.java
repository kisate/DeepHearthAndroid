package com.example.dima.deephearth.FromIdea.Dungeon.Events;

import com.example.dima.deephearth.DungeonEventHandler;
import com.example.dima.deephearth.FromIdea.Dungeon.Choice;
import com.example.dima.deephearth.FromIdea.Dungeon.Dungeon;
import com.example.dima.deephearth.FromIdea.Dungeon.Event;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.ChoiceEvents.LeaveDungeonEvent;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.Choices.LeaveChoice;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Dima on 27.05.2017.
 */

public class ExitEvent extends Event {
    public ExitEvent(DungeonEventHandler handler, String name, String description) {
        super(handler, name, description);
    }

    public ExitEvent(DungeonEventHandler handler) {
        super(handler, "Dungeon exit", "You have entered dungeon exit. Are you ready to leave?");
        choices.add(new ExitChoice(handler));
        choices.add(new LeaveChoice(handler));
    }

    class ExitChoice extends Choice {

        public ExitChoice(DungeonEventHandler handler, String name, LinkedList<Event> events) {
            super(handler, name, events);
        }

        public ExitChoice(DungeonEventHandler handler) {
            super(handler, "Leave dungeon", new LinkedList<Event>(Arrays.asList(new LeaveDungeonEvent(handler))));
        }
    }
}
