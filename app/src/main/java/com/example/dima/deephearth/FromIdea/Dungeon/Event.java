package com.example.dima.deephearth.FromIdea.Dungeon;

import com.example.dima.deephearth.FromIdea.Team;

import java.util.LinkedList;

/**
 * Created by Dima on 10.04.2017.
 */
public class Event {
    public String name, description;
    public LinkedList<Choise> choises = new LinkedList<>();

    public Event(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void choose(Team team){

    }
}
