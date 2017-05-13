package com.example.dima.deephearth;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.FromIdea.UnitTypes;

/**
 * Created by Dima on 11.04.2017.
 */

public class UnitButton extends AppCompatImageButton{
    public Unit unit;
    public ProgressBar healthBar, manaBar;
    public ImageView foreground;
    public TextView movePointsView;
    public UnitLayout unitLayout;
    public int maxAWidth, maxWidth;
    int foregroundId;
    LinearLayout parentLayout, movePointLayout, frameLayout;
    RelativeLayout barLayout;

    public boolean canBeTarget = false;
    public boolean friendly = false;

    public boolean picked = false;

    public UnitButton(Context context) {
        super(context);
    }

    public UnitButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public UnitButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setUnit(Unit unit, int positionWidth){
        this.unit = unit;
        setImageResource(unit.spriteIds.get("idle"));
        Drawable d =  getResources().getDrawable(R.drawable.knight);
        Drawable c = getResources().getDrawable(unit.spriteIds.get("idle"));
        Drawable e = getResources().getDrawable(unit.spriteIds.get("attack"));
        int kwidth = d.getIntrinsicWidth();
        int width = c.getIntrinsicWidth();
        parentLayout = (LinearLayout)(getParent().getParent().getParent());
        frameLayout = (LinearLayout)(getParent().getParent());
        movePointLayout = (LinearLayout) (parentLayout.getChildAt(0));
        barLayout = (RelativeLayout) parentLayout.getChildAt(2);
        LinearLayout temp2 = (LinearLayout) barLayout.getChildAt(0);
        healthBar = (ProgressBar) (temp2.getChildAt(0));
        manaBar = (ProgressBar) temp2.getChildAt(1);
        movePointsView = (TextView) barLayout.getChildAt(1);
        foreground = (ImageView) (frameLayout.getChildAt(1));
        maxAWidth = positionWidth*e.getIntrinsicWidth()/kwidth;
        maxWidth  = positionWidth*width/kwidth;
        setMaxWidth(maxWidth);
        foreground.setMaxWidth(positionWidth);
        if(friendly) foregroundId = R.drawable.foreground_selected_green;
        else foregroundId = R.drawable.foreground_selected_red;

        healthBar.getLayoutParams().width = (int) (positionWidth*0.55);
        healthBar.invalidate();
        healthBar.setMax((int)unit.maxHealth);
        healthBar.setProgress((int)unit.health);
        healthBar.getProgressDrawable().setColorFilter(
                Color.RED, PorterDuff.Mode.MULTIPLY);


        manaBar.getLayoutParams().width = (int) (positionWidth*0.55);
        manaBar.invalidate();
        manaBar.setMax((int)unit.maxMana);
        manaBar.setProgress((int)unit.mana);
        manaBar.getProgressDrawable().setColorFilter(
                Color.BLUE, PorterDuff.Mode.MULTIPLY);
        parentLayout.setVisibility(VISIBLE);
        UpdateInfo();
    }

    public void setCanBeTarget(boolean canBeTarget) {
        this.canBeTarget = canBeTarget;
        if (canBeTarget) {
            foreground.setImageResource(foregroundId);
            foreground.setVisibility(VISIBLE);
        }
        else foreground.setVisibility(INVISIBLE);
    }

    public void setPicked(boolean picked) {
        this.picked = picked;

        if (picked) {
            foreground.setImageResource(R.drawable.foreground_selected_yellow);
            foreground.setVisibility(VISIBLE);
        }

        else setCanBeTarget(canBeTarget);
    }

    public void UpdateInfo(){
        if (unit != null){
            if ((unitLayout.unit == null) || !(unitLayout.unit==unit)) {
                unitLayout.updateUnitInfo(unit);
            }

            healthBar.setProgress((int) unit.health);
            manaBar.setProgress((int) unit.mana);
            movePointsView.setText(unit.moves + "");
            if (unit.health <= 0) {unit.isDead = true; BattleActivity.writeStatus(unit.name + " died");}
        }

        else throw new NullPointerException();
    }
}
