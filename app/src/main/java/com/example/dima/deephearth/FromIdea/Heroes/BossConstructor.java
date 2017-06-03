package com.example.dima.deephearth.FromIdea.Heroes;

import com.example.dima.deephearth.FromIdea.Heroes.Bosses.Lich;
import com.example.dima.deephearth.FromIdea.Skills.CurseOrb;
import com.example.dima.deephearth.FromIdea.Skills.Devastation;
import com.example.dima.deephearth.FromIdea.Skills.Resurrection;
import com.example.dima.deephearth.FromIdea.Team;

/**
 * Created by Dima on 29.05.2017.
 */

public class BossConstructor {
    static Lich constructLich(int dex, int pwr, String name, Team team) {
        Lich lich = new Lich(dex, pwr, name, team);
        lich.skills.add(new Devastation(lich));
        lich.skills.add(new CurseOrb(lich));
        lich.skills.add(new Resurrection(lich));
        return lich;
    }

    public static Lich constructLich(String name, Team team) {
        return constructLich(8, 30, name, team);
    }
}
