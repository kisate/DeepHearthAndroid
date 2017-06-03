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

public class UndeadKnight extends Hero {
    public UndeadKnight(int dexterity, int power, String name, Team team) {
        super(dexterity, power, name, team);

        description = "Dead knight. There are tons of them here nowadays";
        heroClass = HeroClasses.Swordsman;
        nature = NatureTypes.Undead;
        hpScale = Scale.A;
        mpScale = Scale.D;
        dodgeScale = Scale.B;
        crtScale = Scale.A;
        speedScale = Scale.S;
        dmgScale = Scale.B;
        accScale = Scale.B;
        effectDefs.put(EffectTypes.Holy, new UnitStat(20.0));
        effectDefs.put(EffectTypes.Curse, new UnitStat(100.0));
        effectDefs.put(EffectTypes.Move, new UnitStat(0.0));
        effectDefs.put(EffectTypes.Nonmagic, new UnitStat(60.0));
        effectDefs.put(EffectTypes.Demonic, new UnitStat(5.0));
        effectDefs.put(EffectTypes.Stun, new UnitStat(50.0));
        luck = new UnitStat(10);
        defence = new UnitStat(0.18);

        spriteIds.put("idle", R.drawable.knight_undead_idle);
        spriteIds.put("prepairing", R.drawable.knight_undead_prepairing);
        spriteIds.put("attack", R.drawable.knight_undead_attack);
        spriteIds.put("dodge", R.drawable.knight_undead_idle);
        spriteIds.put("hit", R.drawable.knight_undead_idle);

        icoId = R.drawable.knight_undead_ico;
        countStats();
    }
}
