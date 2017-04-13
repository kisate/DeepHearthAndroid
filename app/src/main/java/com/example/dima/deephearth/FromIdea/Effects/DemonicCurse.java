package com.example.dima.deephearth.FromIdea.Effects;

import com.example.dima.deephearth.FromIdea.Effect;
import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.Unit;

/**
 * Created by Dima on 12.04.2017.
 */

public class DemonicCurse extends Effect {
    public DemonicCurse(Unit target) {
        super(target);
        type = EffectTypes.Demonic;
    }

    public DemonicCurse(Unit target, int power, int turns) {
        this(target);
        this.power = power;
        this.turns = turns;
    }

    @Override
    public void apply() {
        super.apply();
        target.modHealth(power);
    }
}
