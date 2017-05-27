package com.example.dima.deephearth;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dima.deephearth.FromIdea.Effects.Buff;
import com.example.dima.deephearth.FromIdea.Game;
import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.Heroes.Archer;
import com.example.dima.deephearth.FromIdea.Heroes.HeroConstructor;
import com.example.dima.deephearth.FromIdea.Player;

import java.util.ArrayList;
import java.util.LinkedList;

public class HomeActivity extends AppCompatActivity {

    LinkedList<Hero> heroes = new LinkedList<>();
    HeroAdapter adapter;
    Game game;
    boolean initialized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        game = (Game) getIntent().getSerializableExtra("Game");

        ImageView imageView = (ImageView) findViewById(R.id.imageView5);

        HomeLayout homeLayout = (HomeLayout) findViewById(R.id.activity_home);
        homeLayout.setGame(game);
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
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

        if (!initialized) {
            HomeLayout layout = (HomeLayout) findViewById(R.id.activity_home);
            double scale = layout.getWidth()/1000.0;
            ImageView imageView = (ImageView) findViewById(R.id.imageView5);

            Drawable d = getResources().getDrawable(R.drawable.cave_empty);

            double ratio = (double) d.getIntrinsicHeight()/ (double) d.getIntrinsicWidth();

            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.height = (int) (layout.getWidth()*ratio);

            setUpButtons(scale);
            initialized = true;
        }
    }

    void fillData() {
        int size = 5;

        HeroConstructor constructor = new HeroConstructor();

        while (heroes.size() < size) {
            int r = (int)(Math.random()*3);
            Hero hero = new Archer(0,0,"",null);
            switch (r) {
                case 0 : hero = constructor.constructArcher("Mark", null); break;
                case 1 : hero = constructor.constructSwordsman("Bob", null); break;
                case 2 : hero = constructor.constructHealer("Tony", null); break;
            }

            hero.health = Math.random()*hero.health;

            heroes.add(hero);
        }
    }

    public void enterInn(View view) {
        Intent intent = new Intent(this, InnActivity.class);
        fillData();
        game.availableHeroes = heroes;
        intent.putExtra("Game", game);
        startActivityForResult(intent, 1);
    }

    public void enterDungeon(View v) {
        Intent intent = new Intent(this, ExpeditionSetupActivity.class);
        intent.putExtra("Game", game);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Game game = (Game) data.getSerializableExtra("Game");
        if (game != null) this.game = game;
        Player player = (Player) data.getSerializableExtra("Player");
        if (player != null) this.game.player = player;
    }

    void setUpButtons(double scale) {

        Button button_dungeon = (Button) findViewById(R.id.button_dungeon);
        double width = 140*scale;
        double height = 123*scale;
        double[] margins = new double[4];
        margins[0] = 426*scale;
        margins[1] = 159*scale;

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) width, (int) height);
        params.setMargins((int) margins[0],(int) margins[1], 0, 0);
        button_dungeon.setLayoutParams(params);

        if  (game.unlockedBuildings.get("Inn")) {
            Button button_inn = (Button) findViewById(R.id.button_inn);

            width = 220*scale;
            height = 194*scale;
            margins[1] = 148*scale;
            params = new RelativeLayout.LayoutParams((int) width, (int) height);
            params.setMargins(0,(int) margins[1], 0, 0);
            button_inn.setLayoutParams(params);
        }
    }
}
