package com.example.dima.deephearth.FromIdea;

import java.util.LinkedList;

/**
 * Created by Dima on 23.02.2017.
 */
public class Team extends LinkedList<Unit> {

    Player player;

    public void applyEffects() {
        for (Unit unit : this) {
            for (Effect effect :
                    unit.effects) {
                effect.apply();
            }
        }
    }

    public Team(Player player) {
        super();
        this.player = player;
    }
}
