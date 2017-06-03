package com.example.dima.deephearth.FromIdea.Heroes;

import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.HeroParams.NatureTypes;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Skills.SwordStrike;
import com.example.dima.deephearth.FromIdea.Team;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 23.02.2017.
 */
public class Swordsman extends Hero {

    public Swordsman(int dexterity, int power, String name, Team team) {
        super(dexterity, power, name, team);
        description = "Strikes enemies with his sword";
        heroClass = HeroClasses.Swordsman;
        nature = NatureTypes.Alive;
        hpScale = Scale.A;
        mpScale = Scale.D;
        dodgeScale = Scale.B;
        crtScale = Scale.A;
        speedScale = Scale.B;
        dmgScale = Scale.B;
        accScale = Scale.B;
        effectDefs.put(EffectTypes.Holy, 100.0);
        effectDefs.put(EffectTypes.Curse, 40.0);
        effectDefs.put(EffectTypes.Move, 0.0);
        effectDefs.put(EffectTypes.Nonmagic, 60.0);
        effectDefs.put(EffectTypes.Demonic, 5.0);
        effectDefs.put(EffectTypes.Stun, 50.0);
        luck = 10;
        defence = 0.2;

        spriteIds.put("idle", R.drawable.knight);
        spriteIds.put("prepairing", R.drawable.knight_prepairing);
        spriteIds.put("attack", R.drawable.knight_attack);
        spriteIds.put("dodge", R.drawable.knight);
        spriteIds.put("hit", R.drawable.knight);

        icoId = R.drawable.knight_ico;
        countStats();
    }
}
