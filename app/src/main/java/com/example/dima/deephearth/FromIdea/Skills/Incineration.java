package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.FromIdea.Effects.Bleeding;
import com.example.dima.deephearth.FromIdea.Effects.HolyFire;
import com.example.dima.deephearth.FromIdea.HeroParams.AttackTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 29.05.2017.
 */

public class Incineration extends Skill {
    public Incineration(Unit owner) {
        super(owner);
        name = "Incineration";
        cost = 15;
        dmgMod = Scale.B;
        accuracyMod = 0.85;
        canBeUsedFrom[0] = true;
        canBeUsedFrom[1] = true;
        canBeUsedFrom[2] = true;
        canBeUsedFrom[3] = true;
        canBeUsedOn[0] = true;
        canBeUsedOn[1] = true;
        canBeUsedOn[2] = true;
        canBeUsedOn[3] = true;
        description = "BURN THE UNDEAD";
        skillIco = R.drawable.skillico_incineration;
        attackType = AttackTypes.Ranged;
        setup();
        effects.add(new HolyFire(null, (int)(2*effectMod), 2));
        animMap.put("drawable", R.drawable.anim_incineration);
    }

    @Override
    public void action(Unit target) {
        super.action(target);
        damage = (int)(damage*(1-target.defence.getValue()));
        target.modHealth(damage);
    }
}
