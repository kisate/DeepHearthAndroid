package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.BattleActivity;
import com.example.dima.deephearth.FromIdea.Effects.Bleeding;
import com.example.dima.deephearth.FromIdea.HeroParams.AttackTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Probability;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 27.03.2017.
 */
public class CuttingStrike extends Skill  {
    public CuttingStrike(Unit owner) {
        super(owner);
        name = "Cutting strike";
        cost = 0;
        dmgMod = Scale.D;
        accuracyMod = 0.85;
        canBeUsedFrom[0] = true;
        canBeUsedFrom[1] = true;
        canBeUsedOn[0] = true;
        canBeUsedOn[1] = true;
        description = "Add target unit bleeding" + power +" for 3 turns";
        skillIco = R.drawable.skillico_cuttingstrike;
        attackType = AttackTypes.Melee;
        setup();
        effects.add(new Bleeding(null, (int)(2*effectMod), 2));
    }

    @Override
    public void action(Unit target) {
        super.action(target);
        damage = (int)(damage*(1-target.defence));
        target.modHealth(damage);
    }
}
