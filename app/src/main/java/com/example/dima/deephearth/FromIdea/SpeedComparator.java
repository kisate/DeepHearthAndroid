package com.example.dima.deephearth.FromIdea;

import com.example.dima.deephearth.UnitButton;

import java.util.Comparator;

/**
 * Created by Dima on 28.03.2017.
 */
public class SpeedComparator implements Comparator<UnitButton> {
    @Override
    public int compare(UnitButton o1, UnitButton o2) {
        return o1.unit.speed - o2.unit.speed;
    }
}
