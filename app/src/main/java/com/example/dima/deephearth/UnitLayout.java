package com.example.dima.deephearth;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
        nameView.setText("" + unit.name + " | Здоровье : " + unit.health + "/" + unit.maxHealth + " | Мана :" + unit.mana + "/" + unit.maxMana);
        TextView statView = (TextView) findViewById(R.id.textView2);
        TextView stat2View = (TextView) findViewById(R.id.textView4);
        statView.setText("ЗАЩ : " + (int)(unit.defence*100) + "%");
        stat2View.setText("УРН : " + unit.damage + " УКЛ : " + unit.dodge + "\nКРТ : " + unit.critical + "% УДЧ : " + unit.luck + "\nСКР : " + unit.speed + "ТЧН : "+ unit.accuracy);

        SkillButton[] buttons = {(SkillButton) findViewById(R.id.skillButton1), (SkillButton) findViewById(R.id.skillButton2), (SkillButton) findViewById(R.id.skillButton3), (SkillButton) findViewById(R.id.skillButton4)};
        ImageView ico = (ImageView)(findViewById(R.id.imageView2));
        ico.setImageResource(unit.icoId);
        for (int i = 0; i < 4; i++) {
            buttons[i].setSkill(unit.skills.get(i));
        }
    }

    public void updateHeroInfo(Hero hero){
        TextView nameView = (TextView) findViewById(R.id.textView5);
        nameView.setText(hero.heroClass + " " + hero.name + " | Здоровье : " + hero.health + "/" + hero.maxHealth + " | Мана :" + hero.mana + "/" + hero.maxMana);
        TextView statView = (TextView) findViewById(R.id.textView2);
        TextView stat2View = (TextView) findViewById(R.id.textView4);
        statView.setText("МОЩЬ : " + hero.power + "\nЛВК : " + hero.dexterity + "\nЗАЩ : " + (int)(hero.defence*100) + "%");
        stat2View.setText("УРН : " + hero.damage + " УКЛ : " + hero.dodge + "\nКРТ : " + hero.critical + "% УДЧ : " + hero.luck + "\nСКР : " + hero.speed + " ТЧН : "+ hero.accuracy);

        ImageView ico = (ImageView)(findViewById(R.id.imageView2));
        ico.setImageResource(hero.icoId);

        SkillButton[] buttons = {(SkillButton) findViewById(R.id.skillButton1), (SkillButton) findViewById(R.id.skillButton2), (SkillButton) findViewById(R.id.skillButton3), (SkillButton) findViewById(R.id.skillButton4)};

        for (int i = 0; i < hero.skills.size(); i++) {
            buttons[i].setSkill(hero.skills.get(i));
        }
    }
}
