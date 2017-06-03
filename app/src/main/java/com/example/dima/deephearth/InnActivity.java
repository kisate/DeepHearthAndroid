package com.example.dima.deephearth;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.dima.deephearth.FromIdea.Game;
import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.Item;

import java.util.LinkedList;
import java.util.List;

public class InnActivity extends AppCompatActivity {

    Game game;
    HeroAdapter heroAdapter, hireAdapter, kickAdapter;
    HeroAdapter from;
    LinkedList<Hero> availableHeroes;
    ClipData curData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inn);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        game = (Game) getIntent().getSerializableExtra("Game");
        availableHeroes = game.availableHeroes;
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

    public void onHireClick(View v) {
        RelativeLayout hireLayout = (RelativeLayout) findViewById(R.id.hireLayout);
        ListView heroList = (ListView) findViewById(R.id.heroList1);
        ListView hireList = (ListView) findViewById(R.id.hireList);

        heroList.setOnDragListener(new HeroDragEventListener());
        hireList.setOnDragListener(new HeroDragEventListener());

        heroAdapter = new HeroAdapter(this, game.reserve);
        hireAdapter = new HeroAdapter(this, availableHeroes);

        heroList.setAdapter(heroAdapter);
        hireList.setAdapter(hireAdapter);

        hireLayout.setVisibility(View.VISIBLE);

    }

    public void onKickClick(View v) {
        RelativeLayout kickLayout = (RelativeLayout) findViewById(R.id.kickLayout);

        ListView heroList = (ListView) findViewById(R.id.heroList2);
        ListView kickList = (ListView) findViewById(R.id.kickList);

        heroList.setOnDragListener(new HeroDragEventListener());
        kickList.setOnDragListener(new HeroDragEventListener());

        heroAdapter = new HeroAdapter(this, game.reserve);
        kickAdapter = new HeroAdapter(this, new LinkedList<Hero>());

        heroList.setAdapter(heroAdapter);
        kickList.setAdapter(kickAdapter);

        kickLayout.setVisibility(View.VISIBLE);
    }

    class HeroDragEventListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            final  int action = event.getAction();

            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED :
                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN) && event.getClipDescription().getLabel().equals(HeroAdapter.heroTag) && from != ((ListView) v).getAdapter()) {
                        v.setBackgroundColor(Color.GRAY);
                        v.invalidate();
                        return true;
                    }

                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundColor(0);
                    v.invalidate();
                    return true;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundColor(Color.GRAY);
                    v.invalidate();
                    return true;
                case DragEvent.ACTION_DROP:
                    v.setBackgroundColor(0);
                    Hero hero = (Hero) event.getClipData().getItemAt(0).getIntent().getSerializableExtra("Hero");
                    HeroAdapter a = (HeroAdapter) ((ListView) v).getAdapter();
                    a.objects.add(hero);
                    a.notifyDataSetChanged();
                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundColor(0);
                    hero = (Hero) curData.getItemAt(0).getIntent().getSerializableExtra("Hero");
                    if (event.getResult()) from.objects.remove(hero);
                    from.notifyDataSetChanged();
                    return true;
                default:
                    break;
            }

            return false;
        }
    }

    public void onLongHeroClick(View v) {
        HeroButton button = (HeroButton) v;
        Hero hero = button.hero;
        Intent intent = new Intent();
        intent.putExtra("Hero", hero);
        ClipData.Item cItem1 = new ClipData.Item(intent);
        ClipData clipdata = new ClipData(v.getTag().toString(), new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, cItem1);
        curData = clipdata;
        boolean t = true;
        switch (((View)button.getParent().getParent()).getId()) {
            case R.id.hireList:
                from = hireAdapter;
                break;
            case R.id.heroList1:
                t = false;
                break;
            case R.id.kickList:
                from = kickAdapter;
                break;
            case R.id.heroList2:
                from = heroAdapter;
                break;
            default:
               break;
        }

        if (t) {
            View.DragShadowBuilder myShadow = new MyDragShadowBuilder(v);
            v.startDrag(clipdata, myShadow, null, 0);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("Game", game);
        intent.putExtra("Inn", true);
        setResult(1, intent);
        killActivity();
    }

    void killActivity() {
        finish();
    }
}
