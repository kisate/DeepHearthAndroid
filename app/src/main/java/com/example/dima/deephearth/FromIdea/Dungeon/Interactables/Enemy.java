package com.example.dima.deephearth.FromIdea.Dungeon.Interactables;

import com.example.dima.deephearth.FromIdea.Dungeon.Interactable;
import com.example.dima.deephearth.FromIdea.Dungeon.ObjectTypes;

/**
 * Created by Dima on 10.04.2017.
 */
public class Enemy extends Interactable{
    public Enemy (){
        this.type = ObjectTypes.Enemy;
        this.price = 1;
    }
}
