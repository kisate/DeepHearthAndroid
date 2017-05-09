package com.example.dima.deephearth;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.dima.deephearth.FromIdea.Dungeon.Corridor;

/**
 * Created by Dima on 07.05.2017.
 */

public class CorridorView extends AppCompatImageView {

    public static int HORIZONTAL = 11, VERTICAL = 22;

    public Corridor corridor;

    public int orientation = HORIZONTAL;

    public boolean accessible;

    public CorridorView(Context context) {
        super(context);
    }

    public CorridorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CorridorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setOrientation(int orientation) {
        this.orientation = orientation;
        if (orientation == HORIZONTAL) setImageResource(R.drawable.corridor_horizontal);
        else if (orientation == VERTICAL) setImageResource(R.drawable.corridor_vertical);
    }

    public void setCorridor(Corridor corridor) {
        this.corridor = corridor;
        setOrientation(corridor.orientation);
    }

    public void setMetrics (int width, int height) {
        FrameLayout parent = (FrameLayout) getParent();
        ImageView iv = (ImageView) parent.getChildAt(1);
        if (orientation == HORIZONTAL) {
            setMaxWidth(width);
            iv.setMinimumHeight(height);
            iv.setMinimumWidth(width);
        }
        else {
            setMaxHeight(width);
            iv.setMinimumHeight(width);
            iv.setMinimumWidth(height);
        }
    }

    public void setAccessible(boolean accessible) {
        FrameLayout parent = (FrameLayout) getParent();
        ImageView iv = (ImageView) parent.getChildAt(1);
        if (accessible) {
            iv.setAlpha(0f);
        }
        else iv.setAlpha(0.5f);
        this.accessible = accessible;
    }
}
