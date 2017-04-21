package com.example.dima.deephearth.FromIdea.Effects;

import com.example.dima.deephearth.BattleActivity;
import com.example.dima.deephearth.FromIdea.Effect;
import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.Unit;

/**
 * Created by Dima on 20.03.2017.
 */
public class Bleeding extends Effect {

    public Bleeding(Unit target) {
        super(target);
        type = EffectTypes.Nonmagic;
    }

    public Bleeding(Unit target, int power, int turns){
        this(target);
        this.turns = turns;
        this.power = power;
    }

    @Override
    public void apply() {
        super.apply();
        target.modHealth(power);
        BattleActivity.writeStatus(target.name + " has lost " + power + " health from bleeding");
    }
}
