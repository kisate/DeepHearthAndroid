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

public class UndeadHealer extends Hero {
    public UndeadHealer(int dexterity, int power, String name, Team team) {
        super(dexterity, power, name, team);

        description = "Heals allies. If it is applicable to undead.";
        heroClass = HeroClasses.Healer;
        nature = NatureTypes.Undead;
        hpScale = Scale.C;
        mpScale = Scale.S;
        dodgeScale = Scale.B;
        crtScale = Scale.A;
        speedScale = Scale.C;
        dmgScale = Scale.C;
        accScale = Scale.B;
        effectDefs.put(EffectTypes.Holy, new UnitStat(30.0));
        effectDefs.put(EffectTypes.Curse, new UnitStat(100.0));
        effectDefs.put(EffectTypes.Move, new UnitStat(0.0));
        effectDefs.put(EffectTypes.Nonmagic, new UnitStat(20.0));
        effectDefs.put(EffectTypes.Demonic, new UnitStat(5.0));
        effectDefs.put(EffectTypes.Stun, new UnitStat(30.0));
        luck = new UnitStat(10);
        defence = new UnitStat(0);
        spriteIds.put("idle", R.drawable.mage_undead);
        spriteIds.put("prepairing", R.drawable.mage_undead_prepairing);
        spriteIds.put("attack", R.drawable.mage_undead_attack);
        spriteIds.put("dodge", R.drawable.mage_undead);
        spriteIds.put("hit", R.drawable.mage_undead);

        icoId = R.drawable.mage_undead_ico;

        countStats();
    }
}
