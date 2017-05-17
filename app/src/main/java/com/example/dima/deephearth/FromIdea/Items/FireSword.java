package com.example.dima.deephearth.FromIdea.Items;

import android.util.Log;

import com.example.dima.deephearth.FromIdea.Effect;
import com.example.dima.deephearth.FromIdea.Effects.Buff;
import com.example.dima.deephearth.FromIdea.Effects.Fire;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Inventory;
import com.example.dima.deephearth.FromIdea.Item;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.R;

import java.util.LinkedList;

/**
 * Created by Dima on 09.05.2017.
 */

public class FireSword extends Item {
    public FireSword() {
        name = "Fire Sword";
        description = "Sword shrouded with fire. Sets enemies on fire. Safe for user.";
        icoId = R.drawable.skillico1;
        Buff buff = new Buff(owner, 0, -1) {

            Effect effect = new Fire(null, 2, 3);

            @Override
            public void apply() {
                super.apply();
                if (!applied) {
                    for (Skill s :
                            target.skills) {
                        if (!s.friendly && !s.onSelf) s.effects.add(effect);
                    }
                }
                applied = true;
            }

            @Override
            public void remove() {
                super.remove();
                for (Skill s :
                        target.skills) {
                    s.effects.remove(effect);
                }
            }
        };
        buff.id = 1;
        effects.add(buff);
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

        for (Effect e :
                owner.effects) {
            if (e.id != 0 && ids.contains(e.id)) {
                e.remove();
                effects.remove(e);
            }
        }
    }

}
