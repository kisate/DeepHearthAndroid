package com.example.dima.deephearth.FromIdea.Dungeon;

import android.util.Log;
import android.util.Pair;

import com.example.dima.deephearth.CorridorView;
import com.example.dima.deephearth.FromIdea.Dungeon.Interactables.Empty;
import com.example.dima.deephearth.RandomSelector;

import java.util.LinkedList;

/**
 * Created by Dima on 01.04.2017.
 */
public class Dungeon {
    public LinkedList<Room> rooms = new LinkedList<>();
    public LinkedList<Corridor> corridors = new LinkedList<>();
    private RandomSelector<Interactable> selector;

    LinkedList<Pair<Interactable, Integer>> interactables = new LinkedList<>();

    public int maxX, maxY, minX, minY;

    public Dungeon(int size, LinkedList<Pair<Interactable, Integer>> interactables) {
        this.interactables = interactables;
        selector = new RandomSelector(interactables);
        Generate(size);
    }

    private void Generate(int size){
        Room start = new Room(0,0);
        start.setInteractable(new Empty());
        rooms.add(start);
        maxX = 0; maxY = 0; minX = 0; minY = 0;
        while (rooms.size() < size){
            rooms.add(nextRoom());
        }
    }

    private Room nextRoom () {
        Room core = rooms.get((int)(Math.random()*rooms.size()));
        int x = core.x , y = core.y;
        int orientaion = CorridorView.HORIZONTAL;
        LinkedList<Integer> vars = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            if (core.corridors[i] == null) vars.add(i);
        }
        while (vars.size() == 0) {
            core = rooms.get((int)(Math.random()*rooms.size()));
            for (int i = 0; i < 4; i++) {
                if (core.corridors[i] == null) vars.add(i);
            }
        }

        int r = vars.get((int)(Math.random()*vars.size()));

        switch (r) {
            case 0 : y++; break;
            case 1 : x++; break;
            case 2 : y--; break;
            case 3 : x--; break;
            default: break;
        }
        boolean t = false;
        for (Room room :
                rooms) {
            if ((room.x == x) && (room.y == y)) t = true;
        }

        if(t) return nextRoom();

        if (x > maxX) maxX = x;
        if (x < minX) minX = x;
        if (y > maxY) maxY = y;
        if (y < minY) minY = y;

        if (r == 0 || r == 2) orientaion = CorridorView.VERTICAL;

        Room next = new Room(x,y);
        Interactable interactable = selector.getRandomElement();
        next.setInteractable(interactable);
        Corridor res = new Corridor(core, next, orientaion);

        core.corridors[r] = res;
        Corridor revRes = new Corridor(next, core , orientaion);
        res.setRevCorridor(revRes);
        revRes.setRevCorridor(res);
        next.corridors[(r + 2) % 4] = revRes;
        corridors.add(res);

        return next;
    }

}
