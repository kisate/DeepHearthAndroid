package com.example.dima.deephearth;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.dima.deephearth.FromIdea.Item;

/**
 * Created by Dima on 17.05.2017.
 */

public class ItemView extends LinearLayout {

    public Item item;

    public void setItem(Item item) {
        this.item = item;
    }

    public ItemView(Context context) {
        super(context);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
