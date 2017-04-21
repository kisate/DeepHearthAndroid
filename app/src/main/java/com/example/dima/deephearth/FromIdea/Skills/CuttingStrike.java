package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.BattleActivity;
import com.example.dima.deephearth.FromIdea.Effects.Bleeding;
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
        description = "Add target unit bleeding" + power +" for 3 turns";
        skillIco = R.drawable.skillico;
        setup();
    }

    @Override
    public void action(Unit target) {
        super.action(target);
        if (new Probability(100 - target.nmDef, 100).check()) {
            target.effects.add(new Bleeding(target, 3, power));
            BattleActivity.writeStatus(owner.name + " used Cutting Strike on " + target.name + " giving " + power + " bleeding for 3 turns");
        }
        else
        {
            BattleActivity.writeStatus("resist");
        }
    }
}
