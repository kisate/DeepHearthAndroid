package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.FromIdea.Effects.Curse;
import com.example.dima.deephearth.FromIdea.Effects.DemonicCurse;
import com.example.dima.deephearth.FromIdea.HeroParams.AttackTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 29.05.2017.
 */

public class CurseOrb extends Skill {
    public CurseOrb(Unit owner) {
        super(owner);
        name = "Curse orb";
        dmgMod = Scale.A;
        cost = 15;
        accuracyMod = 0.85;
        canBeUsedFrom[0] = true;
        canBeUsedFrom[1] = true;
        canBeUsedFrom[2] = true;
        canBeUsedFrom[3] = true;
        canBeUsedOn[0] = true;
        canBeUsedOn[1] = true;
        canBeUsedOn[2] = true;
        canBeUsedOn[3] = true;
        bottom = 0.9;
        top = 1.2;
        description = "Curse orb.";
        skillIco = R.drawable.skillico_demonicarrow;
        attackType = AttackTypes.Ranged;
        setup();
        effects.add(new Curse(null, (int)(2*effectMod), 2));
        animMap.put("drawable", R.drawable.heal_anim);
    }

    @Override
    public void action(Unit target) {
        super.action(target);
        damage = (int)(damage*(1-target.defence));
        target.modHealth(damage);
    }
}
