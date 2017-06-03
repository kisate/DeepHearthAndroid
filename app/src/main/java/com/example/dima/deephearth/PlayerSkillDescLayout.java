package com.example.dima.deephearth;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.PlayerSkill;

import java.util.LinkedList;

/**
 * Created by Dima on 10.05.2017.
 */

public class PlayerSkillDescLayout extends RelativeLayout {

    public PlayerSkillDescLayout(Context context) {
        super(context);
    }

    public PlayerSkillDescLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayerSkillDescLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    PlayerSkill skill;
    public void setSkill(PlayerSkill skill) {
        this.skill = skill;
        setVisibility(VISIBLE);
        ImageView imageView = (ImageView) findViewById(R.id.imageView17);
        imageView.setImageResource(skill.skillIco);
        TextView nameView = (TextView) findViewById(R.id.textView13);
        nameView.setText(skill.name);
        TextView descView = (TextView) findViewById(R.id.textView14);
        descView.setText(skill.description + " | MP : " + skill.cost);
    }


}
