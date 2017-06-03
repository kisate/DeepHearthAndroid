package com.example.dima.deephearth.FromIdea.Units;

import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.NatureTypes;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.FromIdea.UnitStat;
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
        health = new UnitStat(30);
        maxHealth = new UnitStat(30);
        mana = new UnitStat(50);
        maxMana = new UnitStat(50);
        moves = 1;
        maxMoves = 1;
        dodge = new UnitStat(7);
        speed = new UnitStat(6);
        critical = new UnitStat(10);
        damage = new UnitStat(10);
        luck = new UnitStat(2);
        accuracy = new UnitStat(8);
        effectDefs.put(EffectTypes.Holy, new UnitStat(20.0));
        effectDefs.put(EffectTypes.Curse, new UnitStat(100.0));
        effectDefs.put(EffectTypes.Move, new UnitStat(0.0));
        effectDefs.put(EffectTypes.Nonmagic, new UnitStat(60.0));
        effectDefs.put(EffectTypes.Demonic, new UnitStat(5.0));
        effectDefs.put(EffectTypes.Stun, new UnitStat(50.0));
        spriteIds.put("idle", R.drawable.knight_undead_idle);
        spriteIds.put("prepairing", R.drawable.knight_undead_prepairing);
        spriteIds.put("attack", R.drawable.knight_undead_attack);
        spriteIds.put("dodge", R.drawable.knight_undead_idle);
        spriteIds.put("hit", R.drawable.knight_undead_idle);
        icoId = R.drawable.knight_undead_ico;
        surviveChance = new UnitStat(0.1);
    }
}
