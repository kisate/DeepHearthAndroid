package com.example.dima.deephearth.FromIdea.HeroParams;

import android.widget.TextView;

import com.example.dima.deephearth.BattleActivity;
import com.example.dima.deephearth.FromIdea.Effect;
import com.example.dima.deephearth.FromIdea.Probability;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Dima on 23.02.2017.
 */
public abstract class Skill implements Serializable {
    public int cost;
    public int power, accuracy, damage, clearDamage;
    public Scale dmgMod;
    public double accuracyMod;
    public String name, description;
    public Unit owner;
    public boolean[] canBeUsedFrom = {false, false, false, false};
    public boolean[] canBeUsedOn = {false, false, false, false};
    public int targetAmount = 1;
    public boolean friendly = false;
    public boolean onSelf = false;
    public AttackTypes attackType;
    public double bottom = 0.8, top = 1.2;
    public double effectMod = 1;
    public int skillIco = R.drawable.skillico;
    public HashMap<String, Object> animMap = new HashMap<>();
    public LinkedList<Effect> effects = new LinkedList<>();
    public boolean heals = false, moves = false;
    public Skill(Unit owner) {
        this.owner = owner;
    }

    public void use(Unit target){
            setup();
            if (new Probability((int)(owner.luck + accuracy),(int) (owner.luck + accuracy + target.luck + target.dodge)).check()) {
                action(target);
            }
            else BattleActivity.writeStatus(owner.name + " промахнулся");
            if (owner.mana <= 0) owner.manaEnd();
    }

    public void setup(){
        power = (int)(owner.damage*dmgMod.scale);
        accuracy = (int)(owner.accuracy*accuracyMod);
        effectMod = (100 + power)/100;
    }

    public void action(Unit target){
        setup();
        damage = (int)(power*bottom + power*(top-bottom)*Math.random());
        clearDamage = damage;
        if (owner.critical/100.0 > Math.random()) {
            double critMod = 1.5 + Math.random()*2;
            damage*=critMod;
        }
    }
    @Override
    public String toString() {
        return "Skill{" +
                "cost=" + cost +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


    void applyEffect(Unit target, Effect effect){

    }
}
