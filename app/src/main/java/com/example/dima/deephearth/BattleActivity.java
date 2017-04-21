package com.example.dima.deephearth;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.util.Pair;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dima.deephearth.FromIdea.Battle;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.IntellectTypes;
import com.example.dima.deephearth.FromIdea.Player;
import com.example.dima.deephearth.FromIdea.SpeedComparator;
import com.example.dima.deephearth.FromIdea.Team;
import com.example.dima.deephearth.FromIdea.Unit;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Created by Dima on 11.04.2017.
 */

public class BattleActivity extends AppCompatActivity implements View.OnClickListener{

    //TODO : setUnit, setSkill methods, rework hp and mp scaling

    public Battle battle;
    Player player1, player2;
    LinkedList<UnitButton> moveOrder = new LinkedList<>();
    LinkedList<UnitButton> playerButtons = new LinkedList<>(), enemyButtons = new LinkedList<>();
    SkillButton[] skillButtons;
    int curNumber;
    BattleStates state = BattleStates.Init;

    UnitButton curUButton, activeButton;
    SkillButton curSButton;

    public static TextView statusTextView;

    LinearLayout[] uButLayouts;

    UnitButton[] buttons1;

    UnitButton[] buttons2;

    public boolean skillPicked = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
        player1 = (Player) getIntent().getSerializableExtra("Player 1");
        player2 = (Player) getIntent().getSerializableExtra("Player 2");

        skillButtons = new SkillButton[]{(SkillButton) findViewById(R.id.skillButton1),(SkillButton) findViewById(R.id.skillButton2),
                (SkillButton) findViewById(R.id.skillButton3), (SkillButton) findViewById(R.id.skillButton4)};


        statusTextView = (TextView) findViewById(R.id.textView6);

        statusTextView.setMovementMethod(new ScrollingMovementMethod());

        uButLayouts = new LinearLayout[]{(LinearLayout) findViewById(R.id.LinearLayout1),(LinearLayout) findViewById(R.id.LinearLayout2),(LinearLayout) findViewById(R.id.LinearLayout3),(LinearLayout) findViewById(R.id.LinearLayout4),
                (LinearLayout) findViewById(R.id.LinearLayout5),(LinearLayout) findViewById(R.id.LinearLayout6),(LinearLayout) findViewById(R.id.LinearLayout7),(LinearLayout) findViewById(R.id.LinearLayout8)};

        buttons1 = new UnitButton[]{(UnitButton) findViewById(R.id.UnitButton1),(UnitButton) findViewById(R.id.UnitButton2),
                (UnitButton) findViewById(R.id.UnitButton3), (UnitButton) findViewById(R.id.UnitButton4)};

        buttons2 = new UnitButton[]{(UnitButton) findViewById(R.id.UnitButton5),(UnitButton) findViewById(R.id.UnitButton6),
                (UnitButton) findViewById(R.id.UnitButton7), (UnitButton) findViewById(R.id.UnitButton8)};

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int scHeight = size.y;
        int scWidth  = size.x;

        int maxWidth = (int) (scWidth/8.5);

        int kwidth = ContextCompat.getDrawable(this, R.drawable.knight).getIntrinsicWidth();

