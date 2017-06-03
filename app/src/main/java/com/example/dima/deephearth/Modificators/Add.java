package com.example.dima.deephearth.Modificators;

import com.example.dima.deephearth.Modificator;

/**
 * Created by Dima on 03.06.2017.
 */

public class Add extends Modificator {
    public Add(double power, String id) {
        super(power, id);
        order = 1;
    }

    @Override
    public double action(double value) {
        return value+= power;
    }
}
