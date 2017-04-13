package com.example.dima.deephearth.FromIdea.Skills;

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
        skillIco = R.drawable.skillico3;
        setup();
    }

    @Override
    public boolean use(Unit target) {
        for (Unit unit :
                target.team) {
            super.use(unit);
        }
        return true;
    }

    @Override
    public void action(Unit target) {
        super.action(target);
        target.modHealth((int)(damage*(1-target.defence)));
    }
}
