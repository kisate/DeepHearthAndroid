package com.example.dima.deephearth;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.example.dima.deephearth.FromIdea.Unit;

/**
 * Created by Dima on 24.04.2017.
 */

public class EnemyDescLayout extends RelativeLayout {

    public Unit unit;

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public EnemyDescLayout(Context context) {
        super(context);
    }

    public EnemyDescLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EnemyDescLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
