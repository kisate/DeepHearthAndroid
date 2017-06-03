package com.example.dima.deephearth.FromIdea.Effects;

import com.example.dima.deephearth.FromIdea.Effect;
import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 23.04.2017.
 */

public class Stun extends Effect {
    public Stun(Unit target) {
        super(target);
    }

    public Stun(Unit target, int turns) {
        this(target);
        this.turns = turns;
    }

    @Override
    public boolean apply() {
        boolean res = super.apply();

        target.stunned = true;

        return res;
    }

    public Stun(Unit target, int power, int turns) {
        super(target, power, turns);
    }

    @Override
    public void remove() {
        super.remove();
        target.stunned = false;
    }

    @Override
    public void setData() {
        super.setData();
        name = "Stun";
        type = EffectTypes.Stun;
        icoId = R.drawable.effectico_stun;
    }

    @Override
    public Effect addToTarget(Unit target) {
        target.stunned = true;
        return super.addToTarget(target);
    }
}
