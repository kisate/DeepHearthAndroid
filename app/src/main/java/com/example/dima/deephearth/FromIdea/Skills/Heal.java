package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.BattleActivity;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

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
        skillIco = R.drawable.skillico;
        setup();
    }

    @Override
    public void use(Unit target) {
        BattleActivity.writeStatus("Healed " + target.name + " giving" + power + "hp");

        if ((target.maxHealth - target.health) < power) target.modHealth(target.health-target.maxHealth);

        else target.modHealth(-power);
    }
}
