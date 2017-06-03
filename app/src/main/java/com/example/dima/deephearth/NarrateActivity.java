package com.example.dima.deephearth;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dima.deephearth.FromIdea.Dialogue;
import com.example.dima.deephearth.FromIdea.Game;
import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.HeroNames;
import com.example.dima.deephearth.FromIdea.Heroes.HeroConstructor;
import com.example.dima.deephearth.FromIdea.HumanIntellect;
import com.example.dima.deephearth.FromIdea.Phrase;
import com.example.dima.deephearth.FromIdea.Player;
import com.example.dima.deephearth.FromIdea.PlayerSkills.Curse;
import com.example.dima.deephearth.FromIdea.PlayerSkills.Renewal;
import com.example.dima.deephearth.FromIdea.PlayerSkills.UndeadRage;

import java.util.LinkedList;
import java.util.concurrent.Phaser;

public class NarrateActivity extends AppCompatActivity {

    int sceneId = 0;
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_narate);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        sceneId = getIntent().getIntExtra("Scene Id", 0);
        TextWriter textWriter = (TextWriter) findViewById(R.id.textView9);
        textWriter.setMovementMethod(new ScrollingMovementMethod());
        textWriter.setBackgroundColor(0);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        switch (sceneId) {
            case 0 :
                final TextWriter textWriter = (TextWriter) findViewById(R.id.textView9);
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        onTextEnd(2);
                    }
                };
                textWriter.showPhrases(getPhrases(0), textWriter, runnable);
                break;
            case 1 :
                final TextWriter textWriter2 = (TextWriter) findViewById(R.id.textView9);
                Runnable runnable2 = new Runnable() {
                    @Override
                    public void run() {
                        onTextEnd(1);
                    }
                };
                textWriter2.showPhrases(getPhrases(1), textWriter2, runnable2);
                break;
        }
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

    public void onTextEnd(int id) {
        switch (id) {
            case 0 :
                Button button = (Button) findViewById(R.id.button6);
                button.setVisibility(View.VISIBLE);
                button.setText("Begin");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startGame();
                    }
                });
                break;
            case 1 :
                button = (Button) findViewById(R.id.button6);
                button.setVisibility(View.VISIBLE);
                button.setText("Next");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startDialogue();
                    }
                });
                break;
            case 2 :
                button = (Button) findViewById(R.id.button6);
                button.setVisibility(View.VISIBLE);
                button.setText("Next");
                final TextWriter textWriter = (TextWriter) findViewById(R.id.textView9);
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        onTextEnd(0);
                    }
                };
                textWriter.showPhrases(getPhrases(3), button, runnable);
                break;

        }
    }
    void startGame() {
        Intent intent = new Intent(this, DungeonActivity.class);
        intent.putExtra("Id", 0);
        HeroConstructor constructor = new HeroConstructor();

        Player player = new Player(new HumanIntellect());
        player.team.set(0, constructor.constructSwordsman(HeroNames.getName(), player.team));
        player.team.set(1, constructor.constructGunner(HeroNames.getName(), player.team));
        player.team.set(2, constructor.constructArcher(HeroNames.getName(), player.team));
        player.team.set(3, constructor.constructMage(HeroNames.getName(), player.team));
        player.team.player = player;
        player.skills.add(new Renewal(player));
        player.skills.add(new Curse(player));
        player.skills.add(new UndeadRage(player));
        intent.putExtra("startPlayer", player);
        startActivity(intent);
        killActivity();
    }
    void startDialogue() {
        Intent intent = new Intent(this, DialogueActivity.class);
        Dialogue dialogue = new Dialogue("You", "Lich", R.drawable.archer_ico, R.drawable.lich_ico);
        dialogue.phrases = getPhrases(2);
        intent.putExtra("Dialogue", dialogue);
        startActivity(intent);
        killActivity();
    }

    void killActivity() {finish();}

    LinkedList<Phrase> getPhrases(int id) {
        LinkedList<Phrase> phrases = new LinkedList<>();
        switch (id) {
            case 0 :
                phrases.add(new Phrase(1, getString(R.string.text_intro)));
                break;
            case 1 :
                phrases.add(new Phrase(1, "Lich uses very powerful magic. All of your team members are injured and are trying to escape.\n\n You try to run, but aching pain fills your body and you fall on the ground."));
                phrases.add(new Phrase(1, "Suddenly, lifeless voice, that sounds in your head, wakes you up."));
                break;
            case 2 :
                phrases.add(new Phrase(2, "Hey, are you still alive?"));
                phrases.add(new Phrase(1, "..."));
                phrases.add(new Phrase(2, "Well, at least, you show signs of life."));
                phrases.add(new Phrase(2, "Listen carefully. I have a deal."));
                phrases.add(new Phrase(2, "Neither you nor me are going to be alive in the next few hours."));
                phrases.add(new Phrase(2, "I used too much of my power in the last attack and now my body is crumbling."));
                phrases.add(new Phrase(2, "So..... I can offer you to lend me your body for my power."));
                phrases.add(new Phrase(2, "It will be enough to keep you and me alive, and you will keep all the control."));
                phrases.add(new Phrase(1, "..."));
                phrases.add(new Phrase(2, "I just need some vessel for me so I won't disappear"));
                phrases.add(new Phrase(1, "Well...."));
                phrases.add(new Phrase(1, "I do not care much about my humanity. And I don't want to die."));
                phrases.add(new Phrase(1, "I think, I agree."));
                break;
            case 3 :
                phrases.add(new Phrase(1, "This is a short tutorial.\nDungeon consists of rooms. You can enter rooms which are next to your current room (with skull on it). When you enter a new room, you can uncover next rooms with certain chance, otherwise, you won't know its content until you enter it. To move to next room, double click on it."));
                phrases.add(new Phrase(1, "When you enter the battle you can use your units' skills and your player's skills. To use unit skill select it and double click on available target. If you want to use player skill, then you should press button in the right bottom corner and select skill. They can be very useful. Button with arrows on it allows you to change your formation."));
                phrases.add(new Phrase(1, "That is all."));
                break;
            default: break;
        }
        return phrases;
    }
}
