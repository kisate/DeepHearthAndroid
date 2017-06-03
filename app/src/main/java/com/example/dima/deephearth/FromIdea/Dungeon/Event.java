package com.example.dima.deephearth.FromIdea.Dungeon;

import com.example.dima.deephearth.DungeonEventHandler;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.EventTypes;
import com.example.dima.deephearth.FromIdea.EffectTypes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Dima on 10.04.2017.
 */
public abstract class Event implements Serializable{
    public String name, description;
    public int imageId = 0;
    public ArrayList<Choice> choices = new ArrayList<>();
    DungeonEventHandler handler;
    public EventTypes type;

    public Event(DungeonEventHandler handler, String name, String description) {
        this.handler = handler;
        this.name = name;
        this.description = description;
        type = EventTypes.toDisplay;
    }
}
