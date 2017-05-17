package com.example.dima.deephearth.FromIdea.Dungeon;

import com.example.dima.deephearth.DungeonEventHandler;
import com.example.dima.deephearth.FromIdea.Effect;
import com.example.dima.deephearth.FromIdea.Team;

import java.util.LinkedList;

/**
 * Created by Dima on 10.04.2017.
 */
public class Choice {

    public String name;
    public LinkedList<Event> events;
    DungeonEventHandler handler;

    public Choice(DungeonEventHandler handler, String name, LinkedList<Event> events) {
        this.handler = handler;
        this.name = name;
        this.events = events;
    }

    public void make(){
        handler.handle(events);
    }
}
