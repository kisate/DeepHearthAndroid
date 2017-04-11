package com.example.dima.deephearth;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.example.dima.deephearth.FromIdea.Unit;

/**
 * Created by Dima on 11.04.2017.
 */

public class UnitLayout extends RelativeLayout {

    public Unit unit;

    public UnitLayout(Context context) {
        super(context);
    }

    public UnitLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UnitLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void updateInfo(){
        if(unit != null) {

        }
    }
}
