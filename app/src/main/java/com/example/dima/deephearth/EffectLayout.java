package com.example.dima.deephearth;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dima.deephearth.FromIdea.Effect;

import java.util.LinkedList;

/**
 * Created by Dima on 28.05.2017.
 */

public class EffectLayout extends LinearLayout {

    LinkedList<Effect> effects;

    public EffectLayout(Context context) {
        super(context);
    }

    public EffectLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EffectLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setEffects(LinkedList<Effect> effects) {
        this.effects = effects;
        notifyDataChange();
    }

    public void notifyDataChange() {

        removeAllViews();

        for (Effect e :
                effects) {
            ImageView iv = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.effectview, this, false);
            iv.setImageResource(e.icoId);
            addView(iv);
        }
    }
}
