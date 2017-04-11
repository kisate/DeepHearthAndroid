package com.example.dima.deephearth.FromIdea.Heroes;

import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Skills.SwordStrike;
import com.example.dima.deephearth.FromIdea.Team;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 23.02.2017.
 */
public class Swordsman extends Hero {

    public Swordsman(int dexterity, int power, String name, Team team) {
        super(dexterity, power, name, team);
        description = "Strikes enemies with his sword";
        heroClass = HeroClasses.Swordsman;
        hpScale = Scale.A;
        mpScale = Scale.D;
        dodgeScale = Scale.B;
        crtScale = Scale.A;
        speedScale = Scale.B;
        dmgScale = Scale.B;
        accScale = Scale.B;
        holyDef = 80;
        curseDef = 40;
        moveDef = 50;
        nmDef = 60;
        stunDef = 50;
        luck = 10;
        spriteId = R.drawable.knight;
        countStats();
    }
}
