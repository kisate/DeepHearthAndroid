package com.example.dima.deephearth.FromIdea;

import com.example.dima.deephearth.UnitButton;

import java.util.Comparator;

/**
 * Created by Dima on 28.03.2017.
 */
public class SpeedComparator implements Comparator<Unit> {
    @Override
    public int compare(Unit o1, Unit o2) {
        return (int)(o2.speed.getValue() - o1.speed.getValue());
    }
}
