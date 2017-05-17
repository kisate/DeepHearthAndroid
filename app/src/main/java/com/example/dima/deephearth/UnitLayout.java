package com.example.dima.deephearth;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.dima.deephearth.FromIdea.Effect;
import com.example.dima.deephearth.FromIdea.EffectTypes;
import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.IntellectTypes;
import com.example.dima.deephearth.FromIdea.Unit;

import java.util.Iterator;

/**
 * Created by Dima on 11.04.2017.
 */

public class UnitLayout extends RelativeLayout {

    SkillDescLayout skillDescLayout;

    public Unit unit;

    public UnitLayout(Context context) {
        super(context);
    }

    public UnitLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UnitLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void updateUnitInfo(Unit unit){
        TextView nameView = (TextView) findViewById(R.id.textView5);
        nameView.setText("" + unit.getName() + " | Hp : " + (int)unit.health + "/" + (int)unit.maxHealth + " | Mp :" + (int)unit.mana + "/" + (int)unit.maxMana);
        TextView statView = (TextView) findViewById(R.id.dmgDodgeTView);
        TextView stat2View = (TextView) findViewById(R.id.ADSTView);
        TextView stat3View = (TextView) findViewById(R.id.LCTView);

        statView.setText("Dmg : " + (int)unit.damage + "\nDodge : " + (int)unit.dodge);
        stat2View.setText("Acc : " + (int)unit.accuracy + "\nSpd : " + (int)unit.speed + "\nDef : " + (int)unit.defence*100 + "%");
        stat3View.setText("Luck : " + (int)unit.luck + "\nCrt : " + (int)unit.critical + "%");

        ImageView ico = (ImageView) (findViewById(R.id.imageView2));
        ico.setImageResource(unit.icoId);

        if (unit.team.player.intellect.type == IntellectTypes.Human) {

            LinearLayout esLayout = (LinearLayout) findViewById(R.id.effskillLayout);

            esLayout.setVisibility(INVISIBLE);
            esLayout.setClickable(false);

            SkillButton[] buttons = {(SkillButton) findViewById(R.id.skillButton1), (SkillButton) findViewById(R.id.skillButton2), (SkillButton) findViewById(R.id.skillButton3), (SkillButton) findViewById(R.id.skillButton4)};
            for (int i = 0; i < 4; i++) {
                buttons[i].setSkill(unit.skills.get(i));
            }
        }

        else {

            LinearLayout esLayout = (LinearLayout) findViewById(R.id.effskillLayout);

            esLayout.setVisibility(VISIBLE);
            esLayout.setClickable(true);

            TableLayout skillTable = (TableLayout) findViewById(R.id.skillTable2);
            TableLayout effectTable = (TableLayout) findViewById(R.id.targetEffectTable2);
            skillTable.removeAllViews();
            for (Skill s :
                    unit.skills) {
                TableRow row = (TableRow) LayoutInflater.from(getContext()).inflate(R.layout.skillrow, skillTable, false);
                LinearLayout ll = (LinearLayout) row.getChildAt(0);
                TextView skillNameView = (TextView) ll.getChildAt(0);
                skillNameView.setText(s.name);
                for (Effect e :
                        s.effects) {
                    ImageView iv = new ImageView(getContext());
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
                    int height = (int) skillNameView.getTextSize();
                    lp.setMarginStart(height/2);
                    lp.setMargins(height/2, 0, 0, 0);
                    iv.setLayoutParams(lp);
                    iv.setImageResource(e.icoId);
                    iv.setMaxHeight(height);
                    iv.setAdjustViewBounds(true);
                    ll.addView(iv, lp);
                }
                skillTable.addView(row);
            }

            Iterator<EffectTypes> i = unit.effectDefs.keySet().iterator();
            effectTable.removeAllViews();
            while (i.hasNext()) {
                EffectTypes et = i.next();
                TableRow row = (TableRow) LayoutInflater.from(getContext()).inflate(R.layout.effectrow, effectTable, false);
                LinearLayout mll = (LinearLayout) row.getChildAt(0);
                LinearLayout ll1 = (LinearLayout) mll.getChildAt(0);
                ImageView iv = (ImageView) ll1.getChildAt(0);
                TextView tv = (TextView) ll1.getChildAt(1);
                iv.setImageResource(et.icoId);
                tv.setText("" + unit.effectDefs.get(et).intValue() + "%");
                LinearLayout ll2 = (LinearLayout) mll.getChildAt(1);
                if (i.hasNext()) {
                    ll2.setVisibility(VISIBLE);
                    et = i.next();
                    iv = (ImageView) ll2.getChildAt(0);
                    tv = (TextView) ll2.getChildAt(1);
                    iv.setImageResource(et.icoId);
                    tv.setText("" + unit.effectDefs.get(et).intValue() + "%");
                }

                else ll2.setVisibility(INVISIBLE);
                effectTable.addView(row);
            }
        }
    }
}
