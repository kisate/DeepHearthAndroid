package com.example.dima.deephearth.FromIdea.Heroes;

import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.HeroParams.NatureTypes;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Team;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 21.05.2017.
 */

public class Gunner extends Hero {
    public Gunner(int dexterity, int power, String name, Team team) {
        super(dexterity, power, name, team);

        description = "A dwarf with a gun. Nothing special. Explosive.";
        heroClass = HeroClasses.Gunner;
        hpScale = Scale.B;
        mpScale = Scale.B;
        dodgeScale = Scale.B;
        crtScale = Scale.A;
        speedScale = Scale.B;
        dmgScale = Scale.A;
        accScale = Scale.A;
        effectDefs.put(EffectTypes.Holy, 100.0);
        effectDefs.put(EffectTypes.Curse, 10.0);
        effectDefs.put(EffectTypes.Move, 30.0);
        effectDefs.put(EffectTypes.Nonmagic, 30.0);
        effectDefs.put(EffectTypes.Demonic, 5.0);
        effectDefs.put(EffectTypes.Stun, 50.0);
        luck = 13;
        defence = 0.1;
        nature = NatureTypes.Alive;
        spriteIds.put("idle", R.drawable.gunner_idle);
        spriteIds.put("prepairing", R.drawable.gunner_prepairing);
        spriteIds.put("attack", R.drawable.gunner_attack);
        spriteIds.put("dodge", R.drawable.gunner_idle);
        spriteIds.put("hit", R.drawable.gunner_idle);

        icoId = R.drawable.gunner_ico;
        countStats();
    }
}
