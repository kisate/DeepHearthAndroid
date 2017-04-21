package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.BattleActivity;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by student3 on 12.04.17.
 */
public class DagerStrike extends Skill{
    public DagerStrike(Unit owner) {
        super(owner);
        name = "Удар кинжалом";
        dmgMod = Scale.B;
        cost = 7;
        accuracyMod = 0.8;
        canBeUsedFrom[0] = true;
        canBeUsedFrom[1] = true;
        canBeUsedOn[0] = true;
        canBeUsedOn[1] = true;
        bottom = 0.8;
        top = 1.1;
        description = "Атаковать цель кинжалом";
        skillIco = R.drawable.skillico1;
        setup();
    }

    @Override
    public void action(Unit target) {
        super.action(target);
        target.modHealth((int)(damage*(1-target.defence)));
        BattleActivity.writeStatus("Теряет " + (String)((int)(damage*(1-target.defence)) + " здоровья"));
    }
}
