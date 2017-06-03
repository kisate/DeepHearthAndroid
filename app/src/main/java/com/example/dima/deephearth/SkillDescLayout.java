package com.example.dima.deephearth;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
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
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Unit;

import org.w3c.dom.Text;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Dima on 24.04.2017.
 */

public class SkillDescLayout extends LinearLayout {

    public Skill skill; public Unit target;

    RelativeLayout skillLayout, targetLayout;

    public void setSkill(Skill _skill) {
        skill = _skill;
        skillLayout = (RelativeLayout) findViewById(R.id.skillLayout);
        TextView skillNameView = (TextView) findViewById(R.id.skillNameView);
        ImageView skillImageView = (ImageView) findViewById(R.id.skillImageView);
        LinearLayout skillUsageLayout = (LinearLayout) findViewById(R.id.skillUsageLayout);
        TextView mpView = (TextView) findViewById(R.id.skillMpView);
        TextView adView = (TextView) findViewById(R.id.skillADView);
        TableLayout effectTable = (TableLayout) findViewById(R.id.effectTable);

        skill.setup();
        skillLayout.setVisibility(VISIBLE);
        skillNameView.setText(skill.name);
        skillImageView.setImageResource(skill.skillIco);
        mpView.setText("Mp : " + skill.cost);
        adView.setText("Dmg : " + (int)(skill.power*skill.bottom) + "-" + (int)(skill.power*skill.top) + "\nAcc : " + skill.accuracy);
        if (skill.targetAmount > 1) mpView.setText(mpView.getText() + " CHAIN");

        if (!(skill.friendly && skill.onSelf)) {
            for (int i = 0; i < 4; i++) {
                ImageView ico = (ImageView) skillUsageLayout.getChildAt(i + 5);
                if (skill.canBeUsedOn[i]) {
                    ico.setImageResource(R.drawable.skillused_enemy);
                }
                else ico.setImageResource(R.drawable.skillused_unable);
            }
        }

        for (int i = 0; i < 4; i++) {
            ImageView ico = (ImageView) skillUsageLayout.getChildAt(3-i);
            if (skill.canBeUsedFrom[i]) {
                ico.setImageResource(R.drawable.skillused_from);
            }
            else ico.setImageResource(R.drawable.skillused_unable);
        }

        effectTable.removeAllViews();

        for (Effect e :
                skill.effects) {
            TableRow row = (TableRow) LayoutInflater.from(getContext()).inflate(R.layout.row, null);

            ImageView eIco =  (ImageView) row.getChildAt(0);
            eIco.setImageResource(e.icoId);

            TextView eText = (TextView) row.getChildAt(1);

            eText.setText("" + e.power + "/" + e.turns);

            effectTable.addView(row);
        }
    }

    public void setTarget(Unit target) {
        this.target = target;
        targetLayout = (RelativeLayout) findViewById(R.id.targetLayout);
        if (target != null) {
            targetLayout.setVisibility(VISIBLE);
            setClickable(true);
            TextView nameView = (TextView) findViewById(R.id.targetName);
            TextView hmView = (TextView) findViewById(R.id.targetHM);
            TextView ddView = (TextView) findViewById(R.id.targetDD);
            TextView dalView = (TextView) findViewById(R.id.targetDAL);
            ImageView icoView = (ImageView) findViewById(R.id.skillTargetIco);
            icoView.setImageResource(target.icoId);
            nameView.setText(target.getName());
            hmView.setText("Hp: " + (int)target.health.clearValue + "/" + (int)target.maxHealth.getValue() + " | Mp: " + (int)target.mana.clearValue  + "/" + (int)target.maxMana.getValue());
            ddView.setText("Def: " + (int)target.defence.getValue()*100 + "%\nDg : " + (int)target.dodge.getValue());
            dalView.setText("Dmg: " + (int)target.damage.getValue() + "\nAcc: " + (int)target.accuracy.getValue() + "\nLuck: " + (int)target.luck.getValue());


            TableLayout skillTable = (TableLayout) findViewById(R.id.skillTable);
            TableLayout effectTable = (TableLayout) findViewById(R.id.targetEffectTable);
            skillTable.removeAllViews();
            for (Skill s :
                    target.skills) {
                TableRow row = (TableRow) LayoutInflater.from(getContext()).inflate(R.layout.skillrow, skillTable, false);
                LinearLayout ll = (LinearLayout) row.getChildAt(0);
                TextView skillNameView = (TextView) ll.getChildAt(0);
                skillNameView.setText(s.name);
                for (Effect e :
                        s.effects) {
                    ImageView iv = new ImageView(getContext());
                    LinearLayout.LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
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

            Iterator<EffectTypes> i = target.effectDefs.keySet().iterator();
            effectTable.removeAllViews();
            while (i.hasNext()) {
                EffectTypes et = i.next();
                TableRow row = (TableRow) LayoutInflater.from(getContext()).inflate(R.layout.effectrow, effectTable, false);
                LinearLayout mll = (LinearLayout) row.getChildAt(0);
                LinearLayout ll1 = (LinearLayout) mll.getChildAt(0);
                ImageView iv = (ImageView) ll1.getChildAt(0);
                TextView tv = (TextView) ll1.getChildAt(1);
                iv.setImageResource(et.icoId);
                tv.setText("" + target.effectDefs.get(et).getValue() + "%");
                LinearLayout ll2 = (LinearLayout) mll.getChildAt(1);
                if (i.hasNext()) {
                    ll2.setVisibility(VISIBLE);
                    et = i.next();
                    iv = (ImageView) ll2.getChildAt(0);
                    tv = (TextView) ll2.getChildAt(1);
                    iv.setImageResource(et.icoId);
                    tv.setText("" + target.effectDefs.get(et).getValue() + "%");
                }

                else ll2.setVisibility(INVISIBLE);
                effectTable.addView(row);
            }
        }

        else targetLayout.setVisibility(INVISIBLE);
    }

    public SkillDescLayout(Context context) {
        super(context);
    }

    public SkillDescLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SkillDescLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
