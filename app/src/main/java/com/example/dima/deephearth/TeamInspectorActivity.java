package com.example.dima.deephearth;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.dima.deephearth.FromIdea.IntellectTypes;
import com.example.dima.deephearth.FromIdea.Player;
import com.example.dima.deephearth.FromIdea.Skills.Swap;
import com.example.dima.deephearth.FromIdea.Team;
import com.example.dima.deephearth.FromIdea.Unit;

import java.util.LinkedList;

public class TeamInspectorActivity extends AppCompatActivity implements View.OnClickListener{

    Player player;
    UnitButton curUButton, movingButton;
    SkillButton curSButton;
    UnitButton[] buttons;
    SkillButton[] skillButtons;
    private SkillDescLayout skillDescLayout;
    private int posWidth, indent;
    boolean isMoving = false;
    LinkedList<UnitButton> playerButtons = new LinkedList<>();
    CustomRelativeLayout bl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_inspector);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        Intent intent = getIntent();
        player = (Player) intent.getSerializableExtra("Player");

        buttons = new UnitButton[]{(UnitButton) findViewById(R.id.UnitButton1),(UnitButton) findViewById(R.id.UnitButton2),
                (UnitButton) findViewById(R.id.UnitButton3), (UnitButton) findViewById(R.id.UnitButton4)};
        skillDescLayout = (SkillDescLayout) findViewById(R.id.skillDescLayout);

        skillButtons = new SkillButton[]{(SkillButton) findViewById(R.id.skillButton1),(SkillButton) findViewById(R.id.skillButton2),
                (SkillButton) findViewById(R.id.skillButton3), (SkillButton) findViewById(R.id.skillButton4), (SkillButton) findViewById(R.id.moveSkillButton)};

        skillButtons[4].setSkill(new Swap(null));

        for (SkillButton s :
                skillButtons) {
            s.setOnClickListener(this);
            s.usable = true;
        }

        bl = (CustomRelativeLayout) findViewById(R.id.battleLayout);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int scWidth  = size.x;

        posWidth = (int) (scWidth/8.5);
        indent = (int) (scWidth/(1 + player.team.size()));

        int kwidth = ContextCompat.getDrawable(this, R.drawable.knight).getIntrinsicWidth();

        for (int i = 0; i < player.team.size(); i++) {
            Unit unit = player.team.get(i);
            UnitButton button = buttons[i];
            button.unitLayout = (UnitLayout) findViewById(R.id.unitLayout);
            button.setOnClickListener(this);
            button.friendly = true;
            button.setUnit(unit, posWidth);
            playerButtons.add(button);

            Drawable d = ContextCompat.getDrawable(this, unit.spriteIds.get("idle"));
            int width = (int)(posWidth*d.getIntrinsicWidth()/kwidth);

            RelativeLayout rl = (RelativeLayout) button.parentLayout.getParent();

            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rl.getLayoutParams();
            int margin = indent*(player.team.size()-i);
            layoutParams.setMarginStart(margin);
            layoutParams.setMargins(margin, 0, 0, 0);
        }

        uButtonClick(playerButtons.get(0));
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

        int[] skillIds = {R.id.skillButton1, R.id.skillButton2, R.id.skillButton3, R.id.skillButton4, R.id.moveSkillButton};

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
        if (curSButton == v) {
            v.setCurrent(false);
            curSButton = null;
            isMoving = false;
        }
        else {
            if (curSButton != null) {
                curSButton.setCurrent(false);
                curSButton = null;
            }
            v.setCurrent(true);
            curSButton = v;
            if (v.getId() != R.id.moveSkillButton) {
                skillDescLayout.setSkill(v.skill);
                skillDescLayout.setVisibility(View.VISIBLE);
            }
            if (v.getId() == R.id.moveSkillButton){
                curSButton.setCurrent(false);
                isMoving = false;
                v.setCurrent(true);
                curSButton = v;
                if (v.getId() != R.id.moveSkillButton) {
                    skillDescLayout.setSkill(v.skill);
                    skillDescLayout.setVisibility(View.VISIBLE);
                }
                if (v.getId() == R.id.moveSkillButton) {
                    for (UnitButton b :
                            playerButtons) {
                        b.setCanBeTarget(true);
                    }

                    curUButton.setCanBeTarget(false);
                    isMoving = true;
                    movingButton = curUButton;
                }
            }
        }
    }

    private void uButtonClick(UnitButton v) {

        if (curUButton != null) {
            curUButton.setPicked(false);
            bl.moveChildToBack((View) (curUButton.getParent().getParent().getParent()));
        }
        curUButton = v;
        bl.bringChildToFront((View) v.getParent().getParent().getParent());
        v.UpdateInfo();
        curUButton.setPicked(true);
        if (curSButton != null) {
            curSButton.setCurrent(false);
            curSButton = null;
        }
        if (isMoving) {
            swapUnits(movingButton, v);
            for (UnitButton b :
                    playerButtons) {
                b.setCanBeTarget(false);
            }
            isMoving = false;
            uButtonClick(movingButton);
            movingButton = null;
        }
    }

    public void closeDesc(View view) {
        skillDescLayout.setVisibility(View.INVISIBLE);
    }

    public LinkedList<UnitButton> swapUnits(UnitButton u1, UnitButton u2){
        Team team = u1.unit.team;
        LinkedList<UnitButton> buttons;
        team.remove(u1.unit);
        team.add(team.indexOf(u2.unit), u1.unit);
        int kwidth = ContextCompat.getDrawable(this, R.drawable.knight).getIntrinsicWidth();
        buttons = playerButtons;
        buttons.remove(u1);
        buttons.add(buttons.indexOf(u2), u1);
        for (int i = 0; i < buttons.size(); i++) {

            UnitButton b = buttons.get(i);

            Drawable d = ContextCompat.getDrawable(this, b.unit.spriteIds.get("idle"));

            RelativeLayout rl = (RelativeLayout) b.parentLayout.getParent();

            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rl.getLayoutParams();
            int margin = indent*(player.team.size()-i);
            layoutParams.setMarginStart(margin);
            layoutParams.setMargins(margin, 0, 0, 0);
        }
        return buttons;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("Player", player);
        setResult(1, intent);
        finish();
    }
}
