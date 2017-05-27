package com.example.dima.deephearth;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dima.deephearth.FromIdea.Dungeon.Dungeon;
import com.example.dima.deephearth.FromIdea.Game;
import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.HeroNames;
import com.example.dima.deephearth.FromIdea.Heroes.Archer;
import com.example.dima.deephearth.FromIdea.Heroes.HeroConstructor;
import com.example.dima.deephearth.FromIdea.HumanIntellect;
import com.example.dima.deephearth.FromIdea.Items.FireSword;
import com.example.dima.deephearth.FromIdea.Items.GreatStaff;
import com.example.dima.deephearth.FromIdea.Player;
import com.example.dima.deephearth.FromIdea.PlayerConstructor;
import com.example.dima.deephearth.FromIdea.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class ExpeditionSetupActivity extends AppCompatActivity implements View.OnClickListener{

    int size = 0;
    LinkedList<Hero> heroes = new LinkedList<>();
    HeroAdapter adapter;
    Player player;
    Game game;
    Team team;
    IcoButton[] heroButtons;
    Hero[] pickedHeroes = new Hero[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expedition_setup);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        heroButtons = new IcoButton[]{(IcoButton) findViewById(R.id.heroButton1), (IcoButton) findViewById(R.id.heroButton2),(IcoButton) findViewById(R.id.heroButton3),(IcoButton) findViewById(R.id.heroButton4)};

        for (ImageButton b:
             heroButtons) {
            b.setOnClickListener(this);
        }

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar2);
        final TextView textView = (TextView) findViewById(R.id.textView7);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText("" + progress);
                size = (int)(50f*progress/100f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        game = (Game) getIntent().getSerializableExtra("Game");

        heroes = game.reserve;

        adapter = new HeroAdapter(this, heroes);

        ListView listView = (ListView) findViewById(R.id.teamList);

        listView.setAdapter(adapter);
        player = PlayerConstructor.construct(new HumanIntellect());
        player.items.add(new FireSword());
        player.items.add(new GreatStaff());
        player.items.add(new FireSword());
        team = new Team();
        team.player = player;
        player.team = team;

        Button b = (Button) findViewById(R.id.teamButton);

        b.setOnClickListener(this);

        b = (Button) findViewById(R.id.startButton);

        b.setOnClickListener(this);
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

    public void addHero(View view) {
        HeroButton button = (HeroButton) view;
        Hero hero = button.hero;

        int index = 4;

        for (int i = 3; i > -1; i--) {
            if (pickedHeroes[i] == null) index = i;
        }

        if (index < 4) setHero(index, hero);
    }

    void setHero(int pos, Hero hero) {
        pickedHeroes[pos] = hero;
        heroButtons[pos].setHero(hero);
        Toast.makeText(this, hero.getName(), Toast.LENGTH_SHORT).show();
        heroes.remove(hero);
        adapter.notifyDataSetChanged();
    }

    void removeHero(IcoButton button) {
        Hero hero = button.hero;
        button.setHero(null);
        int index = 0;
        for (int i = 0; i < 4; i++) {
            if (pickedHeroes[i] == hero) index = i;
        }

        pickedHeroes[index] = null;
        heroes.add(hero);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        boolean t = false;
        for (int i = 0; i < 4; i++) {
            if (heroButtons[i] ==  v) t = true;
        }

        if (t) removeHero((IcoButton) v);

        if (v.getId() == R.id.teamButton) showTeam(v);

        if (v.getId() == R.id.startButton) startExpedition(v);
    }

    public void showTeam(View v) {
        team.clear();
        for (Hero hero :
                pickedHeroes) {
            if (hero!=null) {
                team.add(hero);
                hero.team = team;
            }
        }

        player.team = team;

        Intent intent = new Intent(this, TeamInspectorActivity.class);
        intent.putExtra("Player", player);
        startActivityForResult(intent, 1);
    }

    public void startExpedition(View v){

        int count = 0;

        for (Hero h : pickedHeroes) {
            if (h != null) count++;
        }

        if (count == 4) {

            team.clear();
            for (Hero hero :
                    pickedHeroes) {
                team.add(hero);
                hero.team = team;
            }

            player.team = team;

            Intent intent = new Intent(this, DungeonActivity.class);
            intent.putExtra("Size", size);
            intent.putExtra("startPlayer", player);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        player = (Player) data.getSerializableExtra("Player");
        Intent intent = new Intent();
        intent.putExtra("Player", player);
        setResult(1, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("Player", player);
        setResult(1, intent);
        finish();
    }
}
