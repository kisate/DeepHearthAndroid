package com.example.dima.deephearth.FromIdea;

import android.widget.TextView;

import com.example.dima.deephearth.BattleActivity;

import java.io.Serializable;

/**
 * Created by student3 on 09.03.17.
 */
public abstract class Effect implements Serializable {
    public EffectTypes type;
    public String name, description;
    public Unit target;
    public int turns, power;
    public Effect(Unit target) {
        this.target = target;
    }

    public void apply(){
        if (turns == 0) {
            target.effects.remove(this);
        }
        else turns--;
    }
}
