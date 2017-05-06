package com.example.dima.deephearth.FromIdea;

import android.util.Pair;

import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Heroes.Swordsman;
import com.example.dima.deephearth.SkillButton;
import com.example.dima.deephearth.UnitButton;

import java.util.LinkedList;

/**
 * Created by Dima on 21.04.2017.
 */

public class ComputerIntellect extends Intellect {
    public ComputerIntellect(){
        type = IntellectTypes.Computer;
    }

    @Override
    public Pair<UnitButton, Skill> think(Battle battle) {

        UnitButton target;

        Skill skill = battle.activeButton.unit.skills.get((int)(4*Math.random()));
        if (battle.activeButton.unit.getClass() == Swordsman.class) skill = battle.activeButton.unit.skills.get(0);
        if (skill.friendly) target = battle.enemyButtons.get((int)(Math.random()*battle.enemyButtons.size()));
        else if (skill.onSelf) target = battle.activeButton;
        else target = battle.playerButtons.get((int)(Math.random()*battle.playerButtons.size()));


        Pair<UnitButton, Skill> res = new Pair<>(target, skill);

        return res;
    }
}
