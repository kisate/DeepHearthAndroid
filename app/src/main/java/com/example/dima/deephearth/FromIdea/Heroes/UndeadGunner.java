package com.example.dima.deephearth.FromIdea.Heroes;

import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.HeroParams.NatureTypes;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Team;
import com.example.dima.deephearth.FromIdea.UnitStat;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 28.05.2017.
 */

public class UndeadGunner extends Hero{
    public UndeadGunner(int dexterity, int power, String name, Team team) {
        super(dexterity, power, name, team);

        description = "A dwarf with a gun. Nothing special. Explosive. A bit bonny.";
        heroClass = HeroClasses.Gunner;
        hpScale = Scale.B;
        mpScale = Scale.B;
        dodgeScale = Scale.B;
        crtScale = Scale.A;
        speedScale = Scale.B;
        dmgScale = Scale.A;
        accScale = Scale.A;
        effectDefs.put(EffectTypes.Holy, new UnitStat(10.0));
        effectDefs.put(EffectTypes.Curse, new UnitStat(100.0));
        effectDefs.put(EffectTypes.Move, new UnitStat(30.0));
        effectDefs.put(EffectTypes.Nonmagic, new UnitStat(30.0));
        effectDefs.put(EffectTypes.Demonic, new UnitStat(5.0));
        effectDefs.put(EffectTypes.Stun, new UnitStat(50.0));
        luck = new UnitStat(13);
        defence = new UnitStat(0.1);
        nature = NatureTypes.Undead;
        spriteIds.put("idle", R.drawable.gunner_undead_idle);
        spriteIds.put("prepairing", R.drawable.gunner_undead_prepairing);
        spriteIds.put("attack", R.drawable.gunner_undead_attack);
        spriteIds.put("dodge", R.drawable.gunner_undead_idle);
        spriteIds.put("hit", R.drawable.gunner_undead_idle);
        icoId = R.drawable.gunner_undead_ico;
        countStats();
    }
}
