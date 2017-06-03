package com.example.dima.deephearth;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dima.deephearth.FromIdea.Dialogue;
import com.example.dima.deephearth.FromIdea.Game;

public class DialogueActivity extends AppCompatActivity {

    Dialogue dialogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogue);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        dialogue = (Dialogue) getIntent().getSerializableExtra("Dialogue");
        ImageView icoView1 = (ImageView) findViewById(R.id.icoView1);
        ImageView icoView2 = (ImageView) findViewById(R.id.icoView2);
        TextView nameView1 = (TextView) findViewById(R.id.nameView1);
        TextView nameView2 = (TextView) findViewById(R.id.nameView2);
        TextWriter textWriter1 = (TextWriter) findViewById(R.id.textWriter1);
        TextWriter textWriter2 = (TextWriter) findViewById(R.id.textWriter2);

        icoView1.setImageResource(dialogue.ico1);
        icoView2.setImageResource(dialogue.ico2);

        nameView1.setText(dialogue.name1);
        nameView2.setText(dialogue.name2);

        dialogue.setWriters(textWriter1, textWriter2);
        dialogue.setActivity(this);

        ((Button) findViewById(R.id.finishButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnHome();
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        final RelativeLayout fadeLayout = (RelativeLayout) findViewById(R.id.fadeLayout);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(fadeLayout, "alpha", 1f, 0f);
        objectAnimator.setDuration(2000);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                fadeLayout.setVisibility(View.GONE);
                dialogue.startDialogue();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimator.start();
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

    public void onDialogueEnd() {
        findViewById(R.id.finishButton).setVisibility(View.VISIBLE);
    }

    public void returnHome(){
        Game game = new Game();
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("Game", game);
        intent.putExtra("tutorial", true);
        game.unlockedBuildings.put("Inn", true);
        startActivity(intent);
        killActivity();
    }

    void killActivity() {
        finish();
    }
}
