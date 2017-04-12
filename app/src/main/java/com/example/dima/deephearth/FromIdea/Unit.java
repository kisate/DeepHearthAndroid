package com.example.dima.deephearth.FromIdea;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.example.dima.deephearth.FromIdea.HeroParams.Skill;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by Dima on 23.02.2017.
 */
public abstract class Unit implements Serializable{
    public int maxHealth, maxMana;
    public int stunDef, moveDef, curseDef, holyDef, nmDef;
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
    public void modHealth (int amount) {
        health-=amount;
        if ((health <= 0) && !isDead) die();
    }

    public void manaEnd(){
        System.out.println(name + " is out of mana");
    }

    public void die() {
        System.out.println(name + " died");
        isDead = true;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "health=" + health +
                ", mana=" + mana +
                ", description='" + description + '\'' +
                '}';
    }
}
