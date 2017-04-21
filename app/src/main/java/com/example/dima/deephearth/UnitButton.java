package com.example.dima.deephearth;

import android.content.Context;
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

import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.FromIdea.UnitTypes;

/**
 * Created by Dima on 11.04.2017.
 */

public class UnitButton extends AppCompatImageButton{
    public Unit unit;
    public ProgressBar hpBar;
    public ImageView foreground;
    public UnitLayout unitLayout;
    LinearLayout parentLayout, movePointLayout;
    FrameLayout frameLayout;

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
        setImageResource(unit.spriteId);
        Drawable d =  getResources().getDrawable(R.drawable.knight);
        Drawable c = getResources().getDrawable(unit.spriteId);
        int kwidth = d.getIntrinsicWidth();
        int width = c.getIntrinsicWidth();
        parentLayout = (LinearLayout)(getParent().getParent());
        frameLayout = (FrameLayout)(getParent());
        movePointLayout = (LinearLayout) (parentLayout.getChildAt(0));
        hpBar = (ProgressBar) (parentLayout.getChildAt(2));
        foreground = (ImageView) (frameLayout.getChildAt(1));
        setMaxWidth((int)(positionWidth*width/kwidth));
        foreground.setMaxWidth(positionWidth);
        hpBar.getLayoutParams().width = (int) (positionWidth*0.55);
        hpBar.invalidate();
        hpBar.setMax(unit.maxHealth);
        hpBar.setProgress(unit.health);
        parentLayout.setVisibility(VISIBLE);
        UpdateInfo();
    }

    public void UpdateInfo(){
        if (unit != null){
            if ((unitLayout.unit == null) || !(unitLayout.unit==unit)) {
                if (unit.type == UnitTypes.Hero) unitLayout.updateHeroInfo((Hero) unit);
                else unitLayout.updateUnitInfo(unit);
            }

            hpBar.setProgress(unit.health);
            if (unit.health <= 0) {unit.isDead = true; BattleActivity.writeStatus(unit.name + " died");}
        }

        else throw new NullPointerException();
    }
}
