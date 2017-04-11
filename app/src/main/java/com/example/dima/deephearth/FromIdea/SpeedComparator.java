package com.example.dima.deephearth.FromIdea;

import java.util.Comparator;

/**
 * Created by Dima on 28.03.2017.
 */
public class SpeedComparator implements Comparator<Unit> {
    @Override
    public int compare(Unit o1, Unit o2) {
        return o1.speed - o2.speed;
    }
}
