package com.example.dima.deephearth.FromIdea;

import android.util.Pair;

import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Heroes.Bosses.Lich;
import com.example.dima.deephearth.FromIdea.Heroes.Swordsman;
import com.example.dima.deephearth.FromIdea.Skills.Skip;
import com.example.dima.deephearth.UnitButton;

import java.util.ArrayList;
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

        Unit current = battle.activeButton.unit;

        LinkedList<Pair<UnitButton, Skill>> choices = new LinkedList<>();

        for (Skill s :
                current.skills) {
            if (current.mana.clearValue >= s.cost && s.canBeUsedFrom[current.team.indexOf(current)]&&s.getClass() != Skip.class) {
                if (s.onSelf) {
                    choices.add(new Pair<>(current.button, s));
                }
                if (s.friendly) {
                    for (int i = 0; i < 4; i++) {
                        Unit unit = current.team.get(i);
                        if (unit != null && s.canBeUsedOn[i] && unit != current) choices.add(new Pair<>(battle.player2.team.get(i).button, s));
                    }
                }
                else if (!s.onSelf) {
                    for (int i = 0; i < 4; i++) {
                        Unit unit = battle.player1.team.get(i);
                        if (s.canBeUsedOn[i] && unit != null) choices.add(new Pair<>(unit.button, s));
                    }
                }
            }
        }

        if (choices.size() == 0) {
            int index = 0;
            for (int i = 0; i < current.skills.size(); i++) {
                if (current.skills.get(i).getClass() == Skip.class) index = i;
                }
            choices.add(new Pair<UnitButton, Skill>(current.button, current.skills.get(index)));
        }

        return choices.get((int)(Math.random()*choices.size()));
    }
}
