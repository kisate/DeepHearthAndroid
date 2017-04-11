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
    public void use(Unit target) {
        super.use(target);
        System.out.println(owner.name + "skipped his move");
        setup();
    }
}
