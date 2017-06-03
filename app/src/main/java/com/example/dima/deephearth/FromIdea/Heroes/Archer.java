package com.example.dima.deephearth.FromIdea.Heroes;

import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.HeroParams.NatureTypes;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Team;
import com.example.dima.deephearth.FromIdea.UnitStat;
import com.example.dima.deephearth.R;

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
        effectDefs.put(EffectTypes.Holy, new UnitStat(100.0));
        effectDefs.put(EffectTypes.Curse, new UnitStat(10.0));
        effectDefs.put(EffectTypes.Move, new UnitStat(15.0));
        effectDefs.put(EffectTypes.Nonmagic, new UnitStat(30.0));
        effectDefs.put(EffectTypes.Demonic, new UnitStat(5.0));
        effectDefs.put(EffectTypes.Stun, new UnitStat(25.0));
        defence = new UnitStat(0);
        luck = new UnitStat(13);
        nature = NatureTypes.Alive;
        spriteIds.put("idle", R.drawable.archer);
        spriteIds.put("prepairing", R.drawable.archer_prepairing);
        spriteIds.put("attack", R.drawable.archer_attack);
        spriteIds.put("dodge", R.drawable.archer);
        spriteIds.put("hit", R.drawable.archer);

        icoId = R.drawable.archer_ico;
        countStats();
    }
}
