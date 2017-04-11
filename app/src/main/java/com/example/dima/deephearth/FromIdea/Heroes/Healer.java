package com.example.dima.deephearth.FromIdea.Heroes;

import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Skills.Heal;
import com.example.dima.deephearth.FromIdea.Team;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 23.02.2017.
 */
public class Healer extends Hero{

    public Healer(int dexterity, int power, String name, Team team) {
        super(dexterity, power, name, team);
        description = "Heals allies";
        heroClass = HeroClasses.Healer;
        hpScale = Scale.C;
        mpScale = Scale.S;
        dodgeScale = Scale.B;
        crtScale = Scale.A;
        speedScale = Scale.C;
        dmgScale = Scale.C;
        accScale = Scale.B;
        holyDef = 100;
        curseDef = 70;
        moveDef = 30;
        nmDef = 20;
        stunDef = 30;
        luck = 10;
        spriteId = R.drawable.mage;

        countStats();
    }
}
