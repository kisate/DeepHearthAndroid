package com.example.dima.deephearth.FromIdea;

import com.example.dima.deephearth.R;

/**
 * Created by Dima on 19.03.2017.
 */
public enum EffectTypes {
    Move(R.drawable.effectico_move), Stun(R.drawable.effectico_stun), Nonmagic(R.drawable.effectico_fire), Holy(R.drawable.effectico_holy), Curse(R.drawable.effectico_curse), Other(R.drawable.effectico_fire), Demonic(R.drawable.effectico_demonic), Buff(R.drawable.effectico_fire);
    public int icoId;

    EffectTypes(int icoId) {
        this.icoId = icoId;
    }
}
