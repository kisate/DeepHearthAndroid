package com.example.dima.deephearth.FromIdea.PlayerSkills;

import android.util.Log;

import com.example.dima.deephearth.FromIdea.Effect;
import com.example.dima.deephearth.FromIdea.Effects.Buff;
import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Player;
import com.example.dima.deephearth.FromIdea.PlayerSkill;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.FromIdea.UnitStat;
import com.example.dima.deephearth.Modificators.Mult;
import com.example.dima.deephearth.R;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Dima on 12.05.2017.
 */

public class UndeadRage extends PlayerSkill {
    int turns = 3;
    public UndeadRage(Player player) {
        super(player);
        power = 20;
        cost = 15;
        name = "Undead rage";
        description = "Increase target's power by " + power + "% for " + turns + " moves and heal it";
        effects.add(new UndeadRageBuff(null, power, turns));
        skillIco = R.drawable.skillico_undeadrage;
        friendly = true;
    }

    @Override
    public void action(Unit target) {
        super.action(target);
        player.mp-= cost;
    }

    private class UndeadRageBuff extends Buff {


        public UndeadRageBuff(Unit target, int power, int turns) {
            super(target, power, turns);
        }
        @Override
        public boolean apply() {
            super.apply();
            if (!applied) {
                Hero hero = (Hero) target;
                hero.power.addMod(new Mult(1 + power/100.0, "undead rage"));
                hero.countStats();
                hero.health = hero.maxHealth;
            }
            applied = true;
            boolean res = true;
            turns--;
            if (turns == 0) {remove(); res = false;}
            return res;
        }

        @Override
        public Effect addToTarget(Unit target) {

            Effect ef = new UndeadRageBuff(target, power, turns);
            target.effects.add(ef);
            ef.apply();

            return ef;
        }

        @Override
        public void remove() {
            super.remove();
            Hero hero = (Hero) target;
            hero.power.removeMod("undead rage");
            hero.countStats();
            hero.health = hero.maxHealth;
        }


    }
}
