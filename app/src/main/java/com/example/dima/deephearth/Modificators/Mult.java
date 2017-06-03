package com.example.dima.deephearth.Modificators;

import com.example.dima.deephearth.Modificator;

/**
 * Created by Dima on 03.06.2017.
 */

public class Mult extends Modificator {
    public Mult(double power, String id) {
        super(power, id);
        order = 0;
    }

    @Override
    public double action(double value) {
        return value *= power;
    }
}
