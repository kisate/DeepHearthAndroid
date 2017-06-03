package com.example.dima.deephearth.FromIdea.Heroes;

import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.HeroParams.NatureTypes;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Skills.Heal;
import com.example.dima.deephearth.FromIdea.Team;
import com.example.dima.deephearth.FromIdea.UnitStat;
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
        effectDefs.put(EffectTypes.Holy, new UnitStat(100.0));
        effectDefs.put(EffectTypes.Curse, new UnitStat(70.0));
        effectDefs.put(EffectTypes.Move, new UnitStat(0.0));
        effectDefs.put(EffectTypes.Nonmagic, new UnitStat(20.0));
        effectDefs.put(EffectTypes.Demonic, new UnitStat(5.0));
        effectDefs.put(EffectTypes.Stun, new UnitStat(30.0));
        luck = new UnitStat(10);
        defence = new UnitStat(0);

        spriteIds.put("idle", R.drawable.mage);
        spriteIds.put("prepairing", R.drawable.mage_prepairing);
        spriteIds.put("attack", R.drawable.mage_attack);
        spriteIds.put("dodge", R.drawable.mage);
        spriteIds.put("hit", R.drawable.mage);

        icoId = R.drawable.mage_ico;

        countStats();
    }
}
