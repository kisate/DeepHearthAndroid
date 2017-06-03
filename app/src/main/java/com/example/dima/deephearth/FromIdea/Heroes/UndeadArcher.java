package com.example.dima.deephearth.FromIdea.Heroes;

import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.HeroParams.NatureTypes;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Team;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 28.05.2017.
 */

public class UndeadArcher extends Hero {
    public UndeadArcher(int dexterity, int power, String name, Team team) {
        super(dexterity, power, name, team);

        description = "Mercenary marksman, who found his end deep underground";
        heroClass = HeroClasses.Archer;
        hpScale = Scale.C;
        mpScale = Scale.B;
        dodgeScale = Scale.A;
        crtScale = Scale.A;
        speedScale = Scale.A;
        dmgScale = Scale.B;
        accScale = Scale.A;
        effectDefs.put(EffectTypes.Holy, 10.0);
        effectDefs.put(EffectTypes.Curse, 100.0);
        effectDefs.put(EffectTypes.Move, 15.0);
        effectDefs.put(EffectTypes.Nonmagic, 30.0);
        effectDefs.put(EffectTypes.Demonic, 5.0);
        effectDefs.put(EffectTypes.Stun, 25.0);
        luck = 13;
        defence = 0;
        nature = NatureTypes.Undead;
        spriteIds.put("idle", R.drawable.archer_undead);
        spriteIds.put("prepairing", R.drawable.archer_undead_prepairing);
        spriteIds.put("attack", R.drawable.archer_undead_attack);
        spriteIds.put("dodge", R.drawable.archer_undead);
        spriteIds.put("hit", R.drawable.archer_undead);

        icoId = R.drawable.archer_undead_ico ;
        countStats();
    }
}
