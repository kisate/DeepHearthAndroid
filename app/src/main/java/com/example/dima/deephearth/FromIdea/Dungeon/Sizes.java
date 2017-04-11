package com.example.dima.deephearth.FromIdea.Dungeon;

/**
 * Created by Dima on 10.04.2017.
 */
public enum Sizes {
    Small(4, 3, 3), Big(5, 5, 5);

    int x, y, price;

    Sizes(int x, int y, int price) {
        this.x = x;
        this.y = y;
        this.price = price;
    }
}
