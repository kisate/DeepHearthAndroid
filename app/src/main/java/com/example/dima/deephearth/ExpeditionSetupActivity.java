package com.example.dima.deephearth;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dima.deephearth.FromIdea.Dungeon.Dungeon;
import com.example.dima.deephearth.FromIdea.Dungeon.DungeonSizes;
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
import com.example.dima.deephearth.FromIdea.Unit;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class ExpeditionSetupActivity extends AppCompatActivity implements View.OnClickListener{

    DungeonSizes size = DungeonSizes.Tiny;
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
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView sizeView = (TextView) findViewById(R.id.sizeView);
                switch (progress / 17) {
                    case 0 :
                        size = DungeonSizes.Tiny;
                        break;
                    case 1 :
                        size = DungeonSizes.Small;
                        break;
                    case 2 :
                        size = DungeonSizes.Normal;
                        break;
                    case 3 :
                        size = DungeonSizes.Big;
                        break;
                    case 4 :
                        size = DungeonSizes.Large;
                        break;
                    case 5 :
                        size = DungeonSizes.Huge;
                        break;
                }

                sizeView.setText(size.toString());
                sizeView.setTextColor(size.color);
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
        player = game.player;
        team = new Team(4);
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

        for (int i = 0; i < 4; i++) {
            if (team.get(i) == null) index = i;
        }

        if (index < 4) setHero(index, hero);
    }

    void setHero(int pos, Hero hero) {
        team.set(pos, hero);
        hero.team = team;
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
            if (team.get(i) == hero) index = i;
        }

        team.set(index, null);
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

        player.team = team;
        Intent intent = new Intent(this, TeamInspectorActivity.class);
        intent.putExtra("Player", player);
        startActivityForResult(intent, 1);
    }

    public void startExpedition(View v){

        int count = 0;

        for (Unit u : team) {
            if (u != null) count++;
        }

        if (count == 4) {

            for (Unit u :
                    team) {
                u.team = team;
            }

            player.team = team;
            Intent intent = new Intent(this, DungeonActivity.class);
            intent.putExtra("Size", size.getSize());
            intent.putExtra("startPlayer", player);
            intent.putExtra("Id", 1);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        player = (Player) data.getSerializableExtra("Player");
        if (data.getBooleanExtra("finished", false)) {
            Intent intent = new Intent();
            intent.putExtra("Player", player);
            intent.putExtra("finished", true);
            setResult(1, intent);
            killActivity();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("Player", player);
        setResult(1, intent);
        killActivity();
    }

    void killActivity() {finish();}
}
