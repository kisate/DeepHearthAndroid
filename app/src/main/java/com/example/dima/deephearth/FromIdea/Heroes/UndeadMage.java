package com.example.dima.deephearth.FromIdea.Heroes;

import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.HeroParams.NatureTypes;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Team;
import com.example.dima.deephearth.FromIdea.UnitStat;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 29.05.2017.
 */

public class UndeadMage extends Hero {
    public UndeadMage(int dexterity, int power, String name, Team team) {
        super(dexterity, power, name, team);
        description = "Powerful mage, that can change everything on the battlefield with one blow.";
        heroClass = HeroClasses.Mage;
        hpScale = Scale.C;
        mpScale = Scale.S;
        dodgeScale = Scale.C;
        crtScale = Scale.A;
        speedScale = Scale.B;
        dmgScale = Scale.A;
        accScale = Scale.B;
        effectDefs.put(EffectTypes.Holy, new UnitStat(50.0));
        effectDefs.put(EffectTypes.Curse, new UnitStat(100.0));
        effectDefs.put(EffectTypes.Move, new UnitStat(5.0));
        effectDefs.put(EffectTypes.Nonmagic, new UnitStat(10.0));
        effectDefs.put(EffectTypes.Demonic, new UnitStat(30.0));
        effectDefs.put(EffectTypes.Stun, new UnitStat(10.0));
        luck = new UnitStat(14);
        defence = new UnitStat(0);
        nature = NatureTypes.Undead;
        spriteIds.put("idle", R.drawable.mage_undead);
        spriteIds.put("prepairing", R.drawable.mage_undead_prepairing);
        spriteIds.put("attack", R.drawable.mage_undead_attack);
        spriteIds.put("dodge", R.drawable.mage_undead);
        spriteIds.put("hit", R.drawable.mage_undead);

        icoId = R.drawable.mage_undead_ico;
        countStats();
    }
}
