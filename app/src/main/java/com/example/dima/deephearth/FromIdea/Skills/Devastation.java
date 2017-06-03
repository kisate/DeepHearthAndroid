package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.FromIdea.HeroParams.AttackTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 29.05.2017.
 */

public class Devastation extends Skill {
    public Devastation(Unit owner) {
        super(owner);
        name = "Devastation";
        dmgMod = Scale.C;
        cost = 1000;
        accuracyMod = 100;
        canBeUsedFrom[0] = true;
        canBeUsedFrom[1] = true;
        canBeUsedFrom[3] = true;
        canBeUsedFrom[2] = true;
        canBeUsedOn[0] = true;
        canBeUsedOn[1] = true;
        canBeUsedOn[2] = true;
        canBeUsedOn[3] = true;
        targetAmount = 4;
        bottom = 0.6;
        top = 1.3;
        description = "Prepare to die.";
        skillIco = R.drawable.skillico_hingedblow;
        attackType = AttackTypes.Ranged;
        setup();
        animMap.put("drawable", R.drawable.skillanim_empty);
    }


    @Override
    public void action(Unit target) {
        super.action(target);
        target.health = 1;
        damage = 9999;
        clearDamage = 9999;
        target.modHealth(damage);
    }
}
