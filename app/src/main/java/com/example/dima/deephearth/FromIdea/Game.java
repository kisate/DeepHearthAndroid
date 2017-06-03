package com.example.dima.deephearth.FromIdea;

import android.os.Build;

import com.example.dima.deephearth.FromIdea.PlayerSkills.Curse;
import com.example.dima.deephearth.FromIdea.PlayerSkills.Renewal;
import com.example.dima.deephearth.FromIdea.PlayerSkills.UndeadRage;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Dima on 27.05.2017.
 */

public class Game implements Serializable{
    public Player player;
    public HashMap<String, Boolean> unlockedBuildings = new HashMap<>();
    public LinkedList<Hero> reserve;
    public LinkedList<Hero> availableHeroes;

    public Game(){
        player = new Player(new HumanIntellect());
        player.skills.add(new Renewal(player));
        player.skills.add(new Curse(player));
        player.skills.add(new UndeadRage(player));
        unlockedBuildings.put("Inn", false);
        unlockedBuildings.put("Smith", false);
        reserve = new LinkedList<>();
        availableHeroes = new LinkedList<>();
    }
}
