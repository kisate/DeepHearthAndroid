package com.example.dima.deephearth.FromIdea.Items;

import android.util.Log;

import com.example.dima.deephearth.FromIdea.Effect;
import com.example.dima.deephearth.FromIdea.Effects.Buff;
import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.Inventory;
import com.example.dima.deephearth.FromIdea.Item;
import com.example.dima.deephearth.FromIdea.PlayerSkills.UndeadRage;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.Modificators.Mult;
import com.example.dima.deephearth.R;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Dima on 17.05.2017.
 */

public class GreatStaff extends Item {
    public GreatStaff(){
        name = "Great staff";
        description = "Large sorcery staff, that keeps grand power within";
        icoId = R.drawable.greatstaff_ico;
        effects.add(new GreatStaffBuff(null, 20, -1));
    }

    @Override
    public void equip(Inventory inventory) {
        super.equip(inventory);
        owner = inventory.hero;
        Log.d("Debug", "" + owner);
        for (Effect e :
                effects) {
            owner.effects.add(e);
            e.target = owner;
            e.apply();
        }
    }

    @Override
    public void unEquip(Inventory inventory) {
        super.unEquip(inventory);

        LinkedList<Integer> ids = new LinkedList<>();

        for (Effect e :
                effects) {
            ids.add(e.id);
        }

        for (Iterator<Effect> iterator = owner.effects.iterator(); iterator.hasNext();) {
            Effect e = iterator.next();
            if (e.id != 0 && ids.contains(e.id)) {
                iterator.remove();
            }
        }
    }

    class GreatStaffBuff extends Buff {

        public GreatStaffBuff(Unit target, int power, int turns) {
            super(target, power, turns);
            id = 2;
        }

        @Override
        public boolean apply() {
            boolean res = super.apply();
            if (!applied) {
                Hero hero = (Hero) target;
                hero.power.addMod(new Mult(1 + power/100.0, "greatstaff buff"));
                hero.countStats();
            }
            applied = true;
            return res;
        }

        @Override
        public void remove() {
            super.remove();
            Hero hero = (Hero) target;
            hero.power.removeMod("greatstaff buff");
            hero.countStats();
        }

        @Override
        public Effect addToTarget(Unit target) {
            super.addToTarget(target);
            GreatStaffBuff ef = new GreatStaffBuff(target, power, turns);
            target.effects.add(ef);
            return ef;
        }
    }
}
