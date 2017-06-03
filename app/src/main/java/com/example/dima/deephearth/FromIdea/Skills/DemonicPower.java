package com.example.dima.deephearth.FromIdea.Skills;

import android.util.Log;

import com.example.dima.deephearth.FromIdea.Effect;
import com.example.dima.deephearth.FromIdea.Effects.Buff;
import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.HeroParams.AttackTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.PlayerSkills.UndeadRage;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.Modificators.Mult;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 29.05.2017.
 */

public class DemonicPower extends Skill {
    public DemonicPower(Unit owner) {
        super(owner);
        name = "Demonic power";
        cost = 15;
        dmgMod = Scale.B;
        heals = true;
        accuracyMod = 1;
        friendly = true;
        canBeUsedFrom[0] = true;
        canBeUsedFrom[1] = true;
        canBeUsedFrom[2] = true;
        canBeUsedFrom[3] = true;
        description = "Make target 50% stronger for 2 turns";
        skillIco = R.drawable.skillico_undeadrage;
        attackType = AttackTypes.Ranged;
        setup();
        power = 50;
        animMap.put("drawable", R.drawable.heal_anim);
        heals = true;
        effects.add(new DemonicPowerBuff(null, power, 2));
    }

    @Override
    public void action(Unit target) {
        setup();
        damage = 0;
    }

    class DemonicPowerBuff extends Buff {
        public DemonicPowerBuff(Unit target, int power, int turns) {
            super(target, power, turns);
        }

        @Override
        public boolean apply() {
            super.apply();
            if (!applied) {
                Hero hero = (Hero) target;
                Log.d("Debug", "" + hero.power);
                hero.power.addMod(new Mult(1 + power/100.0, "demonic power"));
                Log.d("Debug", "" + hero.power);
                hero.countStats();
            }
            applied = true;
            boolean res = true;
            if (turns == 0) {
                remove();
                res = false;
            }
            turns--;
            Log.d("Debug", "" + turns);
            return res;
        }

        @Override
        public Effect addToTarget(Unit target) {

            Effect ef = new DemonicPowerBuff(target, power, turns);
            target.effects.add(ef);
            ef.apply();

            return ef;
        }

        @Override
        public void remove() {
            super.remove();
            Hero hero = (Hero) target;
            hero.power.removeMod("demonic power");
            hero.countStats();
            hero.health = hero.maxHealth;
            Log.d("Debug", "" + hero.power);
        }


    }

}
