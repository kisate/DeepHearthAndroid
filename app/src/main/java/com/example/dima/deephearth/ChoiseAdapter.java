package com.example.dima.deephearth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.dima.deephearth.FromIdea.Dungeon.Choice;

import java.util.ArrayList;

/**
 * Created by Dima on 14.05.2017.
 */

public class ChoiseAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<Choice> choices;

    public ChoiseAdapter(ArrayList<Choice> choices, Context context) {
        this.choices = choices;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return choices.size();
    }

    @Override
    public Object getItem(int position) {
        return choices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.choise_button, parent, false);
        }

        final Choice choice = getChoise(position);

        ((Button) view).setText(choice.name);
        ((Button) view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice.make();
            }
        });

        return view;
    }

    Choice getChoise(int position) {
        return choices.get(position);
    }
}
