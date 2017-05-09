package com.example.dima.deephearth.FromIdea.Dungeon.Interactables;

import com.example.dima.deephearth.FromIdea.Dungeon.Interactable;
import com.example.dima.deephearth.FromIdea.Dungeon.InteractableTypes;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 09.05.2017.
 */

public class Entity extends Interactable {
    public Entity() {
        type = InteractableTypes.Entity;
        image = R.drawable.room_empty;
    }
}
