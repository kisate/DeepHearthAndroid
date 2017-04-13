package com.example.dima.deephearth;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dima.deephearth.FromIdea.Battle;
import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Player;
import com.example.dima.deephearth.FromIdea.Unit;
import com.example.dima.deephearth.FromIdea.UnitTypes;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Dima on 11.04.2017.
 */

public class BattleActivity extends AppCompatActivity implements View.OnClickListener{

    public Battle battle;
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
        Player player1, player2;
        player1 = (Player) getIntent().getSerializableExtra("Player 1");
        player2 = (Player) getIntent().getSerializableExtra("Player 2");
        battle = new Battle(player1, player2);

        UnitButton[] buttons1 = {(UnitButton) findViewById(R.id.UnitButton4),(UnitButton) findViewById(R.id.UnitButton3),
                (UnitButton) findViewById(R.id.UnitButton2), (UnitButton) findViewById(R.id.UnitButton1)};

        UnitButton[] buttons2 = {(UnitButton) findViewById(R.id.UnitButton5),(UnitButton) findViewById(R.id.UnitButton6),
                (UnitButton) findViewById(R.id.UnitButton7), (UnitButton) findViewById(R.id.UnitButton8)};

        for (int i = 0; i < player1.team.size(); i++) {
            Unit unit = player1.team.get(i);
            buttons1[i].setOnClickListener(this);
            buttons1[i].unit = unit;
            buttons1[i].setImageResource(unit.spriteId);
            buttons1[i].setVisibility(View.VISIBLE);
        }

        for (int i = 0; i < player2.team.size(); i++) {
            Unit unit = player2.team.get(i);
            buttons2[i].unit = unit;
            buttons2[i].setOnClickListener(this);
            buttons2[i].setImageResource(unit.spriteId);
            buttons2[i].setVisibility(View.VISIBLE);
        }
        int[] skillIds = {R.id.skillButton1, R.id.skillButton2, R.id.skillButton3, R.id.skillButton4};
        for (int i = 0; i < 4; i++) {
            ((SkillButton)(findViewById(skillIds[i]))).setOnClickListener(this);
        }
        ((UnitLayout) findViewById(R.id.unitLayout)).updateHeroInfo((Hero) player1.team.get(0));
        curUButton = (UnitButton)(findViewById(R.id.UnitButton4));
        ((ImageView) findViewById(R.id.imageView)).setImageResource(player1.team.get(0).icoId);
    }

    private void uButtonClick(UnitButton v) {
        v.setBackgroundColor(Color.GRAY);
        curUButton.setBackgroundResource(0);
        curUButton = v;
        UnitLayout layout = (UnitLayout) findViewById(R.id.unitLayout);
        if ((layout.unit == null) || !(layout.unit==v.unit)) {
            if (v.unit.type == UnitTypes.Hero) layout.updateHeroInfo((Hero) v.unit);
            else layout.updateUnitInfo(v.unit);
        }
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
        Log.d("Debug", "Pressed");
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
