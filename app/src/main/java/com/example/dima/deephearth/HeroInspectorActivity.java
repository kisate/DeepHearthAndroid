package com.example.dima.deephearth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.Hero;

import org.w3c.dom.Text;

import java.util.Iterator;

public class HeroInspectorActivity extends AppCompatActivity {

    Hero hero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_inspector);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        hero = (Hero) getIntent().getSerializableExtra("Hero");

        TextView typeView = (TextView) findViewById(R.id.typeView);
        TextView nameView = (TextView) findViewById(R.id.nameView);
        TextView profView = (TextView) findViewById(R.id.profView);
        TextView powerDexView = (TextView) findViewById(R.id.powerDexView);
        TextView statView1 = (TextView) findViewById(R.id.statView1);
        TextView statView2 = (TextView) findViewById(R.id.statView2);
        ImageView heroView = (ImageView) findViewById(R.id.heroView);
        LinearLayout skillLayout = (LinearLayout) findViewById(R.id.skillLayout);

        typeView.setText(hero.nature.toString());
        nameView.setText(hero.name);
        profView.setText(hero.heroClass.toString());
        powerDexView.setText("Power : " + (int) hero.power + "|Dex : " + (int) hero.dexterity);
        statView1.setText("Dmg : " + (int) hero.damage + "\nDodge : " + (int) hero.dodge + "\nDef : " + (int)hero.defence*100 + "%\nCrt : " + hero.critical);
        statView2.setText("Luck : " + (int) hero.luck + "\nAcc : " + (int) hero.accuracy + "\nSpeed : " + (int) hero.speed);
        heroView.setImageResource(hero.spriteIds.get("idle"));

        for (int i = 0; i < 4; i++) {
            SkillButton b = (SkillButton) ((FrameLayout)skillLayout.getChildAt(i)).getChildAt(0);
            b.setSkill(hero.skills.get(i));
        }

        displayDefs();
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

    void displayDefs() {
        TableLayout effectTable = (TableLayout) findViewById(R.id.effectTable);
        Iterator<EffectTypes> i = hero  .effectDefs.keySet().iterator();
        effectTable.removeAllViews();
        while (i.hasNext()) {
            EffectTypes et = i.next();
            TableRow row = (TableRow) LayoutInflater.from(this).inflate(R.layout.effectrow, effectTable, false);
            LinearLayout mll = (LinearLayout) row.getChildAt(0);
            LinearLayout ll1 = (LinearLayout) mll.getChildAt(0);
            ImageView iv = (ImageView) ll1.getChildAt(0);
            TextView tv = (TextView) ll1.getChildAt(1);
            iv.setImageResource(et.icoId);
            tv.setText("" + hero.effectDefs.get(et).intValue() + "%");
            LinearLayout ll2 = (LinearLayout) mll.getChildAt(1);
            if (i.hasNext()) {
                ll2.setVisibility(View.VISIBLE);
                et = i.next();
                iv = (ImageView) ll2.getChildAt(0);
                tv = (TextView) ll2.getChildAt(1);
                iv.setImageResource(et.icoId);
                tv.setText("" + hero.effectDefs.get(et).intValue() + "%");
            }

            else ll2.setVisibility(View.INVISIBLE);
            effectTable.addView(row);
        }
    }
}
