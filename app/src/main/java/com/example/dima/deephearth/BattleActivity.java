package com.example.dima.deephearth;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.dima.deephearth.FromIdea.Battle;
import com.example.dima.deephearth.FromIdea.Player;
import com.example.dima.deephearth.FromIdea.Unit;

/**
 * Created by Dima on 11.04.2017.
 */

public class BattleActivity extends AppCompatActivity implements View.OnClickListener{

    //TODO : setUnit, setSkill methods, rework hp and mp scaling

    public Battle battle;
    Player player1, player2;
    public UnitButton curUButton;
    public SkillButton curSButton;
    public boolean skillPicked = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        player1 = (Player) getIntent().getSerializableExtra("Player 1");
        player2 = (Player) getIntent().getSerializableExtra("Player 2");
        battle = new Battle(player1, player2);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int scHeight = size.y;
        int scWidth  = size.x;

        int maxWidth = (int) (scWidth/8.5);

        LinearLayout[] uButLayouts = {(LinearLayout) findViewById(R.id.LinearLayout1),(LinearLayout) findViewById(R.id.LinearLayout2),(LinearLayout) findViewById(R.id.LinearLayout3),(LinearLayout) findViewById(R.id.LinearLayout4),
                (LinearLayout) findViewById(R.id.LinearLayout5),(LinearLayout) findViewById(R.id.LinearLayout6),(LinearLayout) findViewById(R.id.LinearLayout7),(LinearLayout) findViewById(R.id.LinearLayout8)};

        UnitButton[] buttons1 = {(UnitButton) findViewById(R.id.UnitButton1),(UnitButton) findViewById(R.id.UnitButton2),
                (UnitButton) findViewById(R.id.UnitButton3), (UnitButton) findViewById(R.id.UnitButton4)};

        UnitButton[] buttons2 = {(UnitButton) findViewById(R.id.UnitButton5),(UnitButton) findViewById(R.id.UnitButton6),
                (UnitButton) findViewById(R.id.UnitButton7), (UnitButton) findViewById(R.id.UnitButton8)};

        ProgressBar[] hpBars = {(ProgressBar) findViewById(R.id.progressBar1),(ProgressBar) findViewById(R.id.progressBar2),(ProgressBar) findViewById(R.id.progressBar3),(ProgressBar) findViewById(R.id.progressBar4),
                (ProgressBar) findViewById(R.id.progressBar5),(ProgressBar) findViewById(R.id.progressBar6),(ProgressBar) findViewById(R.id.progressBar7),(ProgressBar) findViewById(R.id.progressBar8)};
        FrameLayout[] frames = new FrameLayout[8];

        ImageView[] foregrounds = new ImageView[8];

        for (int i = 0; i < 8; i++) {
            frames[i] = (FrameLayout) uButLayouts[i].getChildAt(0);
        }

        for (int i = 0; i < 8; i++) {
            foregrounds[i] = (ImageView) frames[i].getChildAt(1);
        }
        int kwidth = ContextCompat.getDrawable(this, R.drawable.knight).getIntrinsicWidth();

