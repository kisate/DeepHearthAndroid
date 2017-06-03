package com.example.dima.deephearth.FromIdea.Units;

import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.NatureTypes;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.FromIdea.UnitTypes;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 29.05.2017.
 */

public class SkeletonKnight extends Unit {
    public SkeletonKnight() {
        name = "Skeleton knight";
        description = "Dead knight. There are tons of them here nowadays";
        type = UnitTypes.Unit;
        nature = NatureTypes.Undead;
        health = 30;
        maxHealth = 30;
        mana = 50;
        maxMana = 50;
        moves = 1;
        maxMoves = 1;
        dodge = 7;
        speed = 6;
        critical = 10;
        damage = 10;
        luck = 2;
        accuracy = 8;
        effectDefs.put(EffectTypes.Holy, 20.0);
        effectDefs.put(EffectTypes.Curse, 100.0);
        effectDefs.put(EffectTypes.Move, 50.0);
        effectDefs.put(EffectTypes.Nonmagic, 60.0);
        effectDefs.put(EffectTypes.Demonic, 5.0);
        effectDefs.put(EffectTypes.Stun, 40.0);
        spriteIds.put("idle", R.drawable.knight_undead_idle);
        spriteIds.put("prepairing", R.drawable.knight_undead_prepairing);
        spriteIds.put("attack", R.drawable.knight_undead_attack);
        spriteIds.put("dodge", R.drawable.knight_undead_idle);
        spriteIds.put("hit", R.drawable.knight_undead_idle);
        icoId = R.drawable.knight_undead_ico;
        surviveChance = 0.1;
    }
}
