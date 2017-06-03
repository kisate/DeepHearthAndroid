package com.example.dima.deephearth.FromIdea;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Dima on 23.02.2017.
 */
public class Team extends ArrayList<Unit>{
    public Player player;

    public Team(int initialCapacity) {
        super(initialCapacity);

        for (int i = 0; i < initialCapacity; i++) {
            add(null);
        }
    }
}
