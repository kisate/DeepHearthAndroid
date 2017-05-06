package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.FromIdea.HeroParams.AttackTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by student3 on 12.04.17.
 */
public class HingedBlow extends Skill{
    public HingedBlow(Unit owner) {
        super(owner);
        name = "Навесной удар";
        dmgMod = Scale.C;
        cost = 10;
        accuracyMod = 0.60;
        canBeUsedFrom[3] = true;
        canBeUsedFrom[2] = true;
        canBeUsedOn[0] = true;
        canBeUsedOn[1] = true;
        canBeUsedOn[2] = true;
        canBeUsedOn[3] = true;
        targetAmount = 4;
        bottom = 0.6;
        top = 1.3;
        description = "Закидать команду противника градом стрел";
        skillIco = R.drawable.skillico_hingedblow;
        attackType = AttackTypes.Ranged;
        setup();
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
