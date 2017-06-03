package com.example.dima.deephearth.Scripts;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.dima.deephearth.BattleActivity;
import com.example.dima.deephearth.DialogueActivity;
import com.example.dima.deephearth.FromIdea.Dialogue;
import com.example.dima.deephearth.FromIdea.Phrase;
import com.example.dima.deephearth.NarrateActivity;
import com.example.dima.deephearth.R;
import com.example.dima.deephearth.Script;

import java.util.LinkedList;


/**
 * Created by Dima on 30.05.2017.
 */

public class FinishLichBattleScript extends Script {
    BattleActivity battleActivity;
    public FinishLichBattleScript(Activity activity) {
        super(activity);
        battleActivity = (BattleActivity) activity;
    }

    @Override
    public void run() {
        super.run();
        battleActivity.interactable = false;
        Handler handler = new Handler();
        handler.postDelayed(runnable, 1000);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            RelativeLayout relativeLayout = new RelativeLayout(battleActivity);
            relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            relativeLayout.setBackgroundColor(Color.BLACK);
            relativeLayout.setAlpha(0);
            ((RelativeLayout)battleActivity.findViewById(R.id.activity_battle)).addView(relativeLayout);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(relativeLayout, "alpha", 0f, 1f);
            objectAnimator.setDuration(2000);
            objectAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Intent intent = new Intent(battleActivity, NarrateActivity.class);
                    intent.putExtra("Scene Id", 1);
                    battleActivity.startActivity(intent);
                    battleActivity.finish();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            objectAnimator.start();
        }
    };

}
