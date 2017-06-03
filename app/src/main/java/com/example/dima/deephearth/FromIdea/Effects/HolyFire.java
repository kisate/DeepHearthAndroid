package com.example.dima.deephearth.FromIdea.Effects;

import com.example.dima.deephearth.FromIdea.Effect;
import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 23.04.2017.
 */

public class HolyFire extends Effect {
    public HolyFire(Unit target, int power, int turns) {
        super(target, power, turns);
    }

    @Override
    public boolean apply() {
        boolean res = super.apply();
        target.modHealth(power);
        target.mana.clearValue--;
        return res;
    }

    @Override
    public void setData() {
        super.setData();
        type = EffectTypes.Holy;
        icoId = R.drawable.effectico_holy;
        name = "Holy fire";
    }
}
