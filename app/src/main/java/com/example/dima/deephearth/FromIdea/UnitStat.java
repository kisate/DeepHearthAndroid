package com.example.dima.deephearth.FromIdea;

import com.example.dima.deephearth.Modificator;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Created by Dima on 03.06.2017.
 */

public class UnitStat implements Serializable{
    public double clearValue;
    public LinkedList<Modificator> modificators = new LinkedList<>();
   

    public UnitStat(double clearValue) {
        this.clearValue = clearValue;
    }

    public double getValue() {
        return countValue();
    }

    double countValue() {
        double value = clearValue;

        for (Modificator m :
                modificators) {
            value = m.action(value);
        }

        return value;
    }
    
    public void addMod(Modificator m) {
        modificators.add(m);
        Collections.sort(modificators, new Comparator<Modificator>() {
            @Override
            public int compare(Modificator o1, Modificator o2) {
                return o1.order - o2.order;
            }
        });
    }
    public void removeMod(String id) {
        for (Modificator m :
                modificators) {
            if (m.id.equals(id)) {
                modificators.remove(m);
                break;
            }
        }
        Collections.sort(modificators, new Comparator<Modificator>() {
            @Override
            public int compare(Modificator o1, Modificator o2) {
                return o1.order - o2.order;
            }
        });
    }
}
