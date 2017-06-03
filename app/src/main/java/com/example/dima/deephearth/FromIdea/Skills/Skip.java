package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.BattleActivity;
import com.example.dima.deephearth.FromIdea.HeroParams.AttackTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 28.03.2017.
 */
public class Skip extends Skill {
    public Skip(Unit owner) {
        super(owner);
        skillIco = R.drawable.skillico_skip;
        onSelf = true;
        heals = true;
        dmgMod = Scale.D;
        attackType = AttackTypes.Ranged;
        name = "Skip";
        canBeUsedFrom[0] = true;
        canBeUsedFrom[1] = true;
        canBeUsedFrom[2] = true;
        canBeUsedFrom[3] = true;
        setup();
        animMap.put("drawable", R.drawable.skillanim_empty);
    }

    @Override
    public void action(Unit target) {
        super.action(target);
        damage = 0;
    }
}
