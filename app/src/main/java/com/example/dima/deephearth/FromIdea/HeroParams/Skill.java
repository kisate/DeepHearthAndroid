package com.example.dima.deephearth.FromIdea.HeroParams;

import android.widget.TextView;

import com.example.dima.deephearth.FromIdea.Probability;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Dima on 23.02.2017.
 */
public abstract class Skill implements Serializable {
    public int cost;
    public int power, accuracy, damage;
    public Scale dmgMod;
    public double accuracyMod;
    public String name, description;
    public Unit owner;
    public boolean[] canBeUsedFrom = {false, false, false, false};
    public boolean[] canBeUsedOn = {false, false, false, false};
    public int targetAmount = 1;
    public boolean friendly = false;
    public boolean onSelf = false;
    public double bottom = 0.8, top = 1.2;
    public double effectMod = 1;
    public int skillIco;
    public TextView view;
    public Skill(Unit owner) {
        this.owner = owner;
    }

    public boolean use(Unit target){

        if (owner.mana < cost) {view.setText("Не хватает маны, выберите другой навык"); return false;}
        else{
            setup();
            owner.mana-=cost;
            damage = (int)(power*bottom + power*(top-bottom)*Math.random());
            effectMod = (100 + power)/100;
            if (new Probability(owner.luck + accuracy, owner.luck + accuracy + target.luck + target.dodge).check()) {
                action(target);
            }
            else view.setText(view.getText() + "\n" + owner.name + " промахнулся");
            if (owner.mana <= 0) owner.manaEnd();
            return true;
        }
    }

    public void setup(){
        power = (int)(owner.damage*dmgMod.scale);
        accuracy = (int)(owner.accuracy*accuracyMod);
    }

    public void action(Unit target){
        showUse(target);
    }
    @Override
    public String toString() {
        return "Skill{" +
                "cost=" + cost +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public void showUse(Unit target) {
    }
}
