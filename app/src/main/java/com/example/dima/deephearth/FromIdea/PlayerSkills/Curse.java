package com.example.dima.deephearth.FromIdea.PlayerSkills;

import com.example.dima.deephearth.FromIdea.Player;
import com.example.dima.deephearth.FromIdea.PlayerSkill;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 12.05.2017.
 */

public class Curse extends PlayerSkill {
    int turns = 3;
    public Curse(Player player) {
        super(player);
        cost = 15;
        power = 2;
        name = "Curse";
        description = "Add Curse " + power +"/"+ turns + " to target";
        effects.add(new com.example.dima.deephearth.FromIdea.Effects.Curse(null, power, turns));
        skillIco = R.drawable.skillico1;
        friendly = false;
    }

    @Override
    public void action(Unit target) {
        super.action(target);
        player.mp-= cost;
    }
}
