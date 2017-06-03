package com.example.dima.deephearth.FromIdea.Dungeon.DungeonDatas;

import android.util.Pair;

import com.example.dima.deephearth.DungeonEventHandler;
import com.example.dima.deephearth.FromIdea.ComputerIntellect;
import com.example.dima.deephearth.FromIdea.Dungeon.Drop;
import com.example.dima.deephearth.FromIdea.Dungeon.Droppable;
import com.example.dima.deephearth.FromIdea.Dungeon.DungeonData;
import com.example.dima.deephearth.FromIdea.Dungeon.Interactable;
import com.example.dima.deephearth.FromIdea.Dungeon.Interactables.Empty;
import com.example.dima.deephearth.FromIdea.Dungeon.Interactables.Enemy;
import com.example.dima.deephearth.FromIdea.Dungeon.Interactables.Exit;
import com.example.dima.deephearth.FromIdea.Dungeon.Interactables.Treasure;
import com.example.dima.deephearth.FromIdea.Dungeon.Loot;
import com.example.dima.deephearth.FromIdea.Dungeon.Soul;
import com.example.dima.deephearth.FromIdea.Dungeon.SoulSizes;
import com.example.dima.deephearth.FromIdea.Items.FireSword;
import com.example.dima.deephearth.FromIdea.Items.GreatStaff;
import com.example.dima.deephearth.FromIdea.Player;
import com.example.dima.deephearth.FromIdea.Units.UnitConstructor;

import java.util.LinkedList;

/**
 * Created by Dima on 30.05.2017.
 */

public class BasicDungeonData extends DungeonData {
    public BasicDungeonData(DungeonEventHandler handler) {
        interactables = new LinkedList<>();
        interactables.add(new Pair<Interactable, Integer>(new Empty(handler), 400));


        Player enemy1 = new Player(new ComputerIntellect());
        enemy1.team.set(0, UnitConstructor.constructSkeletonKnight(enemy1.team));
        enemy1.team.player = enemy1;

        Loot loot1 = new Loot();
        LinkedList<MyPair<Droppable, Integer>> list1 = new LinkedList<>();
        list1.add(new MyPair<Droppable, Integer>(new Soul(SoulSizes.Weak), 3));
        list1.add(new MyPair<Droppable, Integer>(new Soul(SoulSizes.Common), 1));
        loot1.dropList.add(new MyPair<>(list1, 1f));

        enemy1.loot = loot1;

        Player enemy2 = new Player(new ComputerIntellect());
        enemy2.team.set(0, UnitConstructor.constructSkeletonKnight(enemy2.team));
        enemy2.team.set(1, UnitConstructor.constructSkeletonKnight(enemy2.team));
        enemy2.team.set(2, UnitConstructor.constructSkeletonKnight(enemy2.team));
        enemy2.team.player = enemy2;

        Loot loot2 = new Loot();
        LinkedList<MyPair<Droppable, Integer>> list21 = new LinkedList<>();
        list21.add(new MyPair<Droppable, Integer>(new Soul(SoulSizes.Weak), 3));
        list21.add(new MyPair<Droppable, Integer>(new Soul(SoulSizes.Common), 1));
        loot2.dropList.add(new MyPair<>(list21, 1f));
        LinkedList<MyPair<Droppable, Integer>> list22 = new LinkedList<>();
        list22.add(new MyPair<Droppable, Integer>(new Soul(SoulSizes.Weak), 3));
        list22.add(new MyPair<Droppable, Integer>(new Soul(SoulSizes.Common), 1));
        loot2.dropList.add(new MyPair<>(list22, 1f));
        LinkedList<MyPair<Droppable, Integer>> list23 = new LinkedList<>();
        list23.add(new MyPair<Droppable, Integer>(new Soul(SoulSizes.Weak), 3));
        list23.add(new MyPair<Droppable, Integer>(new Soul(SoulSizes.Common), 1));
        loot2.dropList.add(new MyPair<>(list23, 1f));

        enemy2.loot = loot2;

        Player enemy3 = new Player(new ComputerIntellect());
        enemy3.team.set(2, UnitConstructor.constructSkeletonArcher(enemy3.team));
        enemy3.team.set(3, UnitConstructor.constructSkeletonArcher(enemy3.team));
        enemy3.team.player = enemy3;

        Loot loot3 = new Loot();
        LinkedList<MyPair<Droppable, Integer>> list31 = new LinkedList<>();
        list31.add(new MyPair<Droppable, Integer>(new Soul(SoulSizes.Weak), 3));
        list31.add(new MyPair<Droppable, Integer>(new Soul(SoulSizes.Common), 1));
        loot3.dropList.add(new MyPair<>(list31, 1f));
        LinkedList<MyPair<Droppable, Integer>> list32 = new LinkedList<>();
        list32.add(new MyPair<Droppable, Integer>(new Soul(SoulSizes.Weak), 3));
        list32.add(new MyPair<Droppable, Integer>(new Soul(SoulSizes.Common), 1));
        loot3.dropList.add(new MyPair<>(list32, 1f));

        enemy3.loot = loot3;

        Player enemy4 = new Player(new ComputerIntellect());
        enemy4.team.set(0, UnitConstructor.constructSkeletonKnight(enemy4.team));
        enemy4.team.set(2, UnitConstructor.constructSkeletonArcher(enemy4.team));
        enemy4.team.player = enemy4;

        Loot loot4 = new Loot();
        LinkedList<MyPair<Droppable, Integer>> list41 = new LinkedList<>();
        list41.add(new MyPair<Droppable, Integer>(new Soul(SoulSizes.Weak), 3));
        list41.add(new MyPair<Droppable, Integer>(new Soul(SoulSizes.Common), 1));
        loot4.dropList.add(new MyPair<>(list41, 1f));
        LinkedList<MyPair<Droppable, Integer>> list42 = new LinkedList<>();
        list42.add(new MyPair<Droppable, Integer>(new Soul(SoulSizes.Weak), 3));
        list42.add(new MyPair<Droppable, Integer>(new Soul(SoulSizes.Common), 1));
        loot4.dropList.add(new MyPair<>(list42, 1f));

        enemy4.loot = loot4;

        Drop drop1 = new Drop();
        drop1.add(new Soul(SoulSizes.Strong));

        Drop drop2 = new Drop();
        drop2.add(new Soul(SoulSizes.Weak));
        drop2.add(new Soul(SoulSizes.Weak));
        drop2.add(new Soul(SoulSizes.Common));

        Drop drop3 = new Drop();
        drop3.add(new GreatStaff());

        Drop drop4 = new Drop();
        drop4.add(new FireSword());
        drop4.add(new Soul(SoulSizes.Hero));

        interactables.add(new Pair<Interactable, Integer>(new Enemy(enemy1, handler), 40));
        interactables.add(new Pair<Interactable, Integer>(new Enemy(enemy2, handler), 10));
        interactables.add(new Pair<Interactable, Integer>(new Enemy(enemy3, handler), 30));
        interactables.add(new Pair<Interactable, Integer>(new Enemy(enemy4, handler), 20));
        interactables.add(new Pair<Interactable, Integer>(new Treasure(drop1, handler), 20));
        interactables.add(new Pair<Interactable, Integer>(new Treasure(drop2, handler), 15));
        interactables.add(new Pair<Interactable, Integer>(new Treasure(drop3, handler), 10));
        interactables.add(new Pair<Interactable, Integer>(new Treasure(drop4, handler), 3));
        interactables.add(new Pair<Interactable, Integer>(new Exit(handler), 3));
    }
}
