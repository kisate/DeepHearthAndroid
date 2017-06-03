package com.example.dima.deephearth.FromIdea.Dungeon.Events;

import com.example.dima.deephearth.DungeonEventHandler;
import com.example.dima.deephearth.FromIdea.Dungeon.Choice;
import com.example.dima.deephearth.FromIdea.Dungeon.Event;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.ChoiceEvents.BattleStartEvent;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.ChoiceEvents.LeaveDungeonEvent;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.Choices.LeaveChoice;
import com.example.dima.deephearth.FromIdea.Player;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Dima on 29.05.2017.
 */

public class BossEvent extends Event {
    public Player enemy;
    public BossEvent(DungeonEventHandler handler, String name, String description) {
        super(handler, name, description);
    }

    public BossEvent(DungeonEventHandler handler, Player enemy) {
        super(handler, "Boss battle", "You have encountered boss of this dungeon. If you defeat it, you can leave");
        this.enemy = enemy;
        choices.add(new BossChoice(handler, enemy));
    }

    class BossChoice extends Choice {

        public BossChoice(DungeonEventHandler handler, String name, LinkedList<Event> events) {
            super(handler, name, events);
        }

        public BossChoice(DungeonEventHandler handler, Player enemy) {
            super(handler, "Enter battle", new LinkedList<Event>(Arrays.asList(new BattleStartEvent(handler, enemy))));
            events.get(0).type = EventTypes.boss;
        }
    }
}
