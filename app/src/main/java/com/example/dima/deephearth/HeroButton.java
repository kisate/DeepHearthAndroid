package com.example.dima.deephearth;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.example.dima.deephearth.FromIdea.Hero;

/**
 * Created by Dima on 16.05.2017.
 */

public class HeroButton extends AppCompatButton {

    public Hero hero;

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public HeroButton(Context context) {
        super(context);
    }

    public HeroButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeroButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
