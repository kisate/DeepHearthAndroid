package com.example.dima.deephearth;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.dima.deephearth.FromIdea.Dungeon.Corridor;
import com.example.dima.deephearth.FromIdea.Dungeon.Room;
import com.example.dima.deephearth.FromIdea.Player;

/**
 * Created by Dima on 07.05.2017.
 */

public class RoomButton extends AppCompatImageButton {

    public Room room;
    public Player player;

    public boolean current, accessible, picked;

    public RoomButton(Context context) {
        super(context);
    }

    public RoomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setCurrent(boolean current) {
        FrameLayout parent = (FrameLayout) getParent();
        ImageView imageView = (ImageView) parent.getChildAt(1);
        if (current) {
            imageView.setImageResource(R.drawable.room_current);
            for (Corridor cor :
                    room.corridors) {
                if (cor != null) {
                    cor.corridorView.setAccessible(true);
                    cor.other.button.setAccessible(true);
                }
            }
        }
        else {
            imageView.setImageResource(0);
            for (Corridor cor :
                    room.corridors) {
                if (cor != null) {
                    cor.corridorView.setAccessible(false);
                    cor.other.button.setAccessible(false);
                }
            }
        }
        this.current = current;
    }

    public void setAccessible(boolean accessible) {
        FrameLayout parent = (FrameLayout) getParent();
        ImageView iv = (ImageView) parent.getChildAt(3);
        if (accessible) {
            iv.setAlpha(0f);
        }
        else iv.setAlpha(0.6f);
        this.accessible = accessible;
        if (accessible && room.covered && (player.uncoverChance > Math.random())) setCovered(false);
    }

    public void setPicked(boolean picked) {
        FrameLayout parent = (FrameLayout) getParent();
        ImageView imageView = (ImageView) parent.getChildAt(2);
        if (picked) {
            imageView.setImageResource(R.drawable.room_edge_selected);
        }
        else {
            imageView.setImageResource(R.drawable.room_edge);
        }
        this.picked = picked;
    }

    public void setMaxWidthToAll(int maxWidthToAll) {
        setMaxWidth(maxWidthToAll);
        ImageView im1, im2, im3;
        FrameLayout parent = (FrameLayout) getParent();
        im1 = (ImageView) parent.getChildAt(1);
        im2 = (ImageView) parent.getChildAt(2);
        im3 = (ImageView) parent.getChildAt(3);
        im1.setMaxWidth(maxWidthToAll);
        im2.setMaxWidth(maxWidthToAll);
        im3.setMinimumHeight(maxWidthToAll);
        im3.setMinimumWidth(maxWidthToAll);
    }

    public void update(){
        setImageResource(room.interactable.image);
    }

    public void setCovered(boolean covered) {
        room.covered = covered;
        FrameLayout parent = (FrameLayout) getParent();
        ImageView imageView = (ImageView) parent.getChildAt(1);
        if (covered) imageView.setImageResource(R.drawable.room_background);
        else imageView.setImageResource(0);
    }
}
