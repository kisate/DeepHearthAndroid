package com.example.dima.deephearth.FromIdea.Effects;

import android.util.Log;

import com.example.dima.deephearth.FromIdea.Effect;
import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.Unit;

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
    }

    public Buff(Unit target) {
        super(target);
    }

    @Override
    public void apply() {
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
            public void apply() {
                super.apply();
                Log.wtf("Debug", "wtf");
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
        ef.apply();

        return ef;
    }
}
