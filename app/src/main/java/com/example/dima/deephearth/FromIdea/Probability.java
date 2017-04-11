package com.example.dima.deephearth.FromIdea;

import java.util.Random;

/**
 * Created by Dima on 28.03.2017.
 */
public class Probability {
    public int success, all;
    public Probability(int success, int all) {
        this.success = success;
        this.all = all;
    }

    public boolean check() {
        Random random = new Random(System.currentTimeMillis());
        return (success - 1) < random.nextInt(all);
    }
}
