package com.example.dima.deephearth.FromIdea.Units;

import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.NatureTypes;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.FromIdea.UnitTypes;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 30.05.2017.
 */

public class SkeletonArcher extends Unit {
    public SkeletonArcher() {
        name = "Skeleton archer";
        description = "Dead archer.";
        type = UnitTypes.Unit;
        nature = NatureTypes.Undead;
        health = 25;
        maxHealth = 25;
        mana = 70;
        maxMana = 70;
        moves = 1;
        maxMoves = 1;
        dodge = 8;
        speed = 7;
        critical = 30;
        damage = 13;
        luck = 3;
        accuracy = 9;
        effectDefs.put(EffectTypes.Holy, 20.0);
        effectDefs.put(EffectTypes.Curse, 100.0);
        effectDefs.put(EffectTypes.Move, 20.0);
        effectDefs.put(EffectTypes.Nonmagic, 30.0);
        effectDefs.put(EffectTypes.Demonic, 5.0);
        effectDefs.put(EffectTypes.Stun, 10.0);
        spriteIds.put("idle", R.drawable.archer_undead);
        spriteIds.put("prepairing", R.drawable.archer_undead_prepairing);
        spriteIds.put("attack", R.drawable.archer_undead_attack);
        spriteIds.put("dodge", R.drawable.archer_undead);
        spriteIds.put("hit", R.drawable.archer_undead);
        icoId = R.drawable.archer_undead_ico;
        surviveChance = 0.1;
    }
}
