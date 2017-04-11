package com.example.dima.deephearth.FromIdea;

/**
 * Created by Dima on 19.03.2017.
 */
public enum Scale {
    S(1.75), A(1.25), B(1), C(0.75), D(0.25);

    public double scale;

    Scale(double scale) {
        this.scale = scale;
    }
}
