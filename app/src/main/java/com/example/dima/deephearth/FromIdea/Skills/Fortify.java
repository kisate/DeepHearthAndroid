package com.example.dima.deephearth.FromIdea.Skills;

import android.util.Log;

import com.example.dima.deephearth.FromIdea.Effect;
import com.example.dima.deephearth.FromIdea.Effects.Buff;
import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.HeroParams.AttackTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.Modificators.Add;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 29.05.2017.
 */

public class Fortify extends Skill {
    public Fortify(Unit owner) {
        super(owner);
        skillIco = R.drawable.skillico_fotify;
        onSelf = true;
        dmgMod = Scale.D;
        attackType = AttackTypes.Ranged;
        name = "Fortify";
        canBeUsedFrom[0] = true;
        canBeUsedFrom[1] = true;
        canBeUsedFrom[2] = true;
        canBeUsedFrom[3] = true;
        setup();
        power = 50;
        animMap.put("drawable", R.drawable.anim_fortify);
        effects.add(new FortifyBuff(null, power, 1));
    }

    @Override
    public void action(Unit target) {
        super.action(target);
        damage = 0;
    }

    class FortifyBuff extends Buff {
        public FortifyBuff(Unit target, int power, int turns) {
            super(target, power, turns);
        }

        @Override
        public boolean apply() {
            super.apply();
            if (!applied) {
                Hero hero = (Hero) target;
                hero.defence.addMod(new Add(power/100.0, "fortify"));
                hero.countStats();
                Log.d("Debug", "" + hero.defence.getValue());
            }
            applied = true;
            boolean res = true;
            if (turns == 0) {
                remove();
                res = false;
            }
            turns--;
            return res;
        }

        @Override
        public Effect addToTarget(Unit target) {

            Effect ef = new FortifyBuff(target, power, turns);
            target.effects.add(ef);
            ef.apply();

            return ef;
        }

        @Override
        public void remove() {
            super.remove();
            Hero hero = (Hero) target;
            hero.defence.removeMod("fortify");
            hero.countStats();
        }

    }
}
