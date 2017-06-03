package com.example.dima.deephearth.FromIdea.Effects;

import com.example.dima.deephearth.FromIdea.Effect;
import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 23.04.2017.
 */

public class Curse extends Effect {

    public Curse(Unit target, int power, int turns) {
        super(target, power, turns);
    }

    @Override
    public boolean apply() {
        boolean res = super.apply();
        target.modHealth(power);
        return res;
    }

    @Override
    public void setData() {
        super.setData();
        type = EffectTypes.Curse;
        icoId = R.drawable.effectico_curse;
        name = "Curse";
    }
}
