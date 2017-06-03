package com.example.dima.deephearth;

import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.Item;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Dima on 14.05.2017.
 */

public class HeroAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    LinkedList<Hero> objects;
    public static String heroTag = "hero view";

    public HeroAdapter(Context context, LinkedList<Hero> objects) {
        this.context = context;
        this.objects = objects;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.hero_item, parent, false);
        }

        final Hero hero = getHero(position);

        ((ImageView) view.findViewById(R.id.icoView)).setImageResource(hero.icoId);
        ((TextView) view.findViewById(R.id.nameView)).setText(hero.name);
        ProgressBar hpBar = (ProgressBar) view.findViewById(R.id.hpBar);
        ProgressBar manaBar = (ProgressBar) view.findViewById(R.id.manaBar);

        hpBar.getProgressDrawable().setColorFilter(
                Color.RED, PorterDuff.Mode.SRC_ATOP);

        manaBar.getProgressDrawable().setColorFilter(
                Color.BLUE, PorterDuff.Mode.MULTIPLY);

        hpBar.setMax((int)hero.maxHealth.getValue());
        hpBar.setProgress((int)hero.health.clearValue);

        manaBar.setMax((int)hero.maxMana.getValue());
        manaBar.setProgress((int)hero.mana.clearValue);
        HeroButton button = (HeroButton) view.findViewById(R.id.button3);
        button.setHero(hero);
        button.setTag(heroTag);

        if (context.getClass() == ExpeditionSetupActivity.class) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ExpeditionSetupActivity) context).addHero(v);
                }
            });
        }

        else if (context.getClass() == PlayerInspectorActivity.class) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((PlayerInspectorActivity) context).displayHero(v);
                }
            });
        }
        else if (context.getClass() == InnActivity.class) {
            button.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ((InnActivity) context).onLongHeroClick(v);
                    return false;
                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, HeroInspectorActivity.class);
                    intent.putExtra("Hero", hero);
                    context.startActivity(intent);
                }
            });
        }

        MyDragEventListener myDragEventListener = new MyDragEventListener();
        view.setOnDragListener(myDragEventListener);
        return view;
    }


    Hero getHero(int position) {
        return ((Hero) getItem(position));
    }

    private class MyDragEventListener implements View.OnDragListener{
        @Override
        public boolean onDrag(View v, DragEvent event) {
            final  int action = event.getAction();

            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED :
                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN) && event.getClipDescription().getLabel().equals(ItemAdapter.itemTag)) {
                        v.setBackgroundColor(Color.GRAY);
                        v.invalidate();
                        return true;
                    }

                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundColor(Color.GREEN);
                    v.invalidate();
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:
                    v.setBackgroundColor(Color.LTGRAY);
                    v.invalidate();
                    return true;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundColor(Color.GRAY);
                    v.invalidate();
                    return true;
                case DragEvent.ACTION_DROP:
                    v.setBackgroundColor(0);
                    Item item = ((Item)event.getClipData().getItemAt(0).getIntent().getSerializableExtra("Item"));
                    HeroButton button = (HeroButton) v.findViewById(R.id.button3);
                    button.hero.inventory.add(item);
                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundColor(0);
                    return true;
                default:
                    Log.e("DragDrop", "Unknown action");
                    break;
            }

            return false;
        }
    }
}
