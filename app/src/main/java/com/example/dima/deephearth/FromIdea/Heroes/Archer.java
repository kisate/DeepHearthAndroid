package com.example.dima.deephearth.FromIdea.Heroes;

import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Team;

/**
 * Created by student3 on 12.04.17.
 */
public class Archer extends Hero {
    public Archer(int dexterity, int power, String name, Team team) {
        super(dexterity, power, name, team);
        description = "Наемный лучник, который однажды в своей жизни свернул куда-то не туда";
        heroClass = HeroClasses.Archer;
        hpScale = Scale.C;
        mpScale = Scale.B;
        dodgeScale = Scale.A;
        crtScale = Scale.A;
        speedScale = Scale.A;
        dmgScale = Scale.B;
        accScale = Scale.A;
        holyDef = 100;
        curseDef = 10;
        moveDef = 15;
        nmDef = 30;
        stunDef = 25;
        luck = 13;
        spriteId = R.drawable.mage;
    }
}
