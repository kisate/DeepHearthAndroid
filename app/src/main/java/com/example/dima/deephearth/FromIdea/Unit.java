package com.example.dima.deephearth.FromIdea;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.example.dima.deephearth.BattleActivity;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by Dima on 23.02.2017.
 */
public abstract class Unit implements Serializable{
    public int maxHealth, maxMana;
    public int stunDef, moveDef, curseDef, holyDef, nmDef, demDef;
    public int health, mana, damage, accuracy;
    public int luck, dodge, critical = 0;
    public int speed;
    public double defence, prdefence;
    public String description;
    public UnitTypes type;
    public LinkedList<Skill> skills = new LinkedList<Skill>();
    public LinkedList<Effect> effects = new LinkedList<Effect>();
    public String name;
    public boolean isDead = false, manaEnded = false;
    public Team team;
    public int spriteId;
    public int icoId;
    public int moves = 1;
    public void modHealth (int amount) {
        health-=amount;
    }

    public void manaEnd(){
        BattleActivity.writeStatus(name + " is out of mana");
    }


    @Override
    public String toString() {
        return "Unit{" +
                "health=" + health +
                ", mana=" + mana +
                ", description='" + description + '\'' +
                '}';
    }

    public void applyEffects()
    {
        for (Effect effect : effects) {
            effect.apply();
        }
    }
}
