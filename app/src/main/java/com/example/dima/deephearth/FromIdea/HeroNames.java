package com.example.dima.deephearth.FromIdea;

/**
 * Created by Dima on 15.05.2017.
 */

public class HeroNames {
    public static String[] names = {"Bob", "Mark", "Dan", "Douglas", "Nick", "Bartholomew", "Henry", "Pete", "Jeremy", "Timothy", "Thomas"};

    public static String getName() {
        return names[(int)(names.length * Math.random())];
    }
}
