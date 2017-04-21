package com.example.dima.deephearth.FromIdea;

import android.util.Pair;

import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.SkillButton;
import com.example.dima.deephearth.UnitButton;

/**
 * Created by Dima on 21.04.2017.
 */

public class ComputerIntellect extends Intellect {
    public ComputerIntellect(){
        type = IntellectTypes.Computer;
    }

    @Override
    public Pair<UnitButton, SkillButton> think(Battle battle) {

        Pair<UnitButton, SkillButton> res = new Pair<>(battle.playerButtons.get((int)(battle.playerButtons.size()*Math.random())), battle.skillButtons[(int)(Math.random()*4)]);
        while (res.first.unit.isDead) res = new Pair<>(battle.playerButtons.get((int)(battle.playerButtons.size()*Math.random())), battle.skillButtons[(int)(Math.random()*4)]);

        return res;
    }
}
