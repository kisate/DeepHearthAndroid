package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.FromIdea.Effects.Bleeding;
import com.example.dima.deephearth.FromIdea.HeroParams.AttackTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Probability;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by student3 on 12.04.17.
 */
public class PointStrike extends Skill {
    public PointStrike(Unit owner) {
        super(owner);
        name = "Точечный выстрел";
        dmgMod = Scale.A;
        cost = 10;
        accuracyMod = 0.95;
        canBeUsedFrom[3] = true;
        canBeUsedFrom[2] = true;
        canBeUsedFrom[1] = true;
        canBeUsedOn[0] = true;
        canBeUsedOn[1] = true;
        canBeUsedOn[2] = true;
        canBeUsedOn[3] = true;
        bottom = 0.9;
        top = 1.5;
        description = "Выстрел в слабое место противника, может вызвать кровотечение.";
        skillIco = R.drawable.skillico_pointstrike;
        attackType = AttackTypes.Ranged;
        setup();
        effects.add(new Bleeding(null, (int)(2*effectMod), 2));
        animMap.put("pivotX", 0.5f);
        animMap.put("pivotY", 0.5f);
        animMap.put("drawable", R.drawable.hitanim_arrow);
    }

    @Override
    public void action(Unit target) {
        super.action(target);
        damage = (int)(damage*(1-target.defence));
        target.modHealth(damage);

    }


}
