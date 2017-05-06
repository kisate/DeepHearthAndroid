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
        type = EffectTypes.Stun;
        icoId = R.drawable.effectico_stun;
    }
}
