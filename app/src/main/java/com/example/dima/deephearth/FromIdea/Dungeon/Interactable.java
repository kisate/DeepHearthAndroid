package com.example.dima.deephearth.FromIdea.Dungeon;

import com.example.dima.deephearth.DungeonEventHandler;
import com.example.dima.deephearth.FromIdea.Player;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by Dima on 09.05.2017.
 */

public abstract class Interactable implements Serializable{
    public InteractableTypes type;
    public LinkedList<Event> events = new LinkedList<>();
    public int image;
    DungeonEventHandler handler;

    public Interactable(DungeonEventHandler handler) {
        this.handler = handler;
    }

    public void interact(Player player) {
        handler.handle(events);
    }
}
