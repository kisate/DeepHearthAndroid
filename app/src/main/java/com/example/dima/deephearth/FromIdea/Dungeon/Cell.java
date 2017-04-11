package com.example.dima.deephearth.FromIdea.Dungeon;

import com.example.dima.deephearth.FromIdea.Probability;

/**
 * Created by Dima on 07.04.2017.
 */
public class Cell {
    public int x,y;
    public CellTypes type;
    public double chance;

    public Cell(int x, int y, CellTypes type) {
        this.x = x;
        this.y = y;
        this.type = type;
        chance = 0.2;
    }
}
