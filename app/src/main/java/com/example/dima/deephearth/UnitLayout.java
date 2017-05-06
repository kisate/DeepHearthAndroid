package com.example.dima.deephearth;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.Unit;

/**
 * Created by Dima on 11.04.2017.
 */

public class UnitLayout extends RelativeLayout {

    public Unit unit;

    public UnitLayout(Context context) {
        super(context);
    }

    public UnitLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UnitLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void updateUnitInfo(Unit unit){
        TextView nameView = (TextView) findViewById(R.id.textView5);
        nameView.setText("" + unit.getName() + " | Hp : " + unit.health + "/" + unit.maxHealth + " | Mp :" + unit.mana + "/" + unit.maxMana);
        TextView statView = (TextView) findViewById(R.id.dmgDodgeTView);
        TextView stat2View = (TextView) findViewById(R.id.ADSTView);
        TextView stat3View = (TextView) findViewById(R.id.LCTView);

        statView.setText("Dmg : " + unit.damage + "\nDodge : " + unit.dodge);
        stat2View.setText("Acc : " + unit.accuracy + "\nSpd : " + unit.speed + "\nDef : " + unit.defence + "%");
        stat3View.setText("Luck : " + unit.luck + "\nCrt : " + unit.critical + "%");

        SkillButton[] buttons = {(SkillButton) findViewById(R.id.skillButton1), (SkillButton) findViewById(R.id.skillButton2), (SkillButton) findViewById(R.id.skillButton3), (SkillButton) findViewById(R.id.skillButton4)};
        ImageView ico = (ImageView)(findViewById(R.id.imageView2));
        ico.setImageResource(unit.icoId);
        for (int i = 0; i < 4; i++) {
            buttons[i].setSkill(unit.skills.get(i));
        }
    }
}
