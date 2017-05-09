package com.example.dima.deephearth.FromIdea.Dungeon;

import com.example.dima.deephearth.FromIdea.Player;

import java.util.LinkedList;

/**
 * Created by Dima on 09.05.2017.
 */

public abstract class Interactable {
    public InteractableTypes type;
    public LinkedList<Event> events = new LinkedList<>();
    public int image;

    public Interactable() {
    }

    public LinkedList<Event> interact(Player player) {
        return events;
    }
}
