package com.example.dima.deephearth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dima.deephearth.FromIdea.Battle;
import com.example.dima.deephearth.FromIdea.Player;
import com.example.dima.deephearth.FromIdea.Unit;

/**
 * Created by Dima on 11.04.2017.
 */

public class BattleActivity extends AppCompatActivity {

    public Battle battle;
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
            buttons1[i].unit = unit;
            buttons1[i].setImageResource(unit.spriteId);
            buttons1[i].setVisibility(View.VISIBLE);
        }

        for (int i = 0; i < player2.team.size(); i++) {
            Unit unit = player2.team.get(i);
            buttons2[i].unit = unit;
            buttons2[i].setImageResource(unit.spriteId);
            buttons2[i].setVisibility(View.VISIBLE);
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
}
