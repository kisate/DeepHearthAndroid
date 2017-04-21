package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.BattleActivity;
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
        description = "Hit target dealing " + power +" damage";
        skillIco = R.drawable.skillico;
        setup();
    }
    @Override
    public void action(Unit target) {
        BattleActivity.writeStatus("Stroke " + target.name + " dealing " + power +" damage");
        target.modHealth(power);
    }
}
