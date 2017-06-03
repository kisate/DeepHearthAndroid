package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.FromIdea.Effects.DemonicCurse;
import com.example.dima.deephearth.FromIdea.HeroParams.AttackTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 21.05.2017.
 */

public class OneShot extends Skill {
    public OneShot(Unit owner) {
        super(owner);
        name = "One shot";
        dmgMod = Scale.S;
        cost = 15;
        accuracyMod = 1.5;
        canBeUsedFrom[0] = true;
        canBeUsedFrom[1] = true;
        canBeUsedFrom[2] = true;
        canBeUsedFrom[3] = true;
        canBeUsedOn[0] = true;
        canBeUsedOn[1] = true;
        canBeUsedOn[2] = true;
        canBeUsedOn[3] = true;
        bottom = 0.1;
        top = 100.0;
        description = "Nerf this";
        skillIco = R.drawable.skillico1;
        attackType = AttackTypes.Ranged;
        setup();
        animMap.put("drawable", R.drawable.hitanim_arrow);
    }

    @Override
    public void action(Unit target) {
        super.action(target);
        damage = (int)(damage*(1-target.defence));
        target.modHealth(damage);
    }
}
