package com.example.dima.deephearth;

import java.io.Serializable;

/**
 * Created by Dima on 03.06.2017.
 */

public class Modificator implements Serializable{
    public double power;
    public int order;
    public String id;
    public Modificator(double power, String id) {
        this.power = power;
        this.id = id;
    }

    public double action(double value) {
        return value;
    }
}
