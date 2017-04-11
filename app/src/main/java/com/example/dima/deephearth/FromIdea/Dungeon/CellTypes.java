package com.example.dima.deephearth.FromIdea.Dungeon;

/**
 * Created by Dima on 07.04.2017.
 */
public enum CellTypes {
    Plane(1), Obstacle(-1), Enemy(-2), Door(10), Treasure(5), Empty(0);

    int weight;

    CellTypes(int weight) {
        this.weight = weight;
    }
}
