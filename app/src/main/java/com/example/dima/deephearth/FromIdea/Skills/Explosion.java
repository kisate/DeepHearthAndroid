package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.FromIdea.Effects.Bleeding;
import com.example.dima.deephearth.FromIdea.Effects.Fire;
import com.example.dima.deephearth.FromIdea.Effects.Stun;
import com.example.dima.deephearth.FromIdea.HeroParams.AttackTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 29.05.2017.
 */

public class Explosion extends Skill {
    public Explosion(Unit owner) {
        super(owner);
        name = "Explosion";
        cost = 15;
        dmgMod = Scale.B;
        accuracyMod = 0.85;
        canBeUsedFrom[2] = true;
        canBeUsedFrom[3] = true;
        canBeUsedOn[1] = true;
        canBeUsedOn[2] = true;
        targetAmount = 2;
        description = "Create an explosion in the middle of the opponent's formation";
        skillIco = R.drawable.skillico_explosion;
        attackType = AttackTypes.Ranged;
        setup();
        effects.add(new Fire(null, (int)(2*effectMod), 2));
        effects.add(new Stun(null, 1));
        animMap.put("drawable", R.drawable.anim_explosion);
    }

    @Override
    public void action(Unit target) {
        super.action(target);
        damage = (int)(damage*(1-target.defence));
        target.modHealth(damage);
    }
}
