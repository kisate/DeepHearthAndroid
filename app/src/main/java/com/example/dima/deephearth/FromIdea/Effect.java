package com.example.dima.deephearth.FromIdea;

import android.util.Log;
import android.widget.TextView;

import com.example.dima.deephearth.BattleActivity;
import com.example.dima.deephearth.FromIdea.Effects.Bleeding;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by student3 on 09.03.17.
 */
public abstract class Effect implements Serializable {
    public EffectTypes type;
    public String name, description;
    public Unit target;
    public int turns, power;
    public int icoId;
    public int id = 0;

    public Effect(Unit target) {
        this.target = target;setData();
    }

    public Effect(Unit target, int power, int turns) {
        this(target);
        this.turns = turns;
        this.power = power;
        setData();
    }

    public void update(Unit target, int power, int turns) {
        this.target = target;
        this.power = power;
        this.turns = turns;
    }

    public Effect addToTarget(Unit target) {
        Effect ef = new Effect(target) {
            @Override
            public void apply() {
                super.apply();
                Log.wtf("Debug", "wtf");
            }
        };
        try {

            Constructor<? extends Effect> constructor = this.getClass().getConstructor(Unit.class, int.class, int.class);
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
        return ef;
    }


    public void apply(){
        if (turns == 0) {
            remove();
        }
        else turns--;
    }

    public void setData(){
    }

    public void remove() {
        target.effects.remove(this);
    }
}
