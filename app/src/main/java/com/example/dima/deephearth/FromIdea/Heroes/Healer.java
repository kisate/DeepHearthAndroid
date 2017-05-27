package com.example.dima.deephearth.FromIdea.Heroes;

import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.HeroParams.NatureTypes;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Skills.Heal;
import com.example.dima.deephearth.FromIdea.Team;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 23.02.2017.
 */
public class Healer extends Hero{

    public Healer(int dexterity, int power, String name, Team team) {
        super(dexterity, power, name, team);
        description = "Heals allies";
        heroClass = HeroClasses.Healer;
        nature = NatureTypes.Alive;
        hpScale = Scale.C;
        mpScale = Scale.S;
        dodgeScale = Scale.B;
        crtScale = Scale.A;
        speedScale = Scale.C;
        dmgScale = Scale.C;
        accScale = Scale.B;
        effectDefs.put(EffectTypes.Holy, 100.0);
        effectDefs.put(EffectTypes.Curse, 70.0);
        effectDefs.put(EffectTypes.Move, 30.0);
        effectDefs.put(EffectTypes.Nonmagic, 20.0);
        effectDefs.put(EffectTypes.Demonic, 5.0);
        effectDefs.put(EffectTypes.Stun, 30.0);
        luck = 10;


        spriteIds.put("idle", R.drawable.mage);
        spriteIds.put("prepairing", R.drawable.mage);
        spriteIds.put("attack", R.drawable.mage);
        spriteIds.put("dodge", R.drawable.mage);
        spriteIds.put("hit", R.drawable.mage);

        icoId = R.drawable.mage_ico;

        countStats();
    }
}