        for (int i = player1.team.size()-1; i > -1 ; i--) {
            Unit unit = player1.team.get(3-i);
            buttons1[i].setOnClickListener(this);
            buttons1[i].unit = unit;
            buttons1[i].setImageResource(unit.spriteId);
            buttons1[i].setVisibility(View.VISIBLE);
            buttons1[i].foreground = foregrounds[i];
            buttons1[i].hpBar = hpBars[i];
            hpBars[i].setVisibility(View.VISIBLE);
            Drawable d = ContextCompat.getDrawable(this, unit.spriteId);
            int width = (int)(maxWidth*d.getIntrinsicWidth()/kwidth);
            buttons1[i].setMaxWidth(width);
            foregrounds[i].setMaxWidth(width);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) uButLayouts[i].getLayoutParams();
            int margin = maxWidth*i - maxWidth*(d.getIntrinsicWidth() - kwidth)/2/kwidth;
            layoutParams.setMarginStart(margin);
            layoutParams.setMargins(margin, 0, 0, 0);
            buttons1[i].unitLayout = (UnitLayout) findViewById(R.id.unitLayout);
            buttons1[i].UpdateInfo();
        }

        for (int i = player2.team.size()-1; i > -1; i--) {
            Unit unit = player2.team.get(3-i);
            buttons2[i].unit = unit;
            buttons2[i].setOnClickListener(this);
            buttons2[i].setImageResource(unit.spriteId);
            buttons2[i].setVisibility(View.VISIBLE);
            buttons2[i].foreground = foregrounds[i+4];
            buttons2[i].hpBar = hpBars[4+i];
            hpBars[i + 4].setVisibility(View.VISIBLE);
            Drawable d = ContextCompat.getDrawable(this, unit.spriteId);
            int width = (int)(maxWidth*d.getIntrinsicWidth()/ContextCompat.getDrawable(this, R.drawable.knight).getIntrinsicWidth());
            buttons2[i].setMaxWidth(width);
            foregrounds[4+i].setMaxWidth(width);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) uButLayouts[i + 4].getLayoutParams();
            int margin = maxWidth*(i) - maxWidth*(d.getIntrinsicWidth() - kwidth)/2/kwidth;
            layoutParams.setMarginEnd(margin);
            layoutParams.setMargins(0, 0, margin, 0);
            buttons2[i].unitLayout = (UnitLayout) findViewById(R.id.unitLayout);
            buttons2[i].UpdateInfo();
        }
        int[] skillIds = {R.id.skillButton1, R.id.skillButton2, R.id.skillButton3, R.id.skillButton4};
        for (int i = 0; i < 4; i++) {
            ((SkillButton)(findViewById(skillIds[i]))).setOnClickListener(this);
        }
        uButtonClick(curUButton);
        ((ImageView) findViewById(R.id.imageView)).setImageResource(player1.team.get(0).icoId);
    }

    private void uButtonClick(UnitButton v) {
        if  (curUButton != null) {
            curUButton.setPressed(false);
            curUButton.foreground.setVisibility(View.INVISIBLE);
            CustomRelativeLayout layout = (CustomRelativeLayout) (v.getParent().getParent().getParent());
            layout.moveChildToBack((View) (curUButton.getParent().getParent()));
        }
        if (!(curUButton == v)) {
            curUButton = v;
            v.getParent().getParent().getParent().bringChildToFront((View) v.getParent().getParent());
            v.UpdateInfo();
            v.foreground.setVisibility(View.VISIBLE);
            curUButton.setPressed(true);
        }

        else curUButton = null;
    }

    public void onClick2(View v){
        finish();
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }

    @Override
    public void onClick(View v) {
        int[] butidS = {R.id.UnitButton1, R.id.UnitButton2, R.id.UnitButton3, R.id.UnitButton4, R.id.UnitButton5, R.id.UnitButton6, R.id.UnitButton7, R.id.UnitButton8};
        boolean t = false;
        for (int a :
                butidS) {
            if(a == v.getId()) t = true;
        }

        if (t && (v.getVisibility() == View.VISIBLE) && skillPicked) {
            sButtonClick((UnitButton) v);
        }

        else if (t && (v.getVisibility() == View.VISIBLE)) {
            uButtonClick((UnitButton) v);
        }

        int[] skillIds = {R.id.skillButton1, R.id.skillButton2, R.id.skillButton3, R.id.skillButton4};

        t = false;

        for (int a :
                skillIds) {
            if(a == v.getId()) t = true;
        }
        if (t) {
            skillPicked = true;
            curSButton = (SkillButton) v;
            Log.d("Debug", "clicked skill");
        }
    }

    public  void sButtonClick(UnitButton v){
        if (curSButton.useSkill(v.unit)) skillPicked = false;
        Log.d("Debug", "used skill");
    }
}
