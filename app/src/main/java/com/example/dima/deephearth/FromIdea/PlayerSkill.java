package com.example.dima.deephearth.FromIdea;

import com.example.dima.deephearth.UnitButton;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by Dima on 23.04.2017.
 */

public class PlayerSkill implements Serializable{
    public String name, description;
    public int cost;
    public int power;
    public int skillIco;
    public Player player;
    public boolean friendly = true;
    public LinkedList<Effect> effects = new LinkedList<>();

    public PlayerSkill(Player player) {
        this.player = player;
    }

    public void action(Unit target){}
}
