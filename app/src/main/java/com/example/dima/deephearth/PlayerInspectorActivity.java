package com.example.dima.deephearth;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.Item;
import com.example.dima.deephearth.FromIdea.Player;
import com.example.dima.deephearth.FromIdea.PlayerSkill;
import com.example.dima.deephearth.FromIdea.Unit;

import java.util.LinkedList;

public class PlayerInspectorActivity extends AppCompatActivity implements View.OnClickListener{

    Player player;
    ItemAdapter itemAdapter;
    HeroAdapter heroAdapter;
    LinkedList<Hero> heroes = new LinkedList<>();
    ClipData currentData;
    int curCost = 0;
    double lastCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_inspector);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        player = (Player) getIntent().getSerializableExtra("Player");

        for (Unit unit :
                player.team) {
            heroes.add((Hero) unit);
        }

        heroAdapter = new HeroAdapter(this, heroes);
        itemAdapter = new ItemAdapter(this, player.items);

        ListView teamView = (ListView) findViewById(R.id.teamView);
        ListView inventoryView = (ListView) findViewById(R.id.inventoryView);
        teamView.setAdapter(heroAdapter);
        inventoryView.setAdapter(itemAdapter);
        inventoryView.setOnDragListener(new ItemViewDragListener());

        ProgressBar manaBar = (ProgressBar) findViewById(R.id.manaBar);
        TextView manaView = (TextView) findViewById(R.id.manaView);
        TextView soulView = (TextView) findViewById(R.id.soulView);
        manaBar.setMax(player.maxMp);
        manaBar.setProgress(player.mp);
        manaBar.getProgressDrawable().setColorFilter(
                Color.BLUE, PorterDuff.Mode.MULTIPLY);
        manaView.setText("" + player.mp);
        soulView.setText("" + player.souls);

        LinearLayout skillLayout = (LinearLayout) findViewById(R.id.skillLayout);

        for (int i = 0; i < 3; i++) {
            PlayerSkillButton b = (PlayerSkillButton) ((FrameLayout)skillLayout.getChildAt(i)).getChildAt(0);
            b.setPlayerSkill(player.skills.get(i));
        }

        Button button = (Button) findViewById(R.id.teamButton);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.finishExchangeButton);
        button.setOnClickListener(this);
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

    public void onItemClick(View v) {

    }

    public void onLongItemClick(View v) {
        Item item = ((ItemView) v).item;
        Intent intent = new Intent();
        intent.putExtra("Item", item);
        ClipData.Item cItem1 = new ClipData.Item(intent);
        ClipData clipdata = new ClipData(v.getTag().toString(), new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, cItem1);
        currentData = clipdata;

        View.DragShadowBuilder myShadow = new MyDragShadowBuilder(v);
        v.startDrag(clipdata, myShadow, null, 0);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("Player", player);
        setResult(1, intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.teamButton) {
            Intent intent = new Intent(this, TeamInspectorActivity.class);
            intent.putExtra("Player", player);
            startActivityForResult(intent, 1);
        }
        else if (v.getId() == R.id.finishExchangeButton) finishExchange(v);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        player = (Player) data.getSerializableExtra("Player");
    }

    class ItemViewDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {

            final int action = event.getAction();

            switch (action) {
                case DragEvent.ACTION_DRAG_ENDED :
                    if (event.getResult()) {
                        Item item = (Item) currentData.getItemAt(0).getIntent().getSerializableExtra("Item");
                        player.items.remove(item);
                        itemAdapter.notifyDataSetChanged();
                    }
                    break;
                default: break;
            }
            return true;
        }
    }

    public void onExchangeClick(View v) {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.exchangeLayout);
        if (relativeLayout.getVisibility() == View.INVISIBLE) {
            relativeLayout.setVisibility(View.VISIBLE);
            relativeLayout.setClickable(true);
            SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar4);
            seekBar.setMax(player.maxMp-player.mp);
            seekBar.setProgress(0);
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    TextView textView = (TextView) findViewById(R.id.textView12);
                    textView.setText(progress + "");
                    Button bu = (Button) findViewById(R.id.finishExchangeButton);
                    curCost = 0;
                    lastCost = player.lastMpCost;
                    for (int i = 0; i < progress; i++) {
                        curCost+=lastCost;
                        lastCost*=1.05;
                    }

                    String s = "Exchange for " + curCost + " souls";
                    bu.setText(s);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }
    }

    public void close(View v) {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.exchangeLayout);
        relativeLayout.setVisibility(View.INVISIBLE);
        relativeLayout.setClickable(false);
    }

    public void finishExchange(View v) {
        if (player.souls > curCost) {
            SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar4);
            int additionalMp = seekBar.getProgress();
            player.mp += additionalMp;
            player.souls -= curCost;
            player.lastMpCost = lastCost;
            ProgressBar manaBar = (ProgressBar) findViewById(R.id.manaBar);
            TextView manaView = (TextView) findViewById(R.id.manaView);
            TextView soulView = (TextView) findViewById(R.id.soulView);
            manaBar.setProgress(player.mp);
            manaView.setText("" + player.mp);
            soulView.setText("" + player.souls);
            close(v);
        }
    }

    public void displayHero(View v) {
        Intent intent = new Intent(this, HeroInspectorActivity.class);
        intent.putExtra("Hero", ((HeroButton) v).hero);
        startActivity(intent);
    }
}
