package com.example.dima.deephearth;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Unit;

/**
 * Created by Dima on 12.04.2017.
 */

public class SkillButton extends AppCompatImageButton implements Cloneable{

    FrameLayout frameLayout;
    ImageView foreground;

    boolean usable = false, current = false;

    public SkillButton(Context context) {
        super(context);
    }

    public SkillButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SkillButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public Skill skill;
    public void setSkill(Skill skill) {
        this.skill = skill;
        setImageResource(skill.skillIco);
        frameLayout = (FrameLayout)getParent();
        foreground = (ImageView) frameLayout.getChildAt(1);
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
        if (!usable) {
            foreground.setImageResource(R.drawable.skillico_unusable);
            foreground.setAlpha(0.7f);
            setCurrent(false);
        }

        else {
            foreground.setImageResource(0);
            foreground.setAlpha(1f);
            if (current) setCurrent(current);
        }
    }


    public void useSkill(UnitButton target){
        useSkill(target, skill);
    }

    public void useSkill(UnitButton target, Skill skill) {
        skill.use(target.unit);
    }

    public void setCurrent(boolean current){
        this.current = current;
        if (current) foreground.setImageResource(R.drawable.skillico_selected);
        else {
            if (usable) foreground.setImageResource(0);
        }
    }
}
