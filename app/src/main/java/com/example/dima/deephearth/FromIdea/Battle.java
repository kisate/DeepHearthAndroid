package com.example.dima.deephearth.FromIdea;

import com.example.dima.deephearth.BattleStates;
import com.example.dima.deephearth.SkillButton;
import com.example.dima.deephearth.UnitButton;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Dima on 27.03.2017.
 */
public class Battle {

    public Player player1, player2;
    public LinkedList<UnitButton> moveOrder = new LinkedList<>();
    public LinkedList<UnitButton> playerButtons = new LinkedList<>(), enemyButtons = new LinkedList<>();
    public int curNumber;
    public BattleStates state;

    public UnitButton activeButton;
    public SkillButton[] skillButtons;
}
