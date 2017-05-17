package com.example.dima.deephearth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.ListView;

import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.Heroes.Archer;
import com.example.dima.deephearth.FromIdea.Heroes.HeroConstructor;

import java.util.ArrayList;
import java.util.LinkedList;

public class HomeActivity extends AppCompatActivity {

    LinkedList<Hero> heroes = new LinkedList<>();
    HeroAdapter adapter;

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

        fillData();

        adapter = new HeroAdapter(this, heroes);

        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(adapter);
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

    void fillData() {
        int size = 20;

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
}
