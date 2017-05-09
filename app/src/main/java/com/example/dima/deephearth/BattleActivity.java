package com.example.dima.deephearth;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.util.Pair;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.dima.deephearth.FromIdea.Battle;
import com.example.dima.deephearth.FromIdea.Effect;
import com.example.dima.deephearth.FromIdea.HeroParams.AttackTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.NatureTypes;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.IntellectTypes;
import com.example.dima.deephearth.FromIdea.Player;
import com.example.dima.deephearth.FromIdea.Probability;
import com.example.dima.deephearth.FromIdea.Skills.Swap;
import com.example.dima.deephearth.FromIdea.SpeedComparator;
import com.example.dima.deephearth.FromIdea.Team;
import com.example.dima.deephearth.FromIdea.Unit;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by Dima on 11.04.2017.
 */

public class BattleActivity extends AppCompatActivity implements View.OnClickListener{

    //TODO : setUnit, setSkill methods, rework hp and mp scaling

    public Battle battle = new Battle();
    Player player1, player2;
    LinkedList<UnitButton> moveOrder = new LinkedList<>();
    LinkedList<UnitButton> playerButtons = new LinkedList<>(), enemyButtons = new LinkedList<>();
    LinkedList<UnitButton> unitButtons = new LinkedList<>();
    SkillButton[] skillButtons;
    int curNumber;
    int posWidth, height;
    BattleStates state = BattleStates.Init;
    boolean animationRunning = false;

    UnitButton curUButton, activeButton, pickedHero;
    SkillButton curSButton;
    Skill pickedSkill;

    SkillDescLayout skillDescLayout;

    public static TextView statusTextView;

    LinearLayout[] uButLayouts;

    UnitButton[] buttons1;

    UnitButton[] buttons2;

    CustomRelativeLayout bl;

