package com.example.dima.deephearth.FromIdea.Effects;

import android.util.Log;

import com.example.dima.deephearth.FromIdea.Effect;
import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Dima on 09.05.2017.
 */

public class Buff extends Effect {
    public boolean applied = false;

    public Buff(Unit target, int power, int turns) {
        super(target, power, turns);
        type = EffectTypes.Buff;
        icoId = R.drawable.effectico_buff;
    }

    public Buff(Unit target) {
        super(target);
    }

    @Override
    public boolean apply() {
        return true;
    }

    @Override
    public void remove() {
        super.remove();
        applied = false;
    }

    @Override
    public Effect addToTarget(Unit target) {
        Effect ef = new Effect(target) {
            @Override
            public boolean apply() {
                boolean res = super.apply();
                Log.wtf("Debug", "wtf");
                return res;
            }
        };
        try {

            Constructor<? extends Buff> constructor = this.getClass().getConstructor(Unit.class, int.class, int.class);
            ef = constructor.newInstance(target, power, turns);
            target.effects.add(ef);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (!ef.apply()) target.effects.remove(ef);

        return ef;
    }
}
