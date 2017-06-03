package com.example.dima.deephearth.FromIdea.Units;

import com.example.dima.deephearth.FromIdea.Skills.DaggerStrike;
import com.example.dima.deephearth.FromIdea.Skills.PointStrike;
import com.example.dima.deephearth.FromIdea.Skills.Skip;
import com.example.dima.deephearth.FromIdea.Skills.SwordStrike;
import com.example.dima.deephearth.FromIdea.Team;

/**
 * Created by Dima on 29.05.2017.
 */

public class UnitConstructor {
    public static SkeletonKnight constructSkeletonKnight(Team team) {
        SkeletonKnight skeletonKnight = new SkeletonKnight();
        skeletonKnight.team = team;
        skeletonKnight.skills.add(new SwordStrike(skeletonKnight));
        skeletonKnight.skills.add(new Skip(skeletonKnight));
        return skeletonKnight;
    }
    public static SkeletonArcher constructSkeletonArcher(Team team){
        SkeletonArcher skeletonArcher = new SkeletonArcher();
        skeletonArcher.team = team;
        skeletonArcher.skills.add(new PointStrike(skeletonArcher));
        skeletonArcher.skills.add(new DaggerStrike(skeletonArcher));
        skeletonArcher.skills.add(new Skip(skeletonArcher));
        return skeletonArcher;
    }
}
