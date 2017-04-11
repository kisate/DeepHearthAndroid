package com.example.dima.deephearth.FromIdea.Dungeon;

import com.example.dima.deephearth.FromIdea.Main;
import com.example.dima.deephearth.FromIdea.Probability;

import java.util.*;

/**
 * Created by Dima on 01.04.2017.
 */
public class Room {
    //public LinkedList<Room> neighbours = new LinkedList<>();
    //public LinkedList<Corridor> corridors = new LinkedList<>();
    public LinkedList<LinkedList<Cell>> cells = new LinkedList<>();

    public int x, y;

    public Room(int x, int y){
        this.x = x;
        this.y = y;
        for (int i = 0; i < 7; i++) {
            LinkedList<Cell> temp = new LinkedList<>();
            for (int j = 0; j < 7; j++) {
                Cell cell = new Cell(i, j, CellTypes.Plane);
                temp.add(cell);
            }
            cells.add(temp);
        }
        Generate();
    }

    public void Generate(){}

//    public void Generate(){
//        Probability lPr, rPr, uPr, dPr = new Probability(1, 4);
//        LinkedList<Cell> temp = new LinkedList<>();
//        /*
//        for (LinkedList<Cell> templ:
//                cells) {
//            temp.add((LinkedList<Cell>) templ.clone());
//        }
//        */
//        int count = 1;
//        temp.add(cells.get(cells.size() / 2).get(cells.get(cells.size() / 2).size() / 2));
//        while (count < 500){
//            Cell cell = nextCell(temp);
//            cell.type = CellTypes.Plane;
//            count++;
//        }
//
//    }
//
//    public Cell nextCell(LinkedList<Cell> temp){
//        Cell cell = temp.get((int)(Math.random() * temp.size()));
//        if (!(cell.chance > Math.random())){
//            cell = nextCell(temp);
//        }
//        if (cell.x < 29) {
//            cells.get(cell.x + 1).get(cell.y).chance*=1.6;
//        }
//
//        if (cell.x > 0) {
//            cells.get(cell.x - 1).get(cell.y).chance*=1.6;
//        }
//
//
//        if (cell.y < 29) {
//            cells.get(cell.x).get(cell.y + 1).chance*=1.6;
//        }
//
//        if (cell.y > 0) {
//            cells.get(cell.x).get(cell.y - 1).chance*=1.6;
//        }
//        return cell;
//    }

    public void visualize() {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if(cells.get(i).get(j).type == CellTypes.Plane) System.out.print("[X]");
                else System.out.print("[ ]");
            }
            System.out.println();
        }
    }
/*
    public Cell nextCell(Probability lPr,Probability rPr,Probability uPr, Probability dPr, int x, int y) {



        Cell next = new Cell();
        return next
    }
*/
}
