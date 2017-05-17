package com.example.dima.deephearth.FromIdea.Dungeon.Interactables;

import com.example.dima.deephearth.DungeonEventHandler;
import com.example.dima.deephearth.FromIdea.Dungeon.Interactable;
import com.example.dima.deephearth.FromIdea.Dungeon.InteractableTypes;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 09.05.2017.
 */

public class Empty extends Interactable {

    public Empty(DungeonEventHandler handler) {
        super(handler);
        type = InteractableTypes.Empty;
        image = R.drawable.room_empty;
    }
}
