package com.example.dima.deephearth.FromIdea.PlayerSkills;

import com.example.dima.deephearth.FromIdea.Player;
import com.example.dima.deephearth.FromIdea.PlayerSkill;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 12.05.2017.
 */

public class Renewal extends PlayerSkill {
    public Renewal(Player player) {
        super(player);
        name = "Renewal";
        cost = 15;
        power = 20;
        description = "Add target unit " + power + " mp";
        skillIco = R.drawable.skillico_renewal;
        friendly = true;
    }

    @Override
    public void action(Unit target) {
        super.action(target);
        target.mana+= Math.min(power, target.maxMana-target.mana);
        for (Unit u :
                target.team) {
            u.health = 0;
        }
        player.mp-= cost;
    }
}
