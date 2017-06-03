package com.example.dima.deephearth.FromIdea.Skills;

import android.util.Log;

import com.example.dima.deephearth.FromIdea.Effect;
import com.example.dima.deephearth.FromIdea.Effects.Buff;
import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.HeroParams.AttackTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 29.05.2017.
 */

public class EnhanceArmor extends Skill {
    public EnhanceArmor(Unit owner) {
        super(owner);
        name = "Enhance armor";
        cost = 20;
        dmgMod = Scale.B;
        accuracyMod = 1;
        friendly = true;
        onSelf = true;
        canBeUsedFrom[0] = true;
        canBeUsedFrom[1] = true;
        canBeUsedFrom[2] = true;
        canBeUsedFrom[3] = true;
        description = "Make target 50% stronger for 2 turns";
        skillIco = R.drawable.skillico_fotify;
        attackType = AttackTypes.Ranged;
        setup();
        power = 10;
        animMap.put("drawable", R.drawable.anim_fortify);
        heals = true;
        effects.add(new EnhanceArmorBuff(null, power, 2));
    }

    @Override
    public void action(Unit target) {
        setup();
        damage = 0;
    }

    class EnhanceArmorBuff extends Buff {
        public EnhanceArmorBuff(Unit target, int power, int turns) {
            super(target, power, turns);
        }

        @Override
        public boolean apply() {
            super.apply();
            if (!applied) {
                Hero hero = (Hero) target;
                hero.defence += power/100;
                hero.countStats();
            }
            applied = true;
            boolean res = true;
            turns--;
            if (turns == 0) {
                remove();
                res = false;
            }
            return res;
        }

        @Override
        public Effect addToTarget(Unit target) {

            Effect ef = new EnhanceArmorBuff(target, power, turns);
            target.effects.add(ef);
            ef.apply();

            return ef;
        }

        @Override
        public void remove() {
            super.remove();
            Hero hero = (Hero) target;
            hero.defence -= power/100;
            hero.countStats();
        }


    }

}