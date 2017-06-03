package com.example.dima.deephearth.FromIdea.Effects;

import com.example.dima.deephearth.FromIdea.Effect;
import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 23.04.2017.
 */

public class Move extends Effect {
    public Move(Unit target) {
        super(target);
    }

    public Move(Unit target, int power) {
        this(target);
        this.power = power;
    }

    @Override
    public boolean apply() {
        return false;
    }

    @Override
    public Effect addToTarget(Unit target) {
        return null;
    }

    @Override
    public void setData() {
        super.setData();
        type = EffectTypes.Move;
        icoId = R.drawable.effectico_move;
        name = "Move";
    }
}
