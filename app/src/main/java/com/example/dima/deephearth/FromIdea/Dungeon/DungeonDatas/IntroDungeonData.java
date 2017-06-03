package com.example.dima.deephearth.FromIdea.Dungeon.DungeonDatas;

import android.util.Pair;

import com.example.dima.deephearth.DungeonEventHandler;
import com.example.dima.deephearth.FromIdea.ComputerIntellect;
import com.example.dima.deephearth.FromIdea.Dungeon.Droppable;
import com.example.dima.deephearth.FromIdea.Dungeon.DungeonData;
import com.example.dima.deephearth.FromIdea.Dungeon.Interactable;
import com.example.dima.deephearth.FromIdea.Dungeon.Interactables.Boss;
import com.example.dima.deephearth.FromIdea.Dungeon.Interactables.Empty;
import com.example.dima.deephearth.FromIdea.Dungeon.Interactables.Enemy;
import com.example.dima.deephearth.FromIdea.Dungeon.Loot;
import com.example.dima.deephearth.FromIdea.Dungeon.Soul;
import com.example.dima.deephearth.FromIdea.Dungeon.SoulSizes;
import com.example.dima.deephearth.FromIdea.Heroes.BossConstructor;
import com.example.dima.deephearth.FromIdea.Intellects.LichIntellect;
import com.example.dima.deephearth.FromIdea.Player;
import com.example.dima.deephearth.FromIdea.Units.UnitConstructor;

import java.util.LinkedList;

/**
 * Created by Dima on 29.05.2017.
 */

public class IntroDungeonData extends DungeonData {
    public IntroDungeonData(DungeonEventHandler handler) {
        interactables = new LinkedList<>();
        interactables.add(new Pair<Interactable, Integer>(new Empty(handler), 15));


        Player enemy2 = new Player(new ComputerIntellect());
        enemy2.team.set(0, UnitConstructor.constructSkeletonKnight(enemy2.team));
        enemy2.team.player = enemy2;

        Player enemy3 = new Player(new LichIntellect());
        enemy3.team.set(0, UnitConstructor.constructSkeletonKnight(enemy3.team));
        enemy3.team.set(1, UnitConstructor.constructSkeletonKnight(enemy3.team));
        enemy3.team.set(3, BossConstructor.constructLich("Jostaus III", enemy3.team));
        enemy3.team.player = enemy3;

        Player enemy4 = new Player(new ComputerIntellect());
        enemy4.team.set(0, UnitConstructor.constructSkeletonKnight(enemy4.team));
        enemy4.team.set(2, UnitConstructor.constructSkeletonArcher(enemy4.team));
        enemy4.team.player = enemy4;

        interactables.add(new Pair<Interactable, Integer>(new Enemy(enemy2, handler), 2));
        interactables.add(new Pair<Interactable, Integer>(new Enemy(enemy4, handler), 1));
        boss = new Boss(handler, enemy3);
    }

    public IntroDungeonData(LinkedList<Pair<Interactable, Integer>> interactables) {
        super(interactables);
    }
}
