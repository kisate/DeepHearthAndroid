package com.example.dima.deephearth;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.dima.deephearth.FromIdea.PlayerSkill;

/**
 * Created by Dima on 12.05.2017.
 */

public class PlayerSkillButton extends AppCompatImageButton {

    FrameLayout parent;
    ImageView foreground;

    public PlayerSkillButton(Context context) {
        super(context);
    }

    public PlayerSkillButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayerSkillButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    PlayerSkill playerSkill;
    boolean usable = true, current = false;

    public void setUsable(boolean usable) {
        this.usable = usable;

        if (!usable) {
            foreground.setImageResource(R.drawable.skillico_unusable);
            foreground.setAlpha(0.7f);
        }

        else {
            foreground.setImageResource(0);
            foreground.setAlpha(1f);
        }
    }

    public void setCurrent(boolean current){
        this.current = current;



        if (current) foreground.setImageResource(R.drawable.skillico_selected);
        else {
            if (usable) foreground.setImageResource(0);
        }
    }

    public void setPlayerSkill(PlayerSkill playerSkill) {
        this.playerSkill = playerSkill;

        parent = (FrameLayout)  getParent();
        foreground = (ImageView) parent.getChildAt(1);

        setImageResource(playerSkill.skillIco);
    }
}
