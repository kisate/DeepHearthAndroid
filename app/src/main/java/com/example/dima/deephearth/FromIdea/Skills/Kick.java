package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.FromIdea.Effects.Bleeding;
import com.example.dima.deephearth.FromIdea.Effects.Move;
import com.example.dima.deephearth.FromIdea.HeroParams.AttackTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 28.05.2017.
 */

public class Kick extends Skill {
    public Kick(Unit owner) {
        super(owner);
        name = "Kick";
        cost = 20;
        dmgMod = Scale.C;
        accuracyMod = 2;
        canBeUsedFrom[0] = true;
        canBeUsedFrom[1] = true;
        canBeUsedOn[0] = true;
        canBeUsedOn[1] = true;
        description = "Kick target";
        skillIco = R.drawable.skillico_kick;
        attackType = AttackTypes.Melee;
        setup();
        effects.add(new Move(null, 2));
        moves = true;
        animMap.put("drawable", R.drawable.skillanim_empty);
    }

    @Override
    public void action(Unit target) {
        super.action(target);
        damage = (int)(damage*(1-target.defence.getValue()));
        target.modHealth(damage);
    }
}
