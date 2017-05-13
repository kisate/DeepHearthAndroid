package com.example.dima.deephearth.FromIdea;

import com.example.dima.deephearth.FromIdea.HeroParams.Feature;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Heroes.HeroClasses;

import java.util.LinkedList;

/**
 * Created by Dima on 23.02.2017.
 */
public abstract class Hero extends Unit implements Hireable{
    private double prDexterity, prPower;
    public Scale hpScale, crtScale, mpScale, speedScale, dodgeScale, dmgScale, accScale;
    public double power, dexterity, experience, level;
    public HeroClasses heroClass;
    public Inventory inventory = new Inventory(this);
    boolean initialized = false;

    public Hero(int dexterity, int power, String name,  Team team) {
        super();
        this.prDexterity = dexterity;
        this.prPower = power;
        this.power = power;
        this.dexterity = dexterity;
        this.name = name;
        this.team = team;
        type = UnitTypes.Hero;
        level = 1;
        experience = 0;
    }

    public void manaEnd() {}

    public void countStats() {
        maxHealth = 10 + (int)(2*power * hpScale.scale);
        maxMana = 60 + 3*(int)(power * mpScale.scale);
        critical = (int)(power * crtScale.scale);
        dodge = (int)(dexterity * dodgeScale.scale);
        speed = (int)(dexterity * speedScale.scale);
        damage = (int)(power* dmgScale.scale);
        accuracy = (int)(power*accScale.scale);
        if (!initialized || health > maxHealth)
        health = maxHealth;
        if (!initialized || mana > maxMana)
        mana = maxMana;
        initialized = true;
    }

    public LinkedList<Feature> features = new LinkedList<Feature>();
    public LinkedList<Item> items = new LinkedList<Item>();


    @Override
    public String toString() {
        return heroClass + "{" +
                "name=" + name +
                ", description=" + description +
                ", health=" + health +
                ", experience=" + experience +
                ", level=" + level +
                ", damage=" + damage +
                ", defence=" + defence +
                ", dexterity=" + dexterity +
                ", power=" + power +
                ", heroClass=" + heroClass +
                ", features=" + features +
                ", items=" + items +
                ", skills=" + skills +
                '}';
    }

    @Override
    public String getName() {
        return heroClass + " " + name;
    }
}
