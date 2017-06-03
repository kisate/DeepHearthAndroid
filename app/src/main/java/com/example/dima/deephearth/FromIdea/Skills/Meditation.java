package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.FromIdea.HeroParams.AttackTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 29.05.2017.
 */

public class Meditation extends Skill {
    public Meditation(Unit owner) {
        super(owner);
        skillIco = R.drawable.skillico_meditation;
        onSelf = true;
        heals = true;
        dmgMod = Scale.B;
        attackType = AttackTypes.Ranged;
        name = "Meditation";
        canBeUsedFrom[0] = true;
        canBeUsedFrom[1] = true;
        canBeUsedFrom[2] = true;
        canBeUsedFrom[3] = true;
        setup();
        animMap.put("drawable", R.drawable.anim_meditation);
    }

    @Override
    public void action(Unit target) {
        super.action(target);
        owner.mana.clearValue+= Math.min(damage, owner.maxMana.getValue()-owner.mana.clearValue);
        damage = 0;
    }
}
