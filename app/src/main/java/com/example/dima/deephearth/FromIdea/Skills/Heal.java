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
public class Heal extends Skill{
    public Heal(Unit owner) {
        super(owner);
        name = "Heal";
        cost = 10;
        dmgMod = Scale.C;
        accuracyMod = 1;
        friendly = true;
        onSelf = true;
        canBeUsedFrom[0] = true;
        canBeUsedFrom[1] = true;
        canBeUsedFrom[2] = true;
        canBeUsedFrom[3] = true;
        description = "Add target unit " + power +" health points " + owner.damage;
        skillIco = R.drawable.skillico_heal;
        attackType = AttackTypes.Ranged;
        setup();
        animMap.put("drawable", R.drawable.heal_anim);
        heals = true;
    }

    @Override
    public void action(Unit target) {
        setup();
        damage = (int) (power*bottom + power*(top-bottom)*Math.random());
        if ((target.maxHealth.getValue() - target.health.clearValue) < damage) target.modHealth((target.health.clearValue-target.maxHealth.getValue()));
        else target.modHealth(-damage);
    }
}
