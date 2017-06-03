package com.example.dima.deephearth.FromIdea;

import android.util.Pair;
import android.view.View;
import android.widget.Button;

import com.example.dima.deephearth.DialogueActivity;
import com.example.dima.deephearth.TextWriter;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Dima on 29.05.2017.
 */

public class Dialogue implements Serializable{
    TextWriter textWriter1, textWriter2;
    public String name1, name2;
    public int ico1, ico2;
    Iterator<Phrase> iterator;
    DialogueActivity activity;

    public void setActivity(DialogueActivity activity) {
        this.activity = activity;
    }

    public void setWriters(final TextWriter textWriter1, TextWriter textWriter2) {
        this.textWriter1 = textWriter1;
        this.textWriter2 = textWriter2;
        textWriter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextWriter textWriter = (TextWriter) v;
                textWriter.clickCounter++;
                if (textWriter.clickCounter == 1) textWriter.breakAnimation();
                if (textWriter.clickCounter == 1 && textWriter.finished || textWriter.clickCounter == 2) nextPhrase();
            }
        });

        textWriter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextWriter textWriter = (TextWriter) v;
                textWriter.clickCounter++;
                if (textWriter.clickCounter == 1) textWriter.breakAnimation();
                if (textWriter.clickCounter == 1 && textWriter.finished || textWriter.clickCounter == 2) nextPhrase();
            }
        });
    }

    public Dialogue(String name1, String name2, int ico1, int ico2) {
        this.name1 = name1;
        this.name2 = name2;
        this.ico1 = ico1;
        this.ico2 = ico2;
    }

    public LinkedList<Phrase> phrases = new LinkedList<>();

    public void startDialogue(){
        iterator = phrases.iterator();
        nextPhrase();
    }

    public void nextPhrase(){
        if (iterator.hasNext()) {
            Phrase phrase = iterator.next();
            if (phrase.first == 1) {
                textWriter1.animateText(phrase.second);
                textWriter2.setBackgroundColor(0);
            }
            else {
                textWriter2.animateText(phrase.second);
                textWriter1.setBackgroundColor(0);
            }
        }
        else activity.onDialogueEnd();
    }
}
