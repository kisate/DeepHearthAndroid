package com.example.dima.deephearth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dima.deephearth.FromIdea.Item;

import java.util.LinkedList;

/**
 * Created by Dima on 17.05.2017.
 */

public class ItemAdapter extends BaseAdapter {

    Context context;
    LinkedList<Item> items;
    LayoutInflater inflater;
    public static String itemTag = "item view";

    public ItemAdapter(Context context, LinkedList<Item> items) {
        this.context = context;
        this.items = items;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_view, parent, false);
        }

        view.setTag(itemTag);

        Item item = items.get(position);

        TextView textView = (TextView) view.findViewById(R.id.nameView);
        ImageView imageView = (ImageView) view.findViewById(R.id.icoView);
        textView.setText(item.name);
        imageView.setImageResource(item.icoId);

        ((ItemView) view).setItem(item);

        if (context.getClass() == PlayerInspectorActivity.class) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((PlayerInspectorActivity) context).onItemClick(v);
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ((PlayerInspectorActivity) context).onLongItemClick(v);
                    return false;
                }
            });
        }

        return view;
    }
}
