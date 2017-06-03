package com.example.dima.deephearth;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.ArraySet;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.dima.deephearth.FromIdea.IntellectTypes;
import com.example.dima.deephearth.FromIdea.Player;
import com.example.dima.deephearth.FromIdea.PlayerSkill;
import com.example.dima.deephearth.FromIdea.Skills.Swap;
import com.example.dima.deephearth.FromIdea.SpeedComparator;
import com.example.dima.deephearth.FromIdea.Team;
import com.example.dima.deephearth.FromIdea.Unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class TeamInspectorActivity extends AppCompatActivity implements View.OnClickListener{

    Player player;
    UnitButton curUButton, movingButton;
    SkillButton curSButton;
    SkillButton[] skillButtons;
    private SkillDescLayout skillDescLayout;
    private int posWidth, indent;
    boolean moving = false;
    ArrayList<UnitButton> playerButtons = new ArrayList<>();
    CustomRelativeLayout bl;
    private PlayerSkillButton[] playerSkillButtons;
    private boolean pSkillPicked = false;
    private PlayerSkillButton curPSButton;
    private boolean skillPicked = false;
    private PlayerSkill pickedPlayerSkill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        player = (Player) getIntent().getSerializableExtra("Player");

        skillButtons = new SkillButton[]{(SkillButton) findViewById(R.id.skillButton1),(SkillButton) findViewById(R.id.skillButton2),
                (SkillButton) findViewById(R.id.skillButton3), (SkillButton) findViewById(R.id.skillButton4), (SkillButton) findViewById(R.id.moveSkillButton)};

        skillButtons[4].setSkill(new Swap(null));

        skillDescLayout = (SkillDescLayout) findViewById(R.id.skillDescLayout);
        playerSkillButtons = new PlayerSkillButton[]{(PlayerSkillButton) findViewById(R.id.playerSkillButton1), (PlayerSkillButton) findViewById(R.id.playerSkillButton2), (PlayerSkillButton) findViewById(R.id.playerSkillButton3)};

        for (int i = 0; i < 3; i++) {
            playerSkillButtons[i].setPlayerSkill(player.skills.get(i));
            playerSkillButtons[i].setOnClickListener(this);
            playerSkillButtons[i].setTag("pSkill");
        }

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int scWidth  = size.x;
        posWidth = (int) (scWidth/8.5);

        bl = (CustomRelativeLayout) findViewById(R.id.battleLayout);

        int counter = 1;

        for (Unit u :
                player.team) {
            if (u != null) counter++;
        }

        indent = scWidth/counter;

        setUpPlayerButtons();

        for (SkillButton button :
                skillButtons) {
            button.setOnClickListener(this);
            button.setTag("skill");
        }


        bl = (CustomRelativeLayout) findViewById(R.id.battleLayout);

        ImageButton playerButton = (ImageButton) findViewById(R.id.playerSkillButton);
        playerButton.setOnClickListener(this);

        ReverseProgressBar pb1 = (ReverseProgressBar) findViewById(R.id.playerManaBar1);
        ProgressBar pb2 = (ProgressBar) findViewById(R.id.playerManaBar2);

        pb1.getProgressDrawable().setColorFilter(
                Color.BLUE, PorterDuff.Mode.SRC_ATOP);

        pb2.getProgressDrawable().setColorFilter(
                Color.BLUE, PorterDuff.Mode.SRC_ATOP);

        pb1.setMax(player.maxMp);
        pb2.setMax(player.maxMp);
        updatePlayerMana(player.mp);

        for (int i = 0; i < 4; i++) {
            if (playerButtons.get(i) != null) {
                uButtonClick(playerButtons.get(i));
                break;
            }
        }
    }

    private void setUpPlayerButtons() {

        for (UnitButton b : playerButtons) {
            if (b != null){
                bl.removeView((View) b.parentLayout.getParent());
            }
        }

        playerButtons.clear();

        for (int i = 0; i < 4; i++) {
            playerButtons.add(null);
        }

        for (int i = 0; i < player.team.size(); i++) {
            if (player.team.get(i) != null) {
                Unit unit = player.team.get(i);

                RelativeLayout rl = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.unitbutton, bl, false);
                UnitButton button = (UnitButton) rl.findViewById(R.id.unitButton);

                button.unitLayout = (UnitLayout) findViewById(R.id.unitLayout);
                button.setOnClickListener(this);
                button.friendly = true;
                button.setUnit(unit, posWidth);
                button.setTag("unit");
                unit.button = button;
                playerButtons.add(button);
                bl.addView(rl);

                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rl.getLayoutParams();
                int margin = indent * (player.team.size() - i);
                layoutParams.setMarginStart(margin);
                layoutParams.setMargins(margin, 0, 0, 0);
            }
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

    @Override
    public void onClick(View v) {
        if (v.getTag() != null) switch (v.getTag().toString()) {
            case "unit" :
                uButtonClick((UnitButton) v);
                break;
            case "skill" :
                sButtonClick((SkillButton) v);
                break;
            case "pSkill" :
                playerSkillButtonClick((PlayerSkillButton) v);
                break;
            default: break;
        }

        if (v.getId() == R.id.playerSkillButton) {
            playerSkillDescClick(v);
        }
    }

    private void playerSkillButtonClick(PlayerSkillButton v) {

        if(!pSkillPicked || pSkillPicked&&(v != curPSButton)) {
            if (curPSButton != null) curPSButton.setCurrent(false);
            curPSButton = v;
            PlayerSkillDescLayout layout = (PlayerSkillDescLayout) findViewById(R.id.playerSkillDesc);
            layout.setSkill(v.playerSkill);

            if (v.usable) {
                curPSButton.setCurrent(true);
                pSkillPicked = true;
                pickedPlayerSkill = curPSButton.playerSkill;
            }
        }

        else {
            curPSButton.setCurrent(false);
            curPSButton = null;
            pSkillPicked = false;
            pickedPlayerSkill = null;
        }
    }

    public  void sButtonClick(SkillButton v){

        if (pSkillPicked) interruptPlayerSkill();


    }

    private void uButtonClick(UnitButton v) {

        if (moving && curUButton != null) {
            swapUnits(curUButton, v);
            moving = false;
            for (int i = 0; i < 4; i++) {
                UnitButton b = playerButtons.get(i);
                if (b != null) b.setCanBeTarget(false);
            }
        }
        else {
            if (curUButton != null) {
                curUButton.setPicked(false);
                bl.moveChildToBack((View) (curUButton.getParent().getParent().getParent()));
            }
            if (curUButton != v) {
                curUButton = v;
                bl.bringChildToFront((View) v.getParent().getParent().getParent());
                v.UpdateInfo();
                curUButton.setPicked(true);
            } else curUButton = null;
        }
    }

    public LinkedList<UnitButton> swapUnits(UnitButton u1, UnitButton u2) {
        ArrayList<UnitButton> buttons;

        buttons = playerButtons;

        int index1 = buttons.indexOf(u1);
        int index2 = buttons.indexOf(u2);

        UnitButton[] temp = new UnitButton[4];

        for (int i = 0; i < 4; i++) {
            temp[i] = buttons.get(i);
        }

        if (index1 > index2) {
            System.arraycopy(temp, index2, temp, index2 + 1, index1 - index2);
            temp[index2] = u1;
        } else {
            System.arraycopy(temp, index1 + 1, temp, index1, index2 - index1);
            temp[index2] = u1;
        }

        buttons.clear();

        Collections.addAll(buttons, temp);

        Team team = u1.unit.team;

        for (int i = 0; i < 4; i++) {
            team.set(i, null);
        }

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != null) team.set(i, temp[i].unit);
        }

        int kwidth = ContextCompat.getDrawable(this, R.drawable.knight).getIntrinsicWidth();

        LinkedList<UnitButton> res = new LinkedList<>();

        for (int i = 0; i < 4; i++) {
            UnitButton b = buttons.get(i);
            if (b != null) {
                Drawable d = ContextCompat.getDrawable(this, b.unit.spriteIds.get("idle"));

                RelativeLayout rl = (RelativeLayout) b.parentLayout.getParent();

                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rl.getLayoutParams();
                int margin = indent * (4 - i);
                layoutParams.setMarginStart(margin);
                layoutParams.setMargins(margin, 0, 0, 0);
                res.add(b);
            }
        }

        return res;
    }

    void playerSkillDescClick(View v) {
        LinearLayout playerSkillLayout = (LinearLayout) findViewById(R.id.playerSkillLayout);
        if (playerSkillLayout.getVisibility() == View.VISIBLE) {
            playerSkillLayout.setVisibility(View.INVISIBLE);
            ImageButton b = (ImageButton) findViewById(R.id.playerSkillButton);
            if (pSkillPicked) b.setImageResource(curPSButton.playerSkill.skillIco);
            PlayerSkillDescLayout layout = (PlayerSkillDescLayout) findViewById(R.id.playerSkillDesc);
            layout.setVisibility(View.GONE);
        }
        else {
            playerSkillLayout.setVisibility(View.VISIBLE);
            interruptSkill();
            interruptPlayerSkill();
            for (PlayerSkillButton b :
                    playerSkillButtons) {
            }
        }
    }

    public void selectPlayerSkill(View v) {
        LinearLayout playerSkillLayout = (LinearLayout) findViewById(R.id.playerSkillLayout);
        if (playerSkillLayout.getVisibility() == View.VISIBLE) {
            playerSkillLayout.setVisibility(View.INVISIBLE);
            ImageButton b = (ImageButton) findViewById(R.id.playerSkillButton);
            if (pSkillPicked) b.setImageResource(curPSButton.playerSkill.skillIco);
            PlayerSkillDescLayout layout = (PlayerSkillDescLayout) findViewById(R.id.playerSkillDesc);
            layout.setVisibility(View.GONE);
        }
    }

    void interruptPlayerSkill(){
        if (pSkillPicked){
            if (curPSButton != null) curPSButton.setCurrent(false);
            pSkillPicked = false;
            curPSButton = null;
            curUButton = null;
            ImageButton b = (ImageButton) findViewById(R.id.playerSkillButton);
            b.setImageResource(R.drawable.skillico);
        }
    }

    void interruptSkill(){
        if (skillPicked) {
            curSButton.setCurrent(false);
            skillPicked = false;
            curSButton = null;
            curUButton = null;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        for (Unit u :
                player.team) {
            if (u != null) u.button = null;
        }
        intent.putExtra("Player", player);
        setResult(1, intent);
        killActivity();
    }

    void killActivity() {
        finish();
    }


    public void closeDesc(View v){
        ((View) skillDescLayout.getParent()).setVisibility(View.INVISIBLE);
        skillDescLayout.setClickable(false);
    }

    void updatePlayerMana(int mana){
        ReverseProgressBar pb1 = (ReverseProgressBar) findViewById(R.id.playerManaBar1);
        ProgressBar pb2 = (ProgressBar) findViewById(R.id.playerManaBar2);
        TextView pmv = (TextView) findViewById(R.id.playerManaView);

        ProgressBarAnimation anim1 = new ProgressBarAnimation(pb1, pb1.getProgress(), mana);
        anim1.setDuration(300);
        pb1.startAnimation(anim1);

        ProgressBarAnimation anim2 = new ProgressBarAnimation(pb2, pb2.getProgress(), mana);
        anim2.setDuration(300);
        pb2.startAnimation(anim2);

        pmv.setText("" + mana);

        pmv.setTextColor(Color.YELLOW);
    }

    public class ProgressBarAnimation extends Animation {
        private ProgressBar progressBar;
        private float from;
        private float  to;

        public ProgressBarAnimation(ProgressBar progressBar, float from, float to) {
            super();
            this.progressBar = progressBar;
            this.from = from;
            this.to = to;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            float value = from + (to - from) * interpolatedTime;
            progressBar.setProgress((int) value);
        }

    }
}
