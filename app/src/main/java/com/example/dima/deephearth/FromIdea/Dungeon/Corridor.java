package com.example.dima.deephearth.FromIdea.Dungeon;

import com.example.dima.deephearth.CorridorView;
import com.example.dima.deephearth.FromIdea.Player;

import java.util.LinkedList;

/**
 * Created by Dima on 01.04.2017.
 */
public class Corridor {
    public Room either, other;
    public int orientation;
    public Event event;
    public Interactable interactable;
    public CorridorView corridorView;
    public Corridor revCorridor;
    public Corridor(Room either, Room other, int orientation) {
        this.either = either;
        this.other = other;
        this.orientation = orientation;
    }

    @Override
    public String toString() {
        return "Corridor{" +
                "either=" + either +
                ", other=" + other +
                '}';
    }

    public LinkedList<Event> onEnter(Player player) {
        return interactable.events;
    }
    public void onLeave(Player player) {

    }

    public void setCorridorView(CorridorView corridorView) {
        this.corridorView = corridorView;
    }

    public void setRevCorridor(Corridor revCorridor) {
        this.revCorridor = revCorridor;
    }
}
