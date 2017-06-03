package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.FromIdea.HeroParams.AttackTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.FromIdea.Units.UnitConstructor;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 29.05.2017.
 */

public class Resurrection extends Skill {
    public Resurrection(Unit owner) {
        super(owner);
        cost = 30;
        skillIco = R.drawable.skillico_skip;
        onSelf = true;
        dmgMod = Scale.D;
        attackType = AttackTypes.Ranged;
        name = "Resurrection";
        heals = true;
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
        for (int i = 0; i < 4; i++) {
            if (target.team.get(i) == null) target.team.set(i, UnitConstructor.constructSkeletonKnight(target.team));
            else {
                if (target.team.get(i) != owner) target.team.get(0).mana = target.team.get(0).maxMana;

            }
        }
    }
}
