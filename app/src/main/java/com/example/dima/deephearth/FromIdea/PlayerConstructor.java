package com.example.dima.deephearth.FromIdea;

/**
 * Created by Dima on 21.04.2017.
 */

public class PlayerConstructor {
    public static Player construct(Intellect intellect){
        Player product = new Player(intellect);
        product.team.player = product;
        return product;
    }
}
