package com.example.dima.deephearth;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.example.dima.deephearth.FromIdea.Phrase;

import java.util.LinkedList;

/**
 * Created by Dima on 29.05.2017.
 */

public class TextWriter extends AppCompatTextView {

    private int mIndex;
    private CharSequence mText;
    private long mDur = 50;
    public int clickCounter = 0;
    public boolean finished = true;
    LinkedList<Phrase> phrases;
    int phraseIndex;

    public TextWriter(Context context) {
        super(context);
    }

    public TextWriter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextWriter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Handler mHandler = new Handler();
    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            setText(mText.subSequence(0, mIndex++));
            if (mIndex <= mText.length()) {
                mHandler.postDelayed(characterAdder, mDur);
            }
            else {
                onTextEnd();
            }
        }
    };

    public void animateText(CharSequence text){
        mText = text;
        mIndex = 0;
        setBackgroundColor(Color.DKGRAY);
        setText("");
        finished = false;
        clickCounter = 0;
        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, mDur);
    }

    public void breakAnimation () {
        mHandler.removeCallbacks(characterAdder);
        setText(mText);
        setBackgroundColor(0);
    }

    public void setDelay(long mDur) {
        this.mDur = mDur;
    }

    public void onTextEnd() {
        finished = true;
        setBackgroundColor(0);
    }

    public void showPhrases(final LinkedList<Phrase> phrases, final View button, final Runnable onFinish) {
        this.phrases = phrases;
        phraseIndex = 0;
        showNextPhrase();
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                breakAnimation();
                finished = true;
            }
        });
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!finished) {breakAnimation(); finished = true;}
                else if (phraseIndex < phrases.size()) showNextPhrase();
                else {
                    onFinish.run();
                }
            }
        });
    }

    void showNextPhrase(){
        animateText(phrases.get(phraseIndex).second);
        setBackgroundColor(0);
        phraseIndex++;
    }
}
