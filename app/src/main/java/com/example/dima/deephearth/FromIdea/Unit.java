package com.example.dima.deephearth.FromIdea;

import com.example.dima.deephearth.BattleActivity;
import com.example.dima.deephearth.FromIdea.HeroParams.NatureTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

import static com.example.dima.deephearth.FromIdea.EffectTypes.Buff;

/**
 * Created by Dima on 23.02.2017.
 */
public abstract class Unit implements Serializable{
    public double maxHealth, maxMana;
    public HashMap<EffectTypes, Double> effectDefs = new HashMap<>();
    public HashMap<String, Integer> spriteIds = new HashMap<>();
    public double health, mana, damage, accuracy;
    public double luck, dodge, critical = 0;
    public double speed;
    public double defence, prdefence;
    public String description;
    public UnitTypes type;
    public LinkedList<Skill> skills = new LinkedList<Skill>();
    public LinkedList<Effect> effects = new LinkedList<Effect>();
    public String name;
    public boolean isDead = false, manaEnded = false;
    public Team team;
    public int icoId;
    public int moves = 1, maxMoves = 1;
    public NatureTypes nature;
    public int position;
    public void modHealth (int amount) {
        health-=amount;
    }

    public Unit(){
        effectDefs.put(EffectTypes.Stun, 0.0);
        effectDefs.put(EffectTypes.Move, 0.0);
        effectDefs.put(EffectTypes.Demonic, 0.0);
        effectDefs.put(EffectTypes.Curse, 0.0);
        effectDefs.put(EffectTypes.Holy, 0.0);
        effectDefs.put(EffectTypes.Nonmagic, 0.0);
        effectDefs.put(EffectTypes.Other, 0.0);
        effectDefs.put(Buff, 0.0);
    }

    public void manaEnd(){
        BattleActivity.writeStatus(name + " is out of mana");
    }

    public String getName(){
        return name;
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
