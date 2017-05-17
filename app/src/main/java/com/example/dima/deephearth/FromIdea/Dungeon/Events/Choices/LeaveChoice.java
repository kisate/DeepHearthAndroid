package com.example.dima.deephearth.FromIdea.Dungeon.Events.Choices;

import com.example.dima.deephearth.DungeonEventHandler;
import com.example.dima.deephearth.FromIdea.Dungeon.Choice;
import com.example.dima.deephearth.FromIdea.Dungeon.Event;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.ChoiceEvents.LeaveEvent;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Dima on 14.05.2017.
 */

public class LeaveChoice extends Choice {
    public LeaveChoice(DungeonEventHandler handler, String name, LinkedList<Event> events) {
        super(handler, name, events);
    }
    public LeaveChoice(DungeonEventHandler handler) {
        super(handler, "leave", new LinkedList<Event>(Arrays.asList(new LeaveEvent(handler))));
    }
}