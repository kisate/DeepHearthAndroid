package com.example.dima.deephearth.FromIdea;

import android.util.Pair;

import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.SkillButton;
import com.example.dima.deephearth.UnitButton;

import java.io.Serializable;

/**
 * Created by Dima on 19.04.2017.
 */

public abstract class Intellect implements Serializable{
    public IntellectTypes type;

    public Intellect(){}

    public Pair<UnitButton, Skill> think(Battle battle) {
        return null;
    }
}
