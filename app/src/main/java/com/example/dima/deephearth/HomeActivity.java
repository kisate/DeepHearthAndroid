package com.example.dima.deephearth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.text.method.ScrollingMovementMethod;
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
import com.example.dima.deephearth.FromIdea.HeroNames;
import com.example.dima.deephearth.FromIdea.Heroes.Archer;
import com.example.dima.deephearth.FromIdea.Heroes.HeroConstructor;
import com.example.dima.deephearth.FromIdea.Phrase;
import com.example.dima.deephearth.FromIdea.Player;
import com.example.dima.deephearth.FromIdea.UnitStat;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;

import static android.view.View.GONE;

public class HomeActivity extends AppCompatActivity {

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

        if (getIntent().getBooleanExtra("tutorial", false)) {
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.infoLayout);
            relativeLayout.setVisibility(View.VISIBLE);
            relativeLayout.setClickable(true);
            TextWriter textWriter = (TextWriter) findViewById(R.id.textView2);
            textWriter.setMovementMethod(new ScrollingMovementMethod());
            final Button button = (Button) findViewById(R.id.nextButton);
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    button.setText("finish");
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((View)v.getParent().getParent()).setVisibility(GONE);
                        }
                    });
                }
            };
            textWriter.showPhrases(getPhrases(), findViewById(R.id.nextButton), runnable);
            fillData();
        }
    }

    LinkedList<Phrase> getPhrases () {
        LinkedList<Phrase> phrases = new LinkedList<>();
        phrases.add(new Phrase(1, "Congratulations! You are lich now. And this is your dungeon's heart."));
        phrases.add(new Phrase(1, "Hole in the centre is exit to the dungeon. It will take you to a random place in it. If you are lucky you will find exit, otherwise you will need to kill all the enemies for it to appear. Feel free to explore, but soon it will be not only you, who decided to explore it."));
        phrases.add(new Phrase(1, "While you explore dungeon you can get souls and items. During the expedition you can use souls to recharge your mana and equip items in the \"Player\" menu."));
        phrases.add(new Phrase(1, "Building to the left of it is improvised inn. Dungeon collects every dead body and your heart (fire in the middle) attracts some of them. Inn collects them while you in the dungeon and doesn't let them disappear. There you can hire new units for souls, or get rid of old ones for free."));
        phrases.add(new Phrase(1, "That is all for now, good luck."));
        return phrases;
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
        game.availableHeroes.clear();
        while (game.availableHeroes.size() < size) {
            int r = (int)(Math.random()*4);
            Hero hero = new Archer(0,0,"",null);
            switch (r) {
                case 0 : hero = constructor.constructUndeadArcher(HeroNames.getName(), null); break;
                case 1 : hero = constructor.constructUndeadKnight(HeroNames.getName(), null); break;
                case 2 : hero = constructor.constructUndeadGunner(HeroNames.getName(), null); break;
                case 3 : hero = constructor.constructUndeadMage(HeroNames.getName(), null); break;
            }

            hero.health = new UnitStat(Math.random()*hero.health.clearValue);

            game.availableHeroes.add(hero);
        }
    }

    public void enterInn(View view) {
        Intent intent = new Intent(this, InnActivity.class);
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
        if (data.getBooleanExtra("finished", false)) {
            Player player = (Player) data.getSerializableExtra("Player");
            if (player != null) this.game.player = player;
            fillData();
        }
        else if (data.getBooleanExtra("Inn", false)) {
            game = (Game) data.getSerializableExtra("Game");
        }
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

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        saveGame();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveGame();
    }

    void saveGame() {
        Log.d("Debug", "save" + game.availableHeroes.size() + " " + game.reserve.size());
        try {
            FileOutputStream fos = openFileOutput("save.txt", Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(game);
            os.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
