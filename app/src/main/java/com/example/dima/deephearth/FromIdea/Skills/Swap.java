package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Scale;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

/**
 * Created by Dima on 09.05.2017.
 */

public class Swap extends Skill {
    public Swap(Unit owner) {
        super(owner);
        skillIco = R.drawable.skillico_skip;
        friendly = true;
        onSelf = false;
        heals = true;
        name = "Swap";
        canBeUsedFrom[0] = true;
        canBeUsedFrom[1] = true;
        canBeUsedFrom[2] = true;
        canBeUsedFrom[3] = true;
    }

    @Override
    public void use(Unit target) {
    }

    @Override
    public void setup() {
    }

    @Override
    public void action(Unit target) {
    }
}
