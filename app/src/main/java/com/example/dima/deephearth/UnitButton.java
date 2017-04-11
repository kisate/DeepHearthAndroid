package com.example.dima.deephearth;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.example.dima.deephearth.FromIdea.Unit;

/**
 * Created by Dima on 11.04.2017.
 */

public class UnitButton extends AppCompatImageButton{
    public Unit unit;

    public UnitButton(Context context) {
        super(context);
    }

    public UnitButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public UnitButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
