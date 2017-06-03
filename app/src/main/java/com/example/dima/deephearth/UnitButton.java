package com.example.dima.deephearth;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.util.Log;
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
import com.example.dima.deephearth.FromIdea.HeroParams.NatureTypes;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.FromIdea.UnitTypes;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Dima on 11.04.2017.
 */

public class UnitButton extends AppCompatImageButton{
    public Unit unit;
    public ProgressBar healthBar, manaBar;
    public ImageView foreground;
    public TextView movePointsView;
    public UnitLayout unitLayout;
    public HashMap<String, Integer> maxWidths = new HashMap<>();
    int foregroundId;
    LinearLayout parentLayout, movePointLayout, frameLayout, barLayout;
    EffectLayout effectLayout;

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
        unit.button = this;

        setImageResource(unit.spriteIds.get("idle"));
        Drawable d =  getResources().getDrawable(R.drawable.knight);
        Drawable c = getResources().getDrawable(unit.spriteIds.get("idle"));
        Drawable e = getResources().getDrawable(unit.spriteIds.get("attack"));
        int kwidth = d.getIntrinsicWidth();
        int width = c.getIntrinsicWidth();
        parentLayout = (LinearLayout)(getParent().getParent().getParent());
        frameLayout = (LinearLayout)(getParent().getParent());
        movePointLayout = (LinearLayout) (parentLayout.getChildAt(0));
        effectLayout = (EffectLayout) parentLayout.getChildAt(2);
        barLayout = (LinearLayout) parentLayout.getChildAt(3);
        LinearLayout temp2 = (LinearLayout) barLayout.getChildAt(0);
        healthBar = (ProgressBar) (temp2.getChildAt(0));
        manaBar = (ProgressBar) temp2.getChildAt(1);
        movePointsView = (TextView) barLayout.getChildAt(1);
        foreground = (ImageView) (frameLayout.getChildAt(1));

        maxWidths.put("attack", positionWidth*e.getIntrinsicWidth()/kwidth);
        maxWidths.put("idle", positionWidth*width/kwidth);

        setMaxWidth(maxWidths.get("idle"));
        foreground.setMaxWidth(positionWidth);
        if(friendly) foregroundId = R.drawable.foreground_selected_green;
        else foregroundId = R.drawable.foreground_selected_red;

        healthBar.getLayoutParams().width = (int) (positionWidth*0.55);
        healthBar.invalidate();
        healthBar.setMax((int)unit.maxHealth);
        healthBar.setProgress((int)unit.health);
        healthBar.getProgressDrawable().setColorFilter(
                Color.RED, PorterDuff.Mode.SRC_ATOP);


        manaBar.getLayoutParams().width = (int) (positionWidth*0.55);
        manaBar.invalidate();
        manaBar.setMax((int)unit.maxMana);
        manaBar.setProgress((int)unit.mana);
        manaBar.getProgressDrawable().setColorFilter(
                Color.BLUE, PorterDuff.Mode.SRC_ATOP);

        effectLayout.setEffects(unit.effects);

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
            effectLayout.notifyDataChange();
            if (unit.health <= 0) {
                    unit.nearDeath = true;
                    setAlpha(0.8f);
                    unit.health = 0;
            }
            else {
                unit.nearDeath = false;
                setAlpha(1f);
            }
            if (unit.nature == NatureTypes.Undead) {
                if (unit.mana <= 0) {
                    unit.nearDeath = true;
                    setAlpha(0.8f);
                }
                else {
                    if (unit.health > 0) {
                        unit.nearDeath = false;
                        setAlpha(1f);
                    }
                }
            }

        }

        else throw new NullPointerException();
    }
}
