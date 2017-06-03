package com.example.dima.deephearth.FromIdea.Dungeon;

import android.util.Log;
import android.util.Pair;

import com.example.dima.deephearth.CorridorView;
import com.example.dima.deephearth.DungeonEventHandler;
import com.example.dima.deephearth.FromIdea.Dungeon.Interactables.Empty;
import com.example.dima.deephearth.FromIdea.Dungeon.Interactables.Exit;
import com.example.dima.deephearth.RandomSelector;

import java.util.LinkedList;

/**
 * Created by Dima on 01.04.2017.
 */
public class Dungeon {
    public LinkedList<Room> rooms = new LinkedList<>();
    Room[][] map;
    public LinkedList<Corridor> corridors = new LinkedList<>();
    private RandomSelector<Interactable> selector;
    DungeonEventHandler handler;
    public int enemyCount = 0;
    int retryCounter = 0;

    LinkedList<Pair<Interactable, Integer>> interactables = new LinkedList<>();

    public int maxX, maxY, minX, minY;

    public Dungeon(int size, LinkedList<Pair<Interactable, Integer>> interactables, DungeonEventHandler handler) {
        this.interactables = interactables;
        selector = new RandomSelector(interactables);
        this.handler = handler;
        Generate(size);
        while (enemyCount == 0) {
            rooms.clear();
            corridors.clear();
            Generate(size);
        }
    }

    private void Generate(int size){
        Room start = new Room(0,0);
        start.setInteractable(new Empty(handler));
        rooms.add(start);
        maxX = 0; maxY = 0; minX = 0; minY = 0;
        while (rooms.size() < size){
            rooms.add(nextRoom());
        }

        map = new Room[maxX-minX + 1][maxY-minY + 1];

        for (Room room :
                rooms) {
            map[room.x-minX][room.y-minY] = room;
        }

        addCorridors((int)((size/3 + 1)*Math.random()));
    }

    private Room nextRoom () {
        Room core = rooms.get((int)(Math.random()*rooms.size()));
        int x = core.x , y = core.y;
        int orientation = CorridorView.HORIZONTAL;
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

        if (r == 0 || r == 2) orientation = CorridorView.VERTICAL;

        Room next = new Room(x,y);
        Interactable interactable = selector.getRandomElement();
        if (interactable.type == InteractableTypes.Enemy) enemyCount++;
        next.setInteractable(interactable);
        Corridor res = new Corridor(core, next, orientation);

        core.corridors[r] = res;
        Corridor revRes = new Corridor(next, core , orientation);
        res.setRevCorridor(revRes);
        revRes.setRevCorridor(res);
        next.corridors[(r + 2) % 4] = revRes;
        corridors.add(res);

        return next;
    }


    void addCorridors(int amount) {
        int count = 0;

        while (count < amount) {

            Room room = map[(int) (Math.random() * map.length)][(int) (Math.random() * map[0].length)];

            while (room == null) room = map[(int) (Math.random() * map.length)][(int) (Math.random() * map[0].length)];

            LinkedList<Room> availableRooms = new LinkedList<>();

            if (room.y < maxY && map[room.x - minX][room.y - minY + 1] != null && room.corridors[0] == null)
                availableRooms.add(map[room.x - minX][room.y - minY + 1]);
            else if (room.x < maxX && map[room.x - minX + 1][room.y - minY] != null && room.corridors[1] == null)
                availableRooms.add(map[room.x - minX + 1][room.y - minY]);
            else if (room.y > minY && map[room.x - minX][room.y - minY - 1] != null && room.corridors[2] == null)
                availableRooms.add(map[room.x - minX][room.y - minY - 1]);
            else if (room.x > minX && map[room.x - minX - 1][room.y - minY] != null && room.corridors[3] == null)
                availableRooms.add(map[room.x - minX - 1][room.y - minY]);

            retryCounter=0;

            while (availableRooms.size() == 0) {
                if (retryCounter > 4) {
                    addCorridors(amount - count - 1);
                    return;
                }
                room = map[(int) (Math.random() * map.length)][(int) (Math.random() * map[0].length)];
                while (room == null) room = map[(int) (Math.random() * map.length)][(int) (Math.random() * map[0].length)];
                if (room.y < maxY && map[room.x - minX][room.y - minY + 1] != null && room.corridors[0] == null)
                    availableRooms.add(map[room.x - minX][room.y - minY + 1]);
                else if (room.x < maxX && map[room.x - minX + 1][room.y - minY] != null && room.corridors[1] == null)
                    availableRooms.add(map[room.x - minX + 1][room.y - minY]);
                else if (room.y > minY && map[room.x - minX][room.y - minY - 1] != null && room.corridors[2] == null)
                    availableRooms.add(map[room.x - minX][room.y - minY - 1]);
                else if (room.x > minX && map[room.x - minX - 1][room.y - minY] != null && room.corridors[3] == null)
                    availableRooms.add(map[room.x - minX - 1][room.y - minY]);
                retryCounter++;
            }

            Room next = availableRooms.get((int)(Math.random()*availableRooms.size()));

            int r = 0;

            if (next.y > room.y) r = 0;
            if (next.x > room.x) r = 1;
            if (next.y < room.y) r = 2;
            if (next.x < room.x) r = 3;

            int orientation = CorridorView.HORIZONTAL;

            if (r == 0 || r == 2) orientation = CorridorView.VERTICAL;

            Corridor res = new Corridor(room, next, orientation);

            room.corridors[r] = res;
            Corridor revRes = new Corridor(next, room , orientation);
            res.setRevCorridor(revRes);
            revRes.setRevCorridor(res);
            next.corridors[(r + 2) % 4] = revRes;
            corridors.add(res);

            count++;
        }
    }
}
