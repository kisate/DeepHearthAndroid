package com.example.dima.deephearth.FromIdea.Heroes.Bosses;

import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.HeroParams.NatureTypes;
import com.example.dima.deephearth.FromIdea.Heroes.HeroClasses;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Team;
import com.example.dima.deephearth.FromIdea.UnitStat;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 29.05.2017.
 */

public class Lich extends Hero {
    public Lich(int dexterity, int power, String name, Team team) {
        super(dexterity, power, name, team);
        description = "One of the undead dukes. Strong enemy.";
        heroClass = HeroClasses.Boss;
        hpScale = Scale.S;
        mpScale = Scale.S;
        dodgeScale = Scale.B;
        crtScale = Scale.C;
        speedScale = Scale.C;
        dmgScale = Scale.C;
        accScale = Scale.B;
        effectDefs.put(EffectTypes.Holy, new UnitStat(60.0));
        effectDefs.put(EffectTypes.Curse, new UnitStat(150.0));
        effectDefs.put(EffectTypes.Move, new UnitStat(80.0));
        effectDefs.put(EffectTypes.Nonmagic, new UnitStat(40.0));
        effectDefs.put(EffectTypes.Demonic, new UnitStat(40.0));
        effectDefs.put(EffectTypes.Stun, new UnitStat(90.0));
        luck = new UnitStat(13);
        defence = new UnitStat(0.1);
        nature = NatureTypes.Alive;
        spriteIds.put("idle", R.drawable.lich_idle);
        spriteIds.put("prepairing", R.drawable.lich_prepairing);
        spriteIds.put("attack", R.drawable.lich_attack);
        spriteIds.put("dodge", R.drawable.lich_idle);
        spriteIds.put("hit", R.drawable.lich_idle);
        surviveChance = new UnitStat(0);
        icoId = R.drawable.lich_ico;
        countStats();
    }

    @Override
    public void countStats() {
        super.countStats();
        maxHealth.clearValue = 300;
        health.clearValue = 300;
    }
}
