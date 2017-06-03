package com.example.dima.deephearth.FromIdea.Units;

import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.NatureTypes;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.FromIdea.UnitStat;
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
        health = new UnitStat(25);
        maxHealth = new UnitStat(25);
        mana = new UnitStat(70);
        maxMana = new UnitStat(70);
        moves = 1;
        maxMoves = 1;
        dodge = new UnitStat(8);
        speed = new UnitStat(7);
        critical = new UnitStat(30);
        damage = new UnitStat(13);
        luck = new UnitStat(3);
        accuracy = new UnitStat(9);
        effectDefs.put(EffectTypes.Holy, new UnitStat(10.0));
        effectDefs.put(EffectTypes.Curse, new UnitStat(100.0));
        effectDefs.put(EffectTypes.Move, new UnitStat(15.0));
        effectDefs.put(EffectTypes.Nonmagic, new UnitStat(30.0));
        effectDefs.put(EffectTypes.Demonic, new UnitStat(5.0));
        effectDefs.put(EffectTypes.Stun, new UnitStat(25.0));
        spriteIds.put("idle", R.drawable.archer_undead);
        spriteIds.put("prepairing", R.drawable.archer_undead_prepairing);
        spriteIds.put("attack", R.drawable.archer_undead_attack);
        spriteIds.put("dodge", R.drawable.archer_undead);
        spriteIds.put("hit", R.drawable.archer_undead);
        icoId = R.drawable.archer_undead_ico;
        surviveChance = new UnitStat(0.1);
    }
}
