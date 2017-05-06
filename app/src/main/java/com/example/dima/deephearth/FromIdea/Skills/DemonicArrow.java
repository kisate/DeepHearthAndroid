package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.FromIdea.Effects.DemonicCurse;
import com.example.dima.deephearth.FromIdea.HeroParams.AttackTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Probability;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 12.04.2017.
 */

public class DemonicArrow extends Skill {
    public DemonicArrow(Unit owner) {
        super(owner);
        name = "Демоническая стрела";
        dmgMod = Scale.B;
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
        description = "Выпустить стрелу, пропитанную кровью демона.";
        skillIco = R.drawable.skillico_demonicarrow;
        attackType = AttackTypes.Ranged;
        setup();
        effects.add(new DemonicCurse(null, (int)(1*effectMod), 5));
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
