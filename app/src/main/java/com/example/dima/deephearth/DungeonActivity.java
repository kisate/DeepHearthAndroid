package com.example.dima.deephearth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dima.deephearth.FromIdea.Dungeon.Dungeon;
import com.example.dima.deephearth.FromIdea.Heroes.HeroConstructor;
import com.example.dima.deephearth.FromIdea.Heroes.Swordsman;
import com.example.dima.deephearth.FromIdea.Player;

public class DungeonActivity extends AppCompatActivity {

    Player player;
    Dungeon dungeon;
    HeroConstructor constructor = new HeroConstructor();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        player = getPlayer();
    }

    public void onClick(View v){
        launchBattle();
    }

    private void launchBattle(){
        Intent intent = new Intent(this, BattleActivity.class);
        Player player2 = new Player();
        player2.team.add(constructor.constructHealer("P21", player2.team));
        player2.team.add(constructor.constructSwordsman("P22", player2.team));
        player2.team.add(constructor.constructArcher("P23", player2.team));
        player2.team.add(constructor.constructArcher("P24", player2.team));
        player.team.get(1).spriteId = R.drawable.knight_double;
        intent.putExtra("Player 1", player);
        intent.putExtra("Player 2", player2);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //thread.notify();
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

    private Player getPlayer(){
        Player player = new Player();
        player.team.add(constructor.constructArcher("P11", player.team));
        player.team.add(constructor.constructArcher("P12", player.team));
        player.team.add(constructor.constructHealer("P13", player.team));
        player.team.add(constructor.constructSwordsman("P14", player.team));
        return player;
    }
}
