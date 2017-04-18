package com.example.dima.deephearth;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Unit;

/**
 * Created by Dima on 12.04.2017.
 */

public class SkillButton extends AppCompatImageButton {
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
    }

    public boolean useSkill(Unit target){
        TextView view = (TextView) findViewById(R.id.textView5);
        skill.view = view;
        view.setText(skill.owner.name + " использует " + skill.name + " на " + target.name);
        return skill.use(target);
    }
}
