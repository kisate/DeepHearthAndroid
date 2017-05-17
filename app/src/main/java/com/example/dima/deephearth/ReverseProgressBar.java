package com.example.dima.deephearth;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by Dima on 14.05.2017.
 */

public class ReverseProgressBar extends ProgressBar {
    public ReverseProgressBar(Context context) {
        super(context);
    }

    public ReverseProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReverseProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.scale(-1f, 1f, super.getWidth() * 0.5f, super.getHeight() * 0.5f);
        super.onDraw(canvas);
    }
}
