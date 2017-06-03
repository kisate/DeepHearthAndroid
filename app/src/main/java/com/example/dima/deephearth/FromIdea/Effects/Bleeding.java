package com.example.dima.deephearth.FromIdea.Effects;

import android.util.Log;

import com.example.dima.deephearth.BattleActivity;
import com.example.dima.deephearth.FromIdea.Effect;
import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 20.03.2017.
 */
public class Bleeding extends Effect {

    public Bleeding(Unit target, int power, int turns) {
        super(target, power, turns);
    }

    @Override
    public boolean apply() {
        boolean res = super.apply();
        target.modHealth(power);
        Log.d("Debug", "applied");
        return res;
    }

    @Override
    public void setData() {
        super.setData();
        type = EffectTypes.Nonmagic;
        icoId = R.drawable.effectico_bleeding;
        name = "Bleeding";
    }
}
