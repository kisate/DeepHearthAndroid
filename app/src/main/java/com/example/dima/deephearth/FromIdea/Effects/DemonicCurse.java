package com.example.dima.deephearth.FromIdea.Effects;

import com.example.dima.deephearth.FromIdea.Effect;
import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 12.04.2017.
 */

public class DemonicCurse extends Effect {


    public DemonicCurse(Unit target, int power, int turns) {
        super(target, power, turns);
    }

    @Override
    public void apply() {
        super.apply();
        target.modHealth(power);
    }

    @Override
    public void setData() {
        super.setData();
        type = EffectTypes.Demonic;
        icoId = R.drawable.effectico_demonic;
        name = "Demonic curse";
    }
}
