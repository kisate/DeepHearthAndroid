package com.example.dima.deephearth.FromIdea;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by Dima on 23.02.2017.
 */
public class Player implements Serializable {
    public Intellect intellect;
    public int mp = 130, maxMp = 130;
    public int souls = 0;
    public LinkedList<PlayerSkill> skills = new LinkedList<>();


    public Player(Intellect intellect) {
        this.intellect = intellect;
    }

    public Team team = new Team();
    public Team deadUnits = new Team();
    public void makeMove(int user, int spell, Player enemy, int target) {
        team.get(user).skills.get(spell).use(enemy.team.get(target));
    }
}
