package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;

/**
 * Created by Dima on 23.02.2017.
 */
public class Heal extends Skill{
    public Heal(Unit owner) {
        super(owner);
        name = "Heal";
        cost = 10;
        dmgMod = Scale.C;
        accuracyMod = 1;
        description = "Add target unit " + power +" health points " + owner.damage;
        setup();
    }

    @Override
    public void use(Unit target) {
        super.use(target);
        System.out.println("Healed " + target.name + " giving" + power + "hp");
        target.modHealth(-power);
    }
}
