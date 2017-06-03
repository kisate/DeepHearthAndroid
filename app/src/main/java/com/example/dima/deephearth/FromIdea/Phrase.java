package com.example.dima.deephearth.FromIdea;


import java.io.Serializable;

/**
 * Created by Dima on 29.05.2017.
 */
public class Phrase implements Serializable{
    public int first;
    public CharSequence second;
    public Phrase(int first, CharSequence second) {
        this.first = first;
        this.second = second;
    }
}
