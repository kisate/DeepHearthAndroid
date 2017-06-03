package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.FromIdea.HeroParams.AttackTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 29.05.2017.
 */

public class GunShot extends Skill{
    public GunShot(Unit owner) {
        super(owner);
        name = "Gun shot";
        dmgMod = Scale.B;
        cost = 15;
        accuracyMod = 0.9;
        canBeUsedFrom[0] = true;
        canBeUsedFrom[1] = true;
        canBeUsedFrom[2] = true;
        canBeUsedFrom[3] = true;
        canBeUsedOn[0] = true;
        canBeUsedOn[1] = true;
        canBeUsedOn[2] = true;
        canBeUsedOn[3] = true;
        bottom = 0.1;
        top = 3.0;
        description = "Can be deadly";
        skillIco = R.drawable.skillico_gunshot;
        attackType = AttackTypes.Ranged;
        setup();
        animMap.put("drawable", R.drawable.anim_gunshot);
    }

    @Override
    public void action(Unit target) {
        super.action(target);
        damage = (int)(damage*(1-target.defence));
        target.modHealth(damage);
    }
}
