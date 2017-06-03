package com.example.dima.deephearth.FromIdea.Heroes;

import com.example.dima.deephearth.FromIdea.Skills.CuttingStrike;
import com.example.dima.deephearth.FromIdea.Skills.DaggerStrike;
import com.example.dima.deephearth.FromIdea.Skills.DemonicArrow;
import com.example.dima.deephearth.FromIdea.Skills.DemonicPower;
import com.example.dima.deephearth.FromIdea.Skills.EnhanceArmor;
import com.example.dima.deephearth.FromIdea.Skills.Explosion;
import com.example.dima.deephearth.FromIdea.Skills.Fortify;
import com.example.dima.deephearth.FromIdea.Skills.GunShot;
import com.example.dima.deephearth.FromIdea.Skills.Heal;
import com.example.dima.deephearth.FromIdea.Skills.HingedBlow;
import com.example.dima.deephearth.FromIdea.Skills.HummerStrike;
import com.example.dima.deephearth.FromIdea.Skills.Incineration;
import com.example.dima.deephearth.FromIdea.Skills.Kick;
import com.example.dima.deephearth.FromIdea.Skills.Meditation;
import com.example.dima.deephearth.FromIdea.Skills.OneShot;
import com.example.dima.deephearth.FromIdea.Skills.PointStrike;
import com.example.dima.deephearth.FromIdea.Skills.Skip;
import com.example.dima.deephearth.FromIdea.Skills.StunningStrike;
import com.example.dima.deephearth.FromIdea.Skills.SwordStrike;
import com.example.dima.deephearth.FromIdea.Team;

/**
 * Created by Dima on 20.03.2017.
 */
public class HeroConstructor {
    public Healer constructHealer(int dexterity, int power, String name,  Team team) {
        Healer product = new Healer(dexterity, power, name, team);
        product.skills.add(new Heal(product));
        product.skills.add(new Heal(product));
        product.skills.add(new Heal(product));
        product.skills.add(new Heal(product));
        return product;
    }
    public Healer constructHealer(String name, Team team) {return constructHealer(3, 10, name, team);}

    public Swordsman constructSwordsman(int dexterity, int power, String name,  Team team) {
        Swordsman product = new Swordsman(dexterity, power, name, team);
        product.skills.add(new SwordStrike(product));
        product.skills.add(new CuttingStrike(product));
        product.skills.add(new Fortify(product));
        product.skills.add(new StunningStrike(product));
        return  product;
    }
    public Swordsman constructSwordsman(String name, Team team){return constructSwordsman(5, 10, name, team);}

    public Archer constructArcher(int dex, int pwr, String name, Team team) {
        Archer product = new Archer(dex, pwr, name, team);
        product.skills.add(new DaggerStrike(product));
        product.skills.add(new PointStrike(product));
        product.skills.add(new HingedBlow(product));
        product.skills.add(new DemonicArrow(product));

        return product;
    }

    Gunner constructGunner(int dex, int pwr, String name, Team team) {
        Gunner product = new Gunner(dex, pwr, name, team);
        product.skills.add(new GunShot(product));
        product.skills.add(new HummerStrike(product));
        product.skills.add(new EnhanceArmor(product));
        product.skills.add(new Kick(product));
        return product;
    }

    Mage constructMage(int dex, int pwr, String name, Team team) {
        Mage product = new Mage(dex, pwr, name, team);
        product.skills.add(new Explosion(product));
        product.skills.add(new Incineration(product));
        product.skills.add(new Meditation(product));
        product.skills.add(new DemonicPower(product));
        return product;
    }

    public Mage constructMage(String name, Team team) {
        return constructMage(6, 10, name, team);
    }

    public Gunner constructGunner(String name, Team team) {
        return constructGunner(6, 10, name, team);
    }

    public Archer constructArcher(String name, Team team) {
        return constructArcher(8, 8, name, team);
    }

    UndeadHealer constructUndeadHealer(int dex, int pwr, String name, Team team) {
        UndeadHealer product = new UndeadHealer(dex, pwr, name, team);
        product.skills.add(new Heal(product));
        product.skills.add(new Heal(product));
        product.skills.add(new Heal(product));
        product.skills.add(new Heal(product));
        return product;
    }
    UndeadArcher constructUndeadArcher(int dex, int pwr, String name, Team team) {
        UndeadArcher product = new UndeadArcher(dex, pwr, name, team);
        product.skills.add(new DaggerStrike(product));
        product.skills.add(new PointStrike(product));
        product.skills.add(new HingedBlow(product));
        product.skills.add(new DemonicArrow(product));

        return product;
    }
    UndeadGunner constructUndeadGunner(int dex, int pwr, String name, Team team) {
        UndeadGunner product = new UndeadGunner(dex, pwr, name, team);
        product.skills.add(new GunShot(product));
        product.skills.add(new HummerStrike(product));
        product.skills.add(new EnhanceArmor(product));
        product.skills.add(new Kick(product));
        return product;

    }
    UndeadKnight constructUndeadKnight(int dex, int pwr, String name, Team team) {
        UndeadKnight product = new UndeadKnight(dex, pwr, name, team);
        product.skills.add(new SwordStrike(product));
        product.skills.add(new CuttingStrike(product));
        product.skills.add(new Fortify(product));
        product.skills.add(new StunningStrike(product));
        return  product;
    }

    UndeadMage constructUndeadMage(int dex, int pwr, String name, Team team) {
        UndeadMage product = new UndeadMage(dex, pwr, name, team);
        product.skills.add(new Explosion(product));
        product.skills.add(new Incineration(product));
        product.skills.add(new Meditation(product));
        product.skills.add(new DemonicPower(product));
        return product;
    }

    public UndeadMage constructUndeadMage(String name, Team team) {
        return constructUndeadMage(6, 10, name, team);
    }

    public UndeadHealer constructUndeadHealer(String name, Team team) {
        return constructUndeadHealer(3, 10, name, team);
    }

    public UndeadGunner constructUndeadGunner(String name, Team team) {return  constructUndeadGunner(6, 10, name, team);}

    public UndeadArcher constructUndeadArcher(String name, Team team) {return  constructUndeadArcher(8, 8, name, team);}

    public UndeadKnight constructUndeadKnight(String name, Team team) {return  constructUndeadKnight(5, 10, name, team);}
}
