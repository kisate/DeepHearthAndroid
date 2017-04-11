package com.example.dima.deephearth.FromIdea.Dungeon;

import java.util.LinkedList;

/**
 * Created by Dima on 01.04.2017.
 */
public class Dungeon {
    public LinkedList<Room> rooms = new LinkedList<>();
    public LinkedList<Corridor> corridors = new LinkedList<>();

    public Dungeon (int size) {
        Genetate(size);
    }

    public void Genetate(int size){
        rooms.add(new Room(0,0));
        int count = 1;
        while (count < size){
            rooms.add(nextRoom());
        }
    }

    public Room nextRoom () {
        int r = (int)Math.random()*4;

        Room core = rooms.get((int)(Math.random()*rooms.size()));
        int x = core.x , y = core.y;
        switch (r) {
            case 0 : x++; break;
            case 1 : y++; break;
            case 2 : x--; break;
            case 3 : y--; break;
            default: break;
        }

        boolean t = false;
        for (Room room :
                rooms) {
            if ((room.x == x) && (room.y == y)) t = true;
        }

        if(t) return nextRoom();

        Room next = new Room(x,y);

        corridors.add(new Corridor(core, next));

        return next;
    }
}
