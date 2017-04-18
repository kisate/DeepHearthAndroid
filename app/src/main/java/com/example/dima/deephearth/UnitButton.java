package com.example.dima.deephearth;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
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

    public UnitButton(Context context) {
        super(context);
    }

    public UnitButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public UnitButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void UpdateInfo(){
        if (unit != null){
            if ((unitLayout.unit == null) || !(unitLayout.unit==unit)) {
                if (unit.type == UnitTypes.Hero) unitLayout.updateHeroInfo((Hero) unit);
                else unitLayout.updateUnitInfo(unit);
            }

            hpBar.setProgress((int)(unit.health/unit.maxHealth*100));
        }

        else throw new NullPointerException();
    }
}
