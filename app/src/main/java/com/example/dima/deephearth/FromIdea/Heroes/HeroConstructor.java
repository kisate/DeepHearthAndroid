package com.example.dima.deephearth.FromIdea.Heroes;

import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.Skills.CuttingStrike;
import com.example.dima.deephearth.FromIdea.Skills.Heal;
import com.example.dima.deephearth.FromIdea.Skills.Skip;
import com.example.dima.deephearth.FromIdea.Skills.SwordStrike;
import com.example.dima.deephearth.FromIdea.Team;

/**
 * Created by Dima on 20.03.2017.
 */
public class HeroConstructor {
    public Healer constructHealer(int dexterity, int power, String name,  Team team) {
        Healer product = new Healer(dexterity, power, name, team);
        product.skills.add(new Heal(product));
        return product;
    }
    public Healer constructHealer(String name, Team team) {return constructHealer(3, 10, name, team);}

    public Swordsman constructSwordsman(int dexterity, int power, String name,  Team team) {
        Swordsman product = new Swordsman(dexterity, power, name, team);
        product.skills.add(new SwordStrike(product));
        product.skills.add(new CuttingStrike(product));
        product.skills.add(new Skip(product));
        return  product;
    }
    public Swordsman constructSwordsman(String name, Team team){return constructSwordsman(5, 10, name, team);}
}
