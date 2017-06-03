package com.example.dima.deephearth.FromIdea.Intellects;

import android.util.Log;
import android.util.Pair;

import com.example.dima.deephearth.FromIdea.Battle;
import com.example.dima.deephearth.FromIdea.ComputerIntellect;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Heroes.Bosses.Lich;
import com.example.dima.deephearth.FromIdea.Skills.Devastation;
import com.example.dima.deephearth.UnitButton;

/**
 * Created by Dima on 30.05.2017.
 */

public class LichIntellect extends ComputerIntellect {
    @Override
    public Pair<UnitButton, Skill> think(Battle battle) {
        Log.d("Debug", "" + battle.turnNumber);
        Pair<UnitButton, Skill> _choice = super.think(battle);
        UnitButton target = _choice.first;
        Skill skill = _choice.second;
        if (skill.getClass() == Devastation.class && battle.turnNumber < 3) {
            skill = battle.activeButton.unit.skills.get(1);
        }

        if (Math.random() > 0.67 && battle.activeButton.unit.getClass() == Lich.class) {
            skill = battle.activeButton.unit.skills.get(2);
            target = battle.activeButton;
        }

        if (battle.turnNumber == 5) {
            skill = battle.activeButton.unit.skills.get(0);
            int i = 0;
            while(target == null && i < 4) {
                target = battle.player1.team.get(i).button;
                i++;
            }
        }

        return new Pair<>(target, skill);
    }
}
