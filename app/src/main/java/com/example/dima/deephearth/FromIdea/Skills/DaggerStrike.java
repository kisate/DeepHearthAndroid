package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.BattleActivity;
import com.example.dima.deephearth.FromIdea.HeroParams.AttackTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by student3 on 12.04.17.
 */
public class DaggerStrike extends Skill{
    public DaggerStrike(Unit owner) {
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
        skillIco = R.drawable.skillico_daggerstrike;
        attackType = AttackTypes.Melee;
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
        BattleActivity.writeStatus("Теряет " + (String)((int)(damage*(1-target.defence)) + " здоровья"));
    }
}
