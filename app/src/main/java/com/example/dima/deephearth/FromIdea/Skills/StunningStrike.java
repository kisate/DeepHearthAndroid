package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.FromIdea.Effects.Stun;
import com.example.dima.deephearth.FromIdea.HeroParams.AttackTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 29.05.2017.
 */

public class StunningStrike extends Skill {
    public StunningStrike(Unit owner) {
        super(owner);
        name = "Stunning strike";
        dmgMod = Scale.B;
        cost = 10;
        accuracyMod = 1;
        canBeUsedFrom[0] = true;
        canBeUsedFrom[1] = true;
        canBeUsedOn[0] = true;
        canBeUsedOn[1] = true;
        bottom = 0.8;
        top = 1.1;
        description = "Stun your target.";
        skillIco = R.drawable.skillico_stunningstrike;
        attackType = AttackTypes.Melee;
        setup();
        effects.add(new Stun(null, 1));
        animMap.put("drawable", R.drawable.skillanim_empty);
    }

    @Override
    public void action(Unit target) {
        super.action(target);
        damage = (int)(damage*(1-target.defence));
        target.modHealth(damage);
    }
}
