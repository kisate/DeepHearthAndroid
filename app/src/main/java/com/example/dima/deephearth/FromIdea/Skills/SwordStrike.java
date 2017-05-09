package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.BattleActivity;
import com.example.dima.deephearth.FromIdea.HeroParams.AttackTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 23.02.2017.
 */
public class SwordStrike extends Skill {
    public SwordStrike(Unit owner) {
        super(owner);
        name = "SwordStrike";
        dmgMod = Scale.B;
        accuracyMod = 0.9;
        cost = 0;
        canBeUsedFrom[0] = true;
        canBeUsedFrom[1] = true;
        canBeUsedOn[0] = true;
        canBeUsedOn[1] = true;
        description = "Hit target dealing " + power +" damage";
        skillIco = R.drawable.skillico_sword_strike;
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
    }
}
