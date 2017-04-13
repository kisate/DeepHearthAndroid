package com.example.dima.deephearth.FromIdea.Skills;

import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Unit;

/**
 * Created by Dima on 28.03.2017.
 */
public class Skip extends Skill {
    public Skip(Unit owner) {
        super(owner);
    }

    @Override
    public boolean use(Unit target) {
        view.setText(owner.name + "skipped his move");
        setup();
        return true;
    }
}
