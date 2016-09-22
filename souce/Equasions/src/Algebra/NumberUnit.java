/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algebra;

import java.util.ArrayList;

/**
 *
 * @author venikka
 */

public class NumberUnit{       

    public static ArrayList<String> getDecompositionUnits(int numberToDecompose, Units unit) {
        int number = numberToDecompose * unit.getInMinimalUnits();
        ArrayList<String> results = new ArrayList<String>();
        for (Units u : Units.values()){
            if (number / u.getInMinimalUnits() > 0){
                results.add(u.getName());
            }
        }
        return results;
    }
    public enum Units{
        ones (1, "ед."),
        tens (10, "дес"),
        hundreds (100, "сот."),
        thousands(1000, "тыс."),
        tenthousands(10000, "дес. тыс."),
        hundredthousands (100000, "сот. тыс."),
        millions (1000000, "млн."),
        tenmillions (100000000, "дес. млн."),
        hundredmillions (100000000, "сот. млн."),
        billions (1000000000, "млрд.");
        private final int inMinimalUnits;
        private final String name;
        Units (int inMinimalUnits, String name){
            this.inMinimalUnits = inMinimalUnits;
            this.name = name;
        }
        public int getInMinimalUnits() {return inMinimalUnits;}
        public String getName () {return name;}
        public static Units getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }
    }       
}
