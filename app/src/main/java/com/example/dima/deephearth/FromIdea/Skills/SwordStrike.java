package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;

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
        setup();
    }
    @Override
    public void use(Unit target) {
        super.use(target);
        System.out.println("Stroke " + target.name + " dealing " + power +" damage");
        target.modHealth(power);
    }
}
