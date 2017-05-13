package com.example.dima.deephearth;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.PlayerSkill;

import java.util.LinkedList;

/**
 * Created by Dima on 10.05.2017.
 */

public class PlayerSkillDescLayout extends LinearLayoutCompat {

    public PlayerSkillDescLayout(Context context) {
        super(context);
    }

    public PlayerSkillDescLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayerSkillDescLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    LinkedList<PlayerSkill> skills;
    public void setSkill(LinkedList<PlayerSkill> skills) {
        this.skills = skills;

    }


}