        for (int i = player1.team.size()-1; i > -1 ; i--) {
            Unit unit = player1.team.get(3-i);
            UnitButton button = buttons1[i];
            button.unitLayout = (UnitLayout) findViewById(R.id.unitLayout);
            button.setOnClickListener(this);
            button.setUnit(unit, maxWidth);
            playerButtons.add(button);

            Drawable d = ContextCompat.getDrawable(this, unit.spriteId);
            int width = (int)(maxWidth*d.getIntrinsicWidth()/kwidth);

            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) uButLayouts[i].getLayoutParams();
            int margin = maxWidth*i - maxWidth*(d.getIntrinsicWidth() - kwidth)/2/kwidth;
            layoutParams.setMarginStart(margin);
            layoutParams.setMargins(margin, 0, 0, 0);
        }

        for (int i = player2.team.size()-1; i > -1; i--) {
            Unit unit = player2.team.get(3-i);
            UnitButton button = buttons2[i];
            button.unitLayout = (UnitLayout) findViewById(R.id.unitLayout);
            button.setOnClickListener(this);
            button.setUnit(unit, maxWidth);
            enemyButtons.add(button);

            Drawable d = ContextCompat.getDrawable(this, unit.spriteId);
            int width = (int)(maxWidth*d.getIntrinsicWidth()/ContextCompat.getDrawable(this, R.drawable.knight).getIntrinsicWidth());

            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) uButLayouts[i + 4].getLayoutParams();
            int margin = maxWidth*(i) - maxWidth*(d.getIntrinsicWidth() - kwidth)/2/kwidth;
            layoutParams.setMarginEnd(margin);
            layoutParams.setMargins(0, 0, margin, 0);
        }
        for (SkillButton button :
                skillButtons) {
            button.setOnClickListener(this);
        }
        uButtonClick(curUButton);
        ((ImageView) findViewById(R.id.imageView)).setImageResource(player1.team.get(0).icoId);

        for (UnitButton b :
                buttons1) {
            moveOrder.add(b);
        }
        for (UnitButton b :
                buttons2) {
            moveOrder.add(b);
        }

        Collections.sort(moveOrder, new SpeedComparator());

        curNumber = -1;

        battle = createBattle();
        nextMove();
    }

    private void uButtonClick(UnitButton v) {

        if(skillPicked) {useSkill(new Pair(v, curSButton)); skillPicked = false; v.UpdateInfo(); nextMove();}

        else {

            if (curUButton != null) {
                curUButton.setPressed(false);
                curUButton.foreground.setVisibility(View.INVISIBLE);
                CustomRelativeLayout layout = (CustomRelativeLayout) (v.getParent().getParent().getParent());
                layout.moveChildToBack((View) (curUButton.getParent().getParent()));
            }
            if (!(curUButton == v)) {
                curUButton = v;
                v.getParent().getParent().getParent().bringChildToFront((View) v.getParent().getParent());
                v.UpdateInfo();
                v.foreground.setVisibility(View.VISIBLE);
                curUButton.setPressed(true);
            } else curUButton = null;
        }
    }

    public void onClick2(View v){
        finish();
    }

    @Override
    public void onBackPressed() {
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
        int[] butidS = {R.id.UnitButton1, R.id.UnitButton2, R.id.UnitButton3, R.id.UnitButton4, R.id.UnitButton5, R.id.UnitButton6, R.id.UnitButton7, R.id.UnitButton8};
        boolean t = false;
        for (int a :
                butidS) {
            if(a == v.getId()) t = true;
        }

        if (t && (v.getVisibility() == View.VISIBLE)) {
            uButtonClick((UnitButton) v);
        }

        int[] skillIds = {R.id.skillButton1, R.id.skillButton2, R.id.skillButton3, R.id.skillButton4};

        t = false;

        for (int a :
                skillIds) {
            if(a == v.getId()) t = true;
        }
        if (t) {
            sButtonClick((SkillButton) v);
        }
    }

    public  void sButtonClick(SkillButton v){
        if(!skillPicked) {
            curSButton = v;
            skillPicked = true;
        }

        else {
            curSButton = null;
            skillPicked = false;
        }
    }

    void nextMove(){

        if (playerButtons.size() == 0) defeat();

        else if (enemyButtons.size() == 0) victory();

        else if (curNumber < moveOrder.size() - 1) {
            curNumber++;
            if(activeButton != null) activeButton.movePointLayout.getChildAt(0).setVisibility(View.INVISIBLE);
            setActiveButton(moveOrder.get(curNumber));
            activeButton.unit.applyEffects();
            activeButton.UpdateInfo();
            if (activeButton.unit.team.player.intellect.type == IntellectTypes.Human) state = BattleStates.PlayerMove;
            else {
                state = BattleStates.EnemyMove;
                useSkill(activeButton.unit.team.player.intellect.think(battle));
                skillPicked = false;
                nextMove();
            }
        }

        else nextTurn();
    }

    void defeat(){

    }

    void victory(){

    }

    void nextTurn(){
        curNumber = 0;
        for (UnitButton b :
                playerButtons) {
            b.UpdateInfo();
        }

        for (UnitButton b :
                enemyButtons) {
            b.UpdateInfo();
        }
        nextMove();
    }

    void useSkill(Pair<UnitButton, SkillButton> data){
        writeStatus("");
        if (data.second.skill.owner.mana - data.second.skill.cost >= 0) data.second.useSkill(data.first);
        else writeStatus("Not enough mana");
        if (data.first.unit.isDead) {
            moveOrder.remove(data.first);
            data.first.setBackgroundResource(R.color.colorPrimaryDark);
        }
    }

    void setActiveButton(UnitButton activeButton){
        this.activeButton = activeButton;
        activeButton.movePointLayout.getChildAt(0).setVisibility(View.VISIBLE);
        uButtonClick(activeButton);
    }

    public static void writeStatus(String text) {
        String s = statusTextView.getText().toString();

        String[] temp = s.split("\n");

        if ((temp.length < 6) && (text != "") && (s != "")) {text = s + "\n" + text; Log.d("Debug", text);}
        statusTextView.setText(text);
        if (text == "") statusTextView.setText("");
    }

    Battle createBattle(){
        Battle battle = new Battle();
        battle.activeButton = activeButton;
        battle.moveOrder = moveOrder;
        battle.curNumber = curNumber;
        battle.enemyButtons = enemyButtons;
        battle.playerButtons = playerButtons;
        battle.state = state;
        battle.player1 = player1;
        battle.player2 = player2;
        battle.skillButtons = skillButtons;
        return battle;
    }
}
