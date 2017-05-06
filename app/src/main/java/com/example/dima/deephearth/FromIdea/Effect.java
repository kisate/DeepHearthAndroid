package com.example.dima.deephearth.FromIdea;

import android.widget.TextView;

import com.example.dima.deephearth.BattleActivity;
import com.example.dima.deephearth.FromIdea.Effects.Bleeding;

import java.io.Serializable;
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

    public void addToTarget(Unit target) {
        try {
            target.effects.add(this.getClass().getDeclaredConstructor(Unit.class, Integer.class, Integer.class).newInstance(target, power, turns));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    public void apply(){
        if (turns == 0) {
            target.effects.remove(this);
        }
        else turns--;
    }

    public void setData(){
    }
}
