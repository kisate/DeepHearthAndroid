package com.example.dima.deephearth;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dima.deephearth.FromIdea.ComputerIntellect;
import com.example.dima.deephearth.FromIdea.Dungeon.Choice;
import com.example.dima.deephearth.FromIdea.Dungeon.Corridor;
import com.example.dima.deephearth.FromIdea.Dungeon.Drop;
import com.example.dima.deephearth.FromIdea.Dungeon.Dungeon;
import com.example.dima.deephearth.FromIdea.Dungeon.Event;
import com.example.dima.deephearth.FromIdea.Dungeon.Interactable;
import com.example.dima.deephearth.FromIdea.Dungeon.Interactables.Empty;
import com.example.dima.deephearth.FromIdea.Dungeon.Interactables.Enemy;
import com.example.dima.deephearth.FromIdea.Dungeon.Interactables.Treasue;
import com.example.dima.deephearth.FromIdea.Dungeon.Room;
import com.example.dima.deephearth.FromIdea.Dungeon.Soul;
import com.example.dima.deephearth.FromIdea.Dungeon.SoulSizes;
import com.example.dima.deephearth.FromIdea.Hero;
import com.example.dima.deephearth.FromIdea.Heroes.HeroConstructor;
import com.example.dima.deephearth.FromIdea.HumanIntellect;
import com.example.dima.deephearth.FromIdea.Items.FireSword;
import com.example.dima.deephearth.FromIdea.Player;
import com.example.dima.deephearth.FromIdea.PlayerConstructor;
import com.example.dima.deephearth.FromIdea.PlayerSkills.Curse;
import com.example.dima.deephearth.FromIdea.PlayerSkills.Renewal;
import com.example.dima.deephearth.FromIdea.PlayerSkills.UndeadRage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class DungeonActivity extends AppCompatActivity implements View.OnClickListener{

    Player player;
    Dungeon dungeon;
    HeroConstructor constructor = new HeroConstructor();
    public DungeonEventHandler handler;

    int roomWidth, corWidth, corHeight, count;
    HashMap<Pair<Integer, Integer>, Integer> map = new HashMap<>();
    LinkedList<Pair <Room,Room>> rooms = new LinkedList<>();
    boolean[][] checkedRooms;

    RoomButton pickedRoomButton, currentRoomButton, enterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        player = getPlayer();
        int size = getIntent().getIntExtra("Size", 5);
        handler = new DungeonEventHandler(this);

        Player player2 = PlayerConstructor.construct(new ComputerIntellect());
        player2.team.add(constructor.constructSwordsman("P21", player2.team));
        player2.team.add(constructor.constructHealer("P22", player2.team));
        player2.team.add(constructor.constructArcher("P23", player2.team));
        player2.team.add(constructor.constructArcher("P24", player2.team));

        Player player3 = PlayerConstructor.construct(new ComputerIntellect());
        player3.team.add(constructor.constructSwordsman("Lonely knight", player3.team));

        Drop drop1 = new Drop();

        drop1.add(new FireSword());
        drop1.add(new Soul(SoulSizes.Common));

        Drop drop2 = new Drop();

        drop2.add(new Soul(SoulSizes.Legendary));

        LinkedList<Pair<Interactable, Integer>> pairs = new LinkedList<>();
        pairs.add(new Pair<Interactable, Integer>(new Empty(handler), 6));
        pairs.add(new Pair<Interactable, Integer>(new Enemy(player2, handler), 1));
        pairs.add(new Pair<Interactable, Integer>(new Enemy(player3, handler), 2));
        pairs.add(new Pair<Interactable, Integer>(new Treasue(drop1, handler), 3));
        pairs.add(new Pair<Interactable, Integer>(new Treasue(drop2, handler), 1));

        dungeon = new Dungeon(size, pairs, handler);
        checkedRooms = new boolean[dungeon.maxX - dungeon.minX + 1][dungeon.maxY - dungeon.minY + 1];
        Display display = getWindowManager().getDefaultDisplay();
        Point screenSize = new Point();
        display.getSize(screenSize);
        corWidth = screenSize.x/15;
        Drawable c = ContextCompat.getDrawable(this, R.drawable.corridor_horizontal);
        Drawable r = ContextCompat.getDrawable(this, R.drawable.room);
        corHeight = corWidth*c.getIntrinsicHeight()/c.getIntrinsicWidth();
        roomWidth = corWidth*r.getIntrinsicWidth()/c.getIntrinsicWidth();

        RelativeLayout dungeon_layout = (RelativeLayout) findViewById(R.id.activity_dungeon);
        int u = (roomWidth + corWidth);
        int mapHeight = (dungeon.maxY - dungeon.minY + 1)*u;
        int mapWidth  = (dungeon.maxX - dungeon.minX + 1)*u;
        FrameLayout.LayoutParams lp2 = new FrameLayout.LayoutParams(mapWidth, mapHeight);
        dungeon_layout.setLayoutParams(lp2);

        count = 1;

        FrameLayout centerB = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.room, dungeon_layout, false);
        RoomButton rb = (RoomButton) centerB.getChildAt(0);
        rb.setTag("room");
        rb.setMaxWidthToAll(roomWidth);
        rb.setRoom(dungeon.rooms.get(0));
        rb.setOnClickListener(this);
        rb.setPlayer(player);
        rb.setAccessible(true);
        dungeon.rooms.get(0).setButton(rb);
        dungeon_layout.addView(centerB);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) centerB.getLayoutParams();
        lp.setMargins(0, u*dungeon.maxY, u*dungeon.maxX, 0);

        lp.setMarginEnd(u*dungeon.maxX);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        centerB.setId(count);
        centerB.setLayoutParams(lp);
        enterButton = rb;
        count++;

        map.put(new Pair<>(0,0), 1);


        checkedRooms[-dungeon.minX][-dungeon.minY] = true;

        for (Corridor cor :
                dungeon.rooms.get(0).corridors) {
            if(cor != null && !checkedRooms[cor.other.x-dungeon.minX][cor.other.y-dungeon.minY]) {
                rooms.add(new Pair<Room, Room>(cor.other, rb.room));
            }
        }

        Button button = (Button) findViewById(R.id.playerButton);
        button.setOnClickListener(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        while(rooms.size() > 0) checkRoom(rooms.get(0), rooms);

        enterButton.setCurrent(true);
        currentRoomButton = enterButton;
        roomButtonClick(currentRoomButton);
    }

    void checkRoom(Pair<Room, Room> pair, LinkedList<Pair <Room,Room>> rooms) {
        RelativeLayout parent = (RelativeLayout) findViewById(R.id.activity_dungeon);
        Room base = pair.second, next = pair.first;
        FrameLayout nextB = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.room, parent, false);
        RoomButton rb = (RoomButton) nextB.getChildAt(0);
        rb.setTag("room");
        rb.setRoom(next);
        rb.setOnClickListener(this);
        next.setButton(rb);
        rb.setMaxWidthToAll(roomWidth);
        rb.setPlayer(player);
        rb.setAccessible(false);
        rb.setCovered(true);
        parent.addView(nextB);
        nextB.setId(count);
        RelativeLayout.LayoutParams lp =(RelativeLayout.LayoutParams) nextB.getLayoutParams();

        FrameLayout cvParent = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.corridor, parent, false);
        parent.addView(cvParent);
        CorridorView cv = (CorridorView) cvParent.getChildAt(0);
        cv.setAccessible(false);
        RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams) cvParent.getLayoutParams();

        if (next.x < base.x) {
            lp.addRule(RelativeLayout.ALIGN_RIGHT, map.get(new Pair<>(base.x, base.y)));
            lp.addRule(RelativeLayout.ALIGN_TOP, map.get(new Pair<>(base.x, base.y)));
            lp.setMargins(0, 0, corWidth + roomWidth, 0);
            nextB.setLayoutParams(lp);

            Corridor corridor = base.corridors[3];
            Corridor revCorridor = corridor.revCorridor;
            revCorridor.setCorridorView(cv);
            corridor.setCorridorView(cv);
            cv.setCorridor(corridor);
            cv.setMetrics(corWidth, corHeight);
            lp2.addRule(RelativeLayout.ALIGN_RIGHT, map.get(new Pair<>(base.x, base.y)));
            lp2.addRule(RelativeLayout.ALIGN_TOP, map.get(new Pair<>(base.x, base.y)));
            lp2.setMargins(0, (roomWidth - corHeight)/2, roomWidth, 0);
            cvParent.setLayoutParams(lp2);
        }
        else if(next.x > base.x) {
            lp.addRule(RelativeLayout.ALIGN_LEFT, map.get(new Pair<>(base.x, base.y)));
            lp.addRule(RelativeLayout.ALIGN_TOP, map.get(new Pair<>(base.x, base.y)));
            lp.setMargins(corWidth + roomWidth, 0, 0, 0);
            nextB.setLayoutParams(lp);

            Corridor corridor = base.corridors[1];
            Corridor revCorridor = corridor.revCorridor;
            revCorridor.setCorridorView(cv);
            corridor.setCorridorView(cv);
            cv.setCorridor(corridor);
            cv.setMetrics(corWidth, corHeight);
            lp2.addRule(RelativeLayout.ALIGN_LEFT, map.get(new Pair<>(base.x, base.y)));
            lp2.addRule(RelativeLayout.ALIGN_TOP, map.get(new Pair<>(base.x, base.y)));
            lp2.setMargins(roomWidth, (roomWidth - corHeight)/2, 0, 0);
            cvParent.setLayoutParams(lp2);
        }
        else if (next.y < base.y) {
            lp.addRule(RelativeLayout.ALIGN_TOP, map.get(new Pair<>(base.x, base.y)));
            lp.addRule(RelativeLayout.ALIGN_RIGHT, map.get(new Pair<>(base.x, base.y)));
            lp.setMargins(0, corWidth + roomWidth, 0, 0);
            nextB.setLayoutParams(lp);

            Corridor corridor = base.corridors[2];
            Corridor revCorridor = corridor.revCorridor;
            revCorridor.setCorridorView(cv);
            corridor.setCorridorView(cv);
            cv.setCorridor(corridor);
            cv.setMetrics(corWidth, corHeight);
            lp2.addRule(RelativeLayout.ALIGN_RIGHT, map.get(new Pair<>(base.x, base.y)));
            lp2.addRule(RelativeLayout.ALIGN_TOP, map.get(new Pair<>(base.x, base.y)));
            lp2.setMargins(0, roomWidth, (roomWidth - corHeight)/2, 0);
            cvParent.setLayoutParams(lp2);
        }
        else if(next.y > base.y) {
            lp.addRule(RelativeLayout.ALIGN_BOTTOM, map.get(new Pair<>(base.x, base.y)));
            lp.addRule(RelativeLayout.ALIGN_RIGHT, map.get(new Pair<>(base.x, base.y)));
            lp.setMargins(0, 0, 0, corWidth + roomWidth);
            nextB.setLayoutParams(lp);

            Corridor corridor = base.corridors[0];
            Corridor revCorridor = corridor.revCorridor;
            revCorridor.setCorridorView(cv);
            corridor.setCorridorView(cv);
            cv.setCorridor(corridor);
            cv.setMetrics(corWidth, corHeight);
            lp2.addRule(RelativeLayout.ALIGN_RIGHT, map.get(new Pair<>(base.x, base.y)));
            lp2.addRule(RelativeLayout.ALIGN_BOTTOM, map.get(new Pair<>(base.x, base.y)));
            lp2.setMargins(0, 0, (roomWidth - corHeight)/2, roomWidth);
            cvParent.setLayoutParams(lp2);
        }

        for (Corridor c :
                next.corridors) {
            if(c != null && !checkedRooms[c.other.x-dungeon.minX][c.other.y-dungeon.minY]) {
                rooms.add(new Pair<Room, Room>(c.other, next));
            }
        }

        map.put(new Pair<Integer, Integer>(next.x, next.y), count);
        checkedRooms[next.x - dungeon.minX][next.y - dungeon.minY] = true;
        count++;
        rooms.remove(pair);
    }

    void roomButtonClick(RoomButton roomButton) {
        if (roomButton.picked) {
            goToRoom(roomButton);

        }
        if (roomButton.accessible) {
            if (pickedRoomButton != null) {
                pickedRoomButton.setPicked(false);
                pickedRoomButton = null;
            }
            roomButton.setPicked(true);
            pickedRoomButton = roomButton;
        }

    }

    @Override
    public void onClick(View v) {
        if(v.getTag() == "room"){
            roomButtonClick((RoomButton) v);
        }
        if (v.getId() == R.id.playerButton) {
            Intent intent = new Intent(this, PlayerInspectorActivity.class);
            intent.putExtra("Player", player);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        player = (Player) data.getSerializableExtra("Player");
    }

    public void launchBattle(Player player2){
        Intent intent = new Intent(this, BattleActivity.class);
        player.team.get(1).moves = 2;
        player.team.get(1).maxMoves = 2;
        intent.putExtra("Player 1", player);
        intent.putExtra("Player 2", player2);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //thread.notify();
    }

    @Override
    public void onBackPressed() {
    }

    void goToRoom(RoomButton roomButton) {
        Corridor cor = new Corridor(currentRoomButton.room, roomButton.room, CorridorView.HORIZONTAL);

        if (currentRoomButton.room.y < roomButton.room.y) {
            cor = currentRoomButton.room.corridors[0];
        }
        else if (currentRoomButton.room.x < roomButton.room.x) {
            cor = currentRoomButton.room.corridors[1];

        }
        else if (currentRoomButton.room.y > roomButton.room.y) {
            cor = currentRoomButton.room.corridors[2];

        }
        else if (currentRoomButton.room.x > roomButton.room.x){
            cor = currentRoomButton.room.corridors[3];
        }

        leaveRoom();
        enterCorridor(cor);
        leaveCorridor();
        enterRoom(roomButton.room);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }

    private Player getPlayer(){
        /*Player player = PlayerConstructor.construct(new HumanIntellect());
        player.team.add(constructor.constructSwordsman("P11", player.team));
        player.team.add(constructor.constructArcher("P12", player.team));
        player.team.add(constructor.constructArcher("P13", player.team));
        player.team.add(constructor.constructHealer("P14", player.team));*/

        player = (Player) getIntent().getSerializableExtra("startPlayer");

        Hero hero = (Hero) player.team.get(0);
        hero.inventory.add(new FireSword());
        player.skills.add(new Renewal(player));
        player.skills.add(new Curse(player));
        player.skills.add(new UndeadRage(player));
        return player;
    }

    void enterRoom (Room next) {
        currentRoomButton = next.button;
        next.button.setAccessible(true);
        next.button.setCurrent(true);
        next.interactable.interact(player);
        if (next.interactable.getClass() != Empty.class)next.setInteractable(new Empty(handler));
        next.button.update();
    }

    void leaveRoom () {
        currentRoomButton.setCurrent(false);
        currentRoomButton = null;
    }

    void enterCorridor(Corridor next) {

    }

    void leaveCorridor() {

    }

    void showEvent(Event event) {
        LinearLayout eventLayout = (LinearLayout) findViewById(R.id.eventLayout);
        TextView nameView = (TextView) eventLayout.findViewById(R.id.eventName);
        TextView descView = (TextView) eventLayout.findViewById(R.id.eventDesc);
        ImageView imageView = (ImageView) eventLayout.findViewById(R.id.eventImage);
        nameView.setText(event.name);
        descView.setText(event.description);
        imageView.setImageResource(event.imageId);

        ArrayList<Choice> choices = event.choices;

    }
}
