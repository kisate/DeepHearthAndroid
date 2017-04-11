package com.example.dima.deephearth.FromIdea.Dungeon;

/**
 * Created by Dima on 01.04.2017.
 */
public class Corridor {
    public Room either, other;
    public Event event;
    public Corridor(Room either, Room other) {
        this.either = either;
        this.other = other;
    }
}
