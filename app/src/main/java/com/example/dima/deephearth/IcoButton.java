package com.example.dima.deephearth;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;

import com.example.dima.deephearth.FromIdea.Hero;


/**
 * Created by Dima on 17.05.2017.
 */

public class IcoButton extends AppCompatImageButton {
    public IcoButton(Context context) {
        super(context);
    }

    public IcoButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IcoButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    Hero hero;

    public void setHero(Hero hero) {
        this.hero = hero;
        if (hero == null) setImageResource(0);
        else setImageResource(hero.icoId);
    }
}
