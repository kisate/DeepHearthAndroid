package com.example.dima.deephearth.FromIdea.Heroes;

import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.HeroParams.NatureTypes;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Team;
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
        effectDefs.put(EffectTypes.Holy, 50.0);
        effectDefs.put(EffectTypes.Curse, 100.0);
        effectDefs.put(EffectTypes.Move, 5.0);
        effectDefs.put(EffectTypes.Nonmagic, 10.0);
        effectDefs.put(EffectTypes.Demonic, 30.0);
        effectDefs.put(EffectTypes.Stun, 10.0);
        luck = 14;
        defence = 0;
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
