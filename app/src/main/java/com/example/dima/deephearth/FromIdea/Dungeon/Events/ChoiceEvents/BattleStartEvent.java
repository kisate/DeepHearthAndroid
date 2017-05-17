package com.example.dima.deephearth.FromIdea.Dungeon.Events.ChoiceEvents;

import com.example.dima.deephearth.DungeonEventHandler;
import com.example.dima.deephearth.FromIdea.Dungeon.Event;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.EventTypes;
import com.example.dima.deephearth.FromIdea.Player;

/**
 * Created by Dima on 14.05.2017.
 */

public class BattleStartEvent extends Event {

    public Player enemy;

    public BattleStartEvent(DungeonEventHandler handler, String name, String description) {
        super(handler, name, description);
    }

    public BattleStartEvent(DungeonEventHandler handler, Player enemy) {
        super(handler, "Battle enter", "You have entered battle. Get ready for a fight");
        this.enemy = enemy;
        type = EventTypes.startBattle;
    }
}
