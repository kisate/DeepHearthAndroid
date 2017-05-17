package com.example.dima.deephearth.FromIdea.Dungeon.Events;

import com.example.dima.deephearth.DungeonEventHandler;
import com.example.dima.deephearth.FromIdea.Dungeon.Choice;
import com.example.dima.deephearth.FromIdea.Dungeon.Event;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.ChoiceEvents.BattleStartEvent;
import com.example.dima.deephearth.FromIdea.Player;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Dima on 09.05.2017.
 */

public class EnemyEvent extends Event {
    public Player enemy;
    public EnemyEvent(DungeonEventHandler handler, String name, String description) {
        super(handler, name, description);
    }

    public EnemyEvent(Player enemy, DungeonEventHandler handler) {
        super(handler, "Battle", "You have encountered an enemy");
        this.enemy = enemy;
        choices.add(new EnterBattleChoice(handler, enemy));
    }

    class EnterBattleChoice extends Choice {
        public EnterBattleChoice(DungeonEventHandler handler, String name, LinkedList<Event> events) {
            super(handler, name, events);
        }

        public EnterBattleChoice(DungeonEventHandler handler, Player enemy) {
            super(handler, "Enter battle", new LinkedList<Event>(Arrays.asList(new BattleStartEvent(handler, enemy))));
        }
    }
}
