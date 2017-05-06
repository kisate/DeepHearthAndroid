package com.example.dima.deephearth;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.dima.deephearth.FromIdea.Effect;
import com.example.dima.deephearth.FromIdea.HeroParams.Skill;
import com.example.dima.deephearth.FromIdea.Unit;

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

        skillNameView.setText(skill.name);
        skillImageView.setImageResource(skill.skillIco);
        mpView.setText("Mp : " + skill.cost);
        adView.setText("Dmg : " + (int)(skill.power*skill.bottom) + "-" + (int)(skill.power*skill.top) + "\nAcc : " + skill.accuracy);

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

            Log.d("Debug", e.name);
        }
    }

    public void setTarget(Unit target) {
        this.target = target;
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
