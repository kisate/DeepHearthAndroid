package com.example.dima.deephearth;

import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dima.deephearth.FromIdea.Dungeon.Choice;
import com.example.dima.deephearth.FromIdea.Dungeon.Event;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.ChoiceEvents.BattleStartEvent;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.ChoiceEvents.LeaveEvent;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.ChoiceEvents.TakeEvent;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.EnemyEvent;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.EntityEvent;
import com.example.dima.deephearth.FromIdea.Dungeon.Events.TreasureEvent;
import com.example.dima.deephearth.FromIdea.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Dima on 09.05.2017.
 */

public class DungeonEventHandler implements Serializable{
    DungeonActivity activity;

    public DungeonEventHandler(DungeonActivity activity) {
        this.activity = activity;
    }

    public void handle(LinkedList<Event> events) {
        LinearLayout eventLayout = (LinearLayout) activity.findViewById(R.id.eventLayout);
        for (Event e :
                events) {
            switch (e.type) {
                case startBattle:
                    BattleStartEvent bse = (BattleStartEvent) e;
                    activity.launchBattle(bse.enemy);
                    eventLayout.setVisibility(View.INVISIBLE);
                    break;
                case takeDrop:
                    TakeEvent te = (TakeEvent) e;
                    activity.player.collect(te.drop);
                    for (Item item:
                         activity.player.items) {
                        Log.d("Debug", "" + item.name);
                    }
                    eventLayout.setVisibility(View.INVISIBLE);
                    break;
                case leave:
                    LeaveEvent le = (LeaveEvent) e;
                    eventLayout.setVisibility(View.INVISIBLE);
                    break;
                case leaveDungeon:
                    eventLayout.setVisibility(View.INVISIBLE);
                    activity.exitDungeon();
                    break;
                case boss:
                    bse = (BattleStartEvent) e;
                    activity.launchBattle(bse.enemy);
                    eventLayout.setVisibility(View.INVISIBLE);
                    break;

                case toDisplay:
                    showEvent(e);
                    break;
            }
        }
    }

    void showEvent(Event event) {
        LinearLayout eventLayout = (LinearLayout) activity.findViewById(R.id.eventLayout);
        TextView nameView = (TextView) eventLayout.findViewById(R.id.eventName);
        TextView descView = (TextView) eventLayout.findViewById(R.id.eventDesc);
        ImageView imageView = (ImageView) eventLayout.findViewById(R.id.eventImage);
        GridView gridView = (GridView) eventLayout.findViewById(R.id.choiceGrid);
        nameView.setText(event.name);
        descView.setText(event.description);
        imageView.setImageResource(event.imageId);

        eventLayout.setVisibility(View.VISIBLE);

        ArrayList<Choice> choices = event.choices;

        for (Choice c :
                choices) {
            Log.d("Debug", c.name);
        }

        gridView.setAdapter(new ChoiseAdapter(choices, activity));
    }
}