    public boolean skillPicked = false, heroPicked = false;
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
                (SkillButton) findViewById(R.id.skillButton3), (SkillButton) findViewById(R.id.skillButton4), (SkillButton) findViewById(R.id.moveSkillButton)};

        skillButtons[4].setSkill(new Swap(null));
        statusTextView = (TextView) findViewById(R.id.textView6);

        statusTextView.setMovementMethod(new ScrollingMovementMethod());

        uButLayouts = new LinearLayout[]{(LinearLayout) findViewById(R.id.LinearLayout1),(LinearLayout) findViewById(R.id.LinearLayout2),(LinearLayout) findViewById(R.id.LinearLayout3),(LinearLayout) findViewById(R.id.LinearLayout4),
                (LinearLayout) findViewById(R.id.LinearLayout5),(LinearLayout) findViewById(R.id.LinearLayout6),(LinearLayout) findViewById(R.id.LinearLayout7),(LinearLayout) findViewById(R.id.LinearLayout8)};

        buttons1 = new UnitButton[]{(UnitButton) findViewById(R.id.UnitButton1),(UnitButton) findViewById(R.id.UnitButton2),
                (UnitButton) findViewById(R.id.UnitButton3), (UnitButton) findViewById(R.id.UnitButton4)};

        buttons2 = new UnitButton[]{(UnitButton) findViewById(R.id.UnitButton5),(UnitButton) findViewById(R.id.UnitButton6),
                (UnitButton) findViewById(R.id.UnitButton7), (UnitButton) findViewById(R.id.UnitButton8)};

        skillDescLayout = (SkillDescLayout) findViewById(R.id.skillDescLayout);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int scHeight = size.y;
        int scWidth  = size.x;

        int maxWidth = (int) (scWidth/8.5);

        posWidth = maxWidth;

        int kwidth = ContextCompat.getDrawable(this, R.drawable.knight).getIntrinsicWidth();

        for (int i = 0; i < player1.team.size(); i++) {
            Unit unit = player1.team.get(i);
            UnitButton button = buttons1[i];
            button.unitLayout = (UnitLayout) findViewById(R.id.unitLayout);
            button.setOnClickListener(this);
            button.friendly = true;
            button.setUnit(unit, maxWidth);
            playerButtons.add(button);
            unitButtons.add(button);

            Drawable d = ContextCompat.getDrawable(this, unit.spriteIds.get("idle"));
            int width = (int)(maxWidth*d.getIntrinsicWidth()/kwidth);

            RelativeLayout rl = (RelativeLayout) uButLayouts[i].getParent();

            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rl.getLayoutParams();
            int margin = maxWidth*(3-i) - maxWidth*(d.getIntrinsicWidth() - kwidth)/2/kwidth;
            layoutParams.setMarginStart(margin);
            layoutParams.setMargins(margin, 0, 0, 0);
        }

        for (int i = 0; i < player2.team.size(); i++) {
            Unit unit = player2.team.get(i);
            UnitButton button = buttons2[i];
            button.unitLayout = (UnitLayout) findViewById(R.id.unitLayout);
            button.setOnClickListener(this);
            button.setUnit(unit, maxWidth);
            enemyButtons.add(button);
            unitButtons.add(button);

            Drawable d = ContextCompat.getDrawable(this, unit.spriteIds.get("idle"));
            int width = (int)(maxWidth*d.getIntrinsicWidth()/ContextCompat.getDrawable(this, R.drawable.knight).getIntrinsicWidth());

            RelativeLayout rl = (RelativeLayout) uButLayouts[i + 4].getParent();

            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rl.getLayoutParams();
            int margin = maxWidth*(3-i) - maxWidth*(d.getIntrinsicWidth() - kwidth)/2/kwidth;
            layoutParams.setMarginEnd(margin);
            layoutParams.setMargins(0, 0, margin, 0);
        }

        TableLayout effectTable = (TableLayout) findViewById(R.id.effectTable);

        for (SkillButton button :
                skillButtons) {
            button.setOnClickListener(this);
        }
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

        Log.d("Debug", "created");

        bl = (CustomRelativeLayout) findViewById(R.id.battleLayout);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        height = bl.getHeight();
        refreshBattle();
        nextMove();
    }

    @Override
    public void onBackPressed() {
        RelativeLayout ll = (RelativeLayout) findViewById(R.id.infoLayout);
        Log.d("back", ll.getHeight() + " " + bl.getHeight());
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
        if (!skillPicked && v.getId() != R.id.moveSkillButton) {
            skillDescLayout.setVisibility(View.VISIBLE);
            skillDescLayout.setEnabled(true);
            skillDescLayout.setSkill(v.skill);
        }
        if(!skillPicked || skillPicked&&(v != curSButton)) {
            if (curSButton != null) curSButton.setCurrent(false);
            curSButton = v;

            if (v.usable) {
                curSButton.setCurrent(true);
                skillPicked = true;
                pickedSkill = curSButton.skill;

                if (!pickedSkill.friendly && !pickedSkill.onSelf) {
                    for (int i = 0; i < enemyButtons.size(); i++) {
                        enemyButtons.get(i).setCanBeTarget(pickedSkill.canBeUsedOn[i]);
                    }
                }

                if (pickedSkill.friendly) {
                    for (UnitButton b :
                            playerButtons) {
                        b.setCanBeTarget(true);
                    }
                }

                if (pickedSkill.onSelf) activeButton.setCanBeTarget(true);
                else activeButton.setCanBeTarget(false);
            }
        }

        else {
            curSButton.setCurrent(false);
            curSButton = null;
            skillPicked = false;
            pickedSkill = null;
            for (UnitButton b :
                    playerButtons) {
                b.setCanBeTarget(false);
            }

            for (UnitButton b :
                    enemyButtons) {
                b.setCanBeTarget(false);
            }
        }
    }

    private void uButtonClick(UnitButton v) {

        if (heroPicked && v != pickedHero) {heroPicked = false; pickedHero.setPicked(false); pickedHero = null;}

        if (heroPicked && skillPicked) {useSkill(pickedHero); return;}

        if (skillPicked && v.canBeTarget) {heroPicked = true; pickedHero = v; pickedHero.setPicked(true);}

        if(activeButton == v) {
            for (SkillButton s :
                    skillButtons) {
                v.UpdateInfo();
                if (playerButtons.contains(v)) {s.setUsable(s.skill.canBeUsedFrom[playerButtons.indexOf(v)] && s.skill.cost < v.unit.mana);}
                if ((s == curSButton) && (skillPicked)) s.setCurrent(true);
            }
        }

        else for (SkillButton s : skillButtons) {
            s.setUsable(false);
        }

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

    void nextMove()
    {

        for (UnitButton b :
                moveOrder) {
            if ((b.unit.nature == NatureTypes.Alive) && (b.unit.mana + 1 <= b.unit.maxMana)) {b.unit.mana += 1; b.UpdateInfo();}
        }

        if (playerButtons.size() == 0) defeat();

        else if (enemyButtons.size() == 0) victory();

        else if (curNumber < moveOrder.size() - 1) {
            curNumber++;
            if(activeButton != null) activeButton.movePointLayout.getChildAt(0).setVisibility(View.INVISIBLE);
            setActiveButton(moveOrder.get(curNumber));
            activeButton.unit.applyEffects();
            activeButton.unit.moves--;
            activeButton.UpdateInfo();
            uButtonClick(activeButton);
            if (activeButton.unit.team.player.intellect.type == IntellectTypes.Human) {state = BattleStates.PlayerMove;  }
            else {
                state = BattleStates.EnemyMove;

                refreshBattle();

                Pair<UnitButton, Skill> choice = activeButton.unit.team.player.intellect.think(battle);

                pickedSkill = choice.second;
                pickedHero = choice.first;
                useSkill(choice.first);
            }
        }

        else nextTurn();
    }

    void defeat(){

    }

    void victory(){

    }

    void nextTurn(){

        curNumber = -1;

        for (UnitButton b :
                moveOrder) {
            b.unit.moves = b.unit.maxMoves;
        }

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

    void useSkill(UnitButton target){

        if (pickedSkill.getClass() != Swap.class) {

            if (state == BattleStates.PlayerMove) writeStatus("");

            String info = activeButton.unit.name + " uses " + pickedSkill.name;

            final LinkedList<UnitButton> targets = new LinkedList<>();
            LinkedList<Integer> damages = new LinkedList<>();

            if ((pickedSkill.targetAmount == 1)) {
                targets.add(target);
            } else if (pickedSkill.targetAmount > 1) {
                if (state == BattleStates.PlayerMove) {

                    if (pickedSkill.friendly) {
                        for (UnitButton b :
                                playerButtons) {
                            targets.add(b);
                        }
                    } else {
                        for (UnitButton b : enemyButtons) {
                            if (enemyButtons.indexOf(b) > -1 && pickedSkill.canBeUsedOn[enemyButtons.indexOf(b)])
                                targets.add(b);
                        }
                    }
                } else {
                    if (pickedSkill.friendly) {
                        for (UnitButton b : enemyButtons) {
                            targets.add(b);
                        }
                    } else {
                        for (UnitButton b : playerButtons) {
                            if (playerButtons.indexOf(b) > -1 && pickedSkill.canBeUsedOn[playerButtons.indexOf(b)])
                                targets.add(b);
                        }
                    }
                }
            }

            if (!pickedSkill.onSelf) {
                info += " on ";
                if (targets.size() == 1) info += target.unit.name;
                else {
                    for (int i = 0; i < targets.size(); i++) {
                        info += targets.get(i).unit.name + ", ";
                    }

                    info = info.replace(", ", ".");
                }
            }

            writeStatus(info);

            pickedSkill.owner.mana -= pickedSkill.cost;

            if (pickedSkill.onSelf) pickedSkill.action(target.unit);

            else if (pickedSkill.friendly) {
                for (UnitButton b : targets) {
                    pickedSkill.action(b.unit);
                }
            } else {
                for (UnitButton b :
                        targets) {
                    if (new Probability(pickedSkill.accuracy + pickedSkill.owner.luck, pickedSkill.accuracy + pickedSkill.owner.luck + b.unit.luck + b.unit.dodge).check()) {
                        pickedSkill.action(b.unit);
                        damages.add(pickedSkill.damage);
                        for (Effect e :
                                pickedSkill.effects) {
                            if (!(b.unit.effectDefs.get(e.type) / 100 > Math.random())) {
                                e.addToTarget(b.unit);
                                writeStatus("Added " + e.name + " to " + b.unit.name);
                            } else {
                                writeStatus(b.unit + " resisted");
                            }
                        }

                    } else {
                        writeStatus(activeButton.unit.name + " missed");
                        damages.add(-1);
                    }
                }
            }

            if (!pickedSkill.friendly && !pickedSkill.onSelf) {
                startAnim(targets, damages);
            } else {
                finishUsage(targets);
            }
        }

        else {LinkedList bs = swapUnits(activeButton, pickedHero); finishUsage(bs);}
    }


    void setActiveButton(UnitButton activeButton){

        this.activeButton = activeButton;
        activeButton.movePointLayout.getChildAt(0).setVisibility(View.VISIBLE);
    }

    public static void writeStatus(String text) {

        String s = statusTextView.getText().toString();

        String[] temp = s.split("\n");

        if ((temp.length < 10) && (text != "") && (s != "")) {text = s + "\n" + text;}
        statusTextView.setText(text);
        if (text == "") statusTextView.setText("");
    }

    void refreshBattle(){

        battle.activeButton = activeButton;
        battle.moveOrder = moveOrder;
        battle.curNumber = curNumber;
        battle.enemyButtons = enemyButtons;
        battle.playerButtons = playerButtons;
        battle.state = state;
        battle.player1 = player1;
        battle.player2 = player2;
        battle.skillButtons = skillButtons;
    }

    public void startAnim(final LinkedList<UnitButton> targets, LinkedList<Integer> damages) {
        int height = targets.get(0).parentLayout.getHeight();
        expandBattle();

        RelativeLayout battleLayout =(RelativeLayout) findViewById(R.id.battleLayout);

        for (LinearLayout ll :
                uButLayouts) {
            if(ll != activeButton.parentLayout) {
                ll.setAlpha(0.3f);
            }
        }
        

        for (UnitButton b : unitButtons){
            b.barLayout.setVisibility(View.INVISIBLE);
            b.movePointLayout.setVisibility(View.INVISIBLE);
        }

        float aScale = 1.2f, tScale = 1.2f;

        AnimatorSet set = new AnimatorSet();

        activeButton.setImageResource(activeButton.unit.spriteIds.get("prepairing"));

        int[] locations = new int[2];
        int[] targLocations = new int[2];
        activeButton.getLocationOnScreen(locations);
        targets.get(0).getLocationOnScreen(targLocations);

        float deltaX = targLocations[0] - locations[0] + posWidth/3f;

        PropertyValuesHolder prx;

        if (activeButton.getScaleX() > 0) {prx = PropertyValuesHolder.ofFloat("scaleX", aScale); deltaX-= posWidth;}
        else prx = PropertyValuesHolder.ofFloat("scaleX", -aScale);

        PropertyValuesHolder pry = PropertyValuesHolder.ofFloat("scaleY", aScale);

        long time = (long) Math.abs(deltaX*1.1f);

        PropertyValuesHolder prtx = PropertyValuesHolder.ofFloat("translationX", deltaX);

        PropertyValuesHolder a3 = PropertyValuesHolder.ofFloat( "translationX", 0);
        PropertyValuesHolder a1 = PropertyValuesHolder.ofFloat("scaleY", 1.0f);
        PropertyValuesHolder a2;
        if( activeButton.getScaleX() > 0) a2 = PropertyValuesHolder.ofFloat("scaleX", 1.0f);
        else a2 = PropertyValuesHolder.ofFloat("scaleX", -1.0f);
        ObjectAnimator slideTo, slideBack;
        if (pickedSkill.attackType == AttackTypes.Melee) {
            slideTo = ObjectAnimator.ofPropertyValuesHolder(activeButton, prx, pry, prtx);
            slideBack = ObjectAnimator.ofPropertyValuesHolder(activeButton, a1, a2, a3);
        }
        else {
            time = 400;
            slideTo = ObjectAnimator.ofPropertyValuesHolder(activeButton, prx, pry);
            slideBack = ObjectAnimator.ofPropertyValuesHolder(activeButton, a1, a2);
        }


        slideTo.setDuration(time);

        slideBack.setDuration(time / 2);
        slideBack.setStartDelay(500);

        activeButton.setPivotY(activeButton.getHeight());

        battleLayout.bringChildToFront((RelativeLayout)activeButton.parentLayout.getParent());

        slideTo.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                activeButton.setMaxWidth(activeButton.maxAWidth);
                activeButton.setImageResource(activeButton.unit.spriteIds.get("attack"));
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        slideBack.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                activeButton.setMaxWidth(activeButton.maxWidth);
                activeButton.setImageResource(activeButton.unit.spriteIds.get("idle"));
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                shrinkBattle();
                finishUsage(targets);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        set.playSequentially(slideTo, slideBack);

        int i = 0;

        for (UnitButton b :
                targets) {
            b.parentLayout.setAlpha(1f);
            PropertyValuesHolder tprx, tpry, t1, t2;

            if (b.getScaleX() > 0) {tprx = PropertyValuesHolder.ofFloat("scaleX", tScale); t1 = PropertyValuesHolder.ofFloat("scaleX", 1f);}
            else {tprx = PropertyValuesHolder.ofFloat("scaleX", -tScale); t1 = PropertyValuesHolder.ofFloat("scaleX", -1f);}

            tpry = PropertyValuesHolder.ofFloat("scaleY", tScale);
            t2 = PropertyValuesHolder.ofFloat("scaleY", 1.0f);

            ObjectAnimator at1, at2;
            at1 = ObjectAnimator.ofPropertyValuesHolder(b, tprx, tpry);
            at1.setDuration(time);
            at2 = ObjectAnimator.ofPropertyValuesHolder(b, t1, t2);
            at2.setDuration(time/2);
            at2.setStartDelay(500);

            set.play(at1).with(slideTo);
            set.play(at2).after(at1);

            final TextView hittext = (TextView) LayoutInflater.from(this).inflate(R.layout.hittext, null);
            if (damages.get(i) == -1) hittext.setText("MISS");
            else hittext.setText("-" + damages.get(i));
            final RelativeLayout rl = (RelativeLayout) b.parentLayout.getParent();
            rl.addView(hittext);
            RelativeLayout.LayoutParams p = (RelativeLayout.LayoutParams)hittext.getLayoutParams();
            p.addRule(RelativeLayout.CENTER_IN_PARENT);
            hittext.setLayoutParams(p);

            Animation animation = AnimationUtils.loadAnimation(this, R.anim.move_text);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    hittext.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            rl.removeView(hittext);
                        }
                    }, 500);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            hittext.startAnimation(animation);


            ImageView hitview = new ImageView(this);
            hitview.setImageResource((Integer) pickedSkill.animMap.get("drawable"));
            Drawable d =  getResources().getDrawable(R.drawable.knight);
            Drawable c = getResources().getDrawable((Integer) pickedSkill.animMap.get("drawable"));
            int mWidth = (int)(posWidth*c.getIntrinsicWidth()/d.getIntrinsicWidth());
            hitview.setMaxWidth(mWidth);
            hitview.setAdjustViewBounds(true);
            hitview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            hitview.setLayoutParams(params);
            RelativeLayout rl2 = (RelativeLayout) b.getParent();
            if (b.getScaleX() >  0) hitview.setScaleX(-1f);
            rl2.addView(hitview);
            i++;
        }

        set.start();

    }

    public LinkedList<UnitButton> swapUnits(UnitButton u1, UnitButton u2){
        Team team = u1.unit.team;
        LinkedList<UnitButton> buttons;
        team.remove(u1.unit);
        team.add(team.indexOf(u2.unit), u1.unit);
        int kwidth = ContextCompat.getDrawable(this, R.drawable.knight).getIntrinsicWidth();
        if (team.player.intellect.type == IntellectTypes.Human) {
            buttons = playerButtons;
            buttons.remove(u1);
            buttons.add(buttons.indexOf(u2), u1);
            for (int i = 0; i < buttons.size(); i++) {

                UnitButton b = buttons.get(i);

                Drawable d = ContextCompat.getDrawable(this, b.unit.spriteIds.get("idle"));

                RelativeLayout rl = (RelativeLayout) b.parentLayout.getParent();

                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rl.getLayoutParams();
                int margin = posWidth*(3-i) - posWidth*(d.getIntrinsicWidth() - kwidth)/2/kwidth;
                layoutParams.setMarginStart(margin);
                layoutParams.setMargins(margin, 0, 0, 0);
            }

        }
        else {
            buttons = enemyButtons;
            buttons.remove(u1);
            buttons.add(buttons.indexOf(u2), u1);
            for (int i = 0; i < buttons.size(); i++) {
                UnitButton b = buttons.get(i);

                Drawable d = ContextCompat.getDrawable(this, b.unit.spriteIds.get("idle"));

                RelativeLayout rl = (RelativeLayout) b.parentLayout.getParent();

                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rl.getLayoutParams();
                int margin = posWidth*(3-i) - posWidth*(d.getIntrinsicWidth() - kwidth)/2/kwidth;
                layoutParams.setMarginStart(margin);
                layoutParams.setMargins(margin, 0, 0, 0);
            }
            }
            return buttons;
        }

    public void finishUsage(LinkedList<UnitButton> targets){


        activeButton.UpdateInfo();

        for (UnitButton target :
                targets) {
            target.UpdateInfo();
        }
        pickedSkill = null;
        skillPicked = false;
        curSButton.setCurrent(false);
        skillDescLayout.setVisibility(View.INVISIBLE);
        for (UnitButton b :
                playerButtons) {
            b.setCanBeTarget(false);
        }


        for (UnitButton b :
                enemyButtons) {
            b.setCanBeTarget(false);
        }

        for (UnitButton target :
                targets) {

        if ((target.unit.isDead)&&(moveOrder.contains(target))) {
            moveOrder.remove(target);
            target.setBackgroundResource(R.color.colorPrimaryDark);
            }
        }

        heroPicked = false; if (pickedHero != null) {pickedHero.UpdateInfo(); pickedHero.setPicked(false); pickedHero = null;}
        nextMove();
    }

    public void expandBattle() {
        CustomRelativeLayout battleLayout = (CustomRelativeLayout) findViewById(R.id.battleLayout);
        RelativeLayout infoLayout = (RelativeLayout) findViewById(R.id.infoLayout);
        LinearLayout messageLayout = (LinearLayout) findViewById(R.id.messageLayout);

        messageLayout.setVisibility(View.INVISIBLE);
        infoLayout.setVisibility(View.INVISIBLE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 14f);
        battleLayout.setLayoutParams(params);
        battleLayout.setClipChildren(false);
        battleLayout.setClipToPadding(false);
        onWindowFocusChanged(true);

    }

    public void shrinkBattle(){
        final CustomRelativeLayout battleLayout = (CustomRelativeLayout) findViewById(R.id.battleLayout);
        final RelativeLayout infoLayout = (RelativeLayout) findViewById(R.id.infoLayout);
        final LinearLayout messageLayout = (LinearLayout) findViewById(R.id.messageLayout);
        final Animation ensmallen = AnimationUtils.loadAnimation(this, R.anim.anim_ensmallen);

        for (LinearLayout ll :
                uButLayouts) {
            ll.setAlpha(1f);
        }

        for (UnitButton b :
                unitButtons) {
            b.barLayout.setVisibility(View.VISIBLE);
            b.movePointLayout.setVisibility(View.VISIBLE);
        }

        messageLayout.setVisibility(View.VISIBLE);
        infoLayout.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 6f);
        battleLayout.setLayoutParams(params);
        battleLayout.setClipChildren(false);
        battleLayout.setClipToPadding(false);
        onWindowFocusChanged(true);

    }

    public void closeDesc(View v){
        skillDescLayout.setVisibility(View.INVISIBLE);
        skillDescLayout.setEnabled(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Debug", "Sart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Debug", "Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Debug", "Destroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Debug", "Resume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Debug", "Restart");
    }
}
