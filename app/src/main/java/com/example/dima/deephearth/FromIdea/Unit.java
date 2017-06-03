package com.example.dima.deephearth.FromIdea;

import com.example.dima.deephearth.BattleActivity;
import com.example.dima.deephearth.FromIdea.HeroParams.NatureTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.UnitButton;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import static com.example.dima.deephearth.FromIdea.EffectTypes.Buff;

/**
 * Created by Dima on 23.02.2017.
 */
public abstract class Unit implements Serializable{
    public UnitStat maxHealth = new UnitStat(0), maxMana = new UnitStat(0);
    public HashMap<EffectTypes, UnitStat> effectDefs = new HashMap<>();
    public HashMap<String, Integer> spriteIds = new HashMap<>();
    public UnitStat health = new UnitStat(0), mana= new UnitStat(0), damage= new UnitStat(0), accuracy= new UnitStat(0);
    public UnitStat luck= new UnitStat(0), dodge= new UnitStat(0), critical = new UnitStat(0);
    public UnitStat speed= new UnitStat(0);
    public UnitStat defence= new UnitStat(0);
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
    public boolean stunned = false;
    public boolean nearDeath = false;
    public UnitStat surviveChance = new UnitStat(0.3);
    public int position;
    public UnitButton button;
    public void modHealth (double amount) {
        if (!nearDeath || (nearDeath && amount <= 0)) health.clearValue-=amount;
        else {
            if (surviveChance.getValue() < Math.random() && amount > 0) isDead = true;
        }
    }

    public Unit(){
        effectDefs.put(EffectTypes.Stun, new UnitStat(0.0));
        effectDefs.put(EffectTypes.Move, new UnitStat(0.0));
        effectDefs.put(EffectTypes.Demonic, new UnitStat(0.0));
        effectDefs.put(EffectTypes.Curse, new UnitStat(0.0));
        effectDefs.put(EffectTypes.Holy, new UnitStat(0.0));
        effectDefs.put(EffectTypes.Nonmagic, new UnitStat(0.0));
        effectDefs.put(EffectTypes.Other, new UnitStat(0.0));
        effectDefs.put(Buff, new UnitStat(0.0));
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
        for (Iterator<Effect> iterator = effects.iterator(); iterator.hasNext();) {
            Effect effect = iterator.next();
            if (!effect.apply()) iterator.remove();
        }
    }
}
