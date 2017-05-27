package com.example.dima.deephearth;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.dima.deephearth.FromIdea.Game;

/**
 * Created by Dima on 27.05.2017.
 */

public class HomeLayout extends RelativeLayout {

    public Game game;

    public HomeLayout(Context context) {
        super(context);
    }

    public HomeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setGame(Game game) {
        this.game = game;

        ImageView imageView = (ImageView) findViewById(R.id.imageView5);
        if (game.unlockedBuildings.get("Inn")) {
            imageView.setImageResource(R.drawable.cave_inn_lighted);
            ((AnimationDrawable) imageView.getDrawable()).start();
        }
    }
}
