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
    public UnitStat power, dexterity;
    public int  experience, level;
    public HeroClasses heroClass;
    public Inventory inventory = new Inventory(this);
    boolean initialized = false;

    public Hero(int dexterity, int power, String name,  Team team) {
        super();
        this.power = new UnitStat(power);
        this.dexterity = new UnitStat(dexterity);
        this.name = name;
        this.team = team;
        type = UnitTypes.Hero;
        level = 1;
        experience = 0;
    }

    public void manaEnd() {}

    public void countStats() {
        maxHealth.clearValue = 10 + (2*power.getValue() * hpScale.scale);
        maxMana.clearValue = 60 + 3* (power.getValue() * mpScale.scale);
        critical = new UnitStat((power.getValue() * crtScale.scale));
        dodge = new UnitStat((dexterity.getValue() * dodgeScale.scale));
        speed = new UnitStat((dexterity.getValue() * speedScale.scale));
        damage = new UnitStat((power.getValue()* dmgScale.scale));
        accuracy = new UnitStat((power.getValue()*accScale.scale));
        if (!initialized || health.getValue() > maxHealth.getValue())
        health.clearValue = maxHealth.getValue();
        if (!initialized || mana.getValue() > maxMana.getValue())
        mana.clearValue = maxMana.getValue();
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
