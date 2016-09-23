/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algebra;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Set;


/**
 *
 * @author venikka
 */

public class MeasurementUnit{           
    public int valueInMinimalUnits;    
    public Units units;
    public int value;
    public ArrayList<Units> minorUnits;  
    public MeasurementUnitTypes unitType;
    public MeasurementUnit (){
        Random rnd = new Random();
        unitType = getRandomUnitType();
        units = getRandomUnit(unitType);        
        value = Util.Rndm.showRandomInteger(0, unitType.getMaxValueInMinimalUnits()/units.getInMinimalUnits(), rnd, false);
        valueInMinimalUnits =  value * units.getInMinimalUnits();
        minorUnits = Units.getAllUnitsOfType(unitType);
        for (Units u : Units.values()){
            if (!(valueInMinimalUnits / u.getInMinimalUnits() > 0)){
                minorUnits.remove(u);
            }
            else{
                break;
            }
        }
    }   
    public ArrayList<String> getDecompositionUnits(int numberOfLesserMeasurementUnits){
        Random rnd = new Random();
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<Units> decompositionUnits = Units.getAllUnitsOfType(unitType);
        boolean includedLesserUnit = false;
        for (Units u : decompositionUnits){
            if ((valueInMinimalUnits > u.getInMinimalUnits()&& u.getName() != units.getName())){
                if (u.getInMinimalUnits() < units.getInMinimalUnits()){
                    if (!includedLesserUnit){
                        result.add(u.getName());
                        includedLesserUnit = true;
                    }
                }else{
                    result.add(u.getName());
                }
            }            
        }
        while (result.size() > numberOfLesserMeasurementUnits){
            result.remove(Util.Rndm.showRandomInteger(1, result.size() - 1, rnd, false));
        }
        Collections.reverse(result);
        return result;
    }
    public ArrayList<String> getLesserUnits(int numberOfLesserMeasurementUnits){
        ArrayList<String> result = new ArrayList<String>();
        Random rnd = new Random();
        for (Units u : minorUnits){
            result.add(u.getName());
        }
        if (result.size() < numberOfLesserMeasurementUnits){
            return result;
        }else{
            for (int i = 0; i < minorUnits.size() - numberOfLesserMeasurementUnits; i++){
                result.remove(Util.Rndm.showRandomInteger(1, result.size() - 1, rnd, false));
            }
            Collections.reverse(result);
            return result;
        }
    }
    public Units getRandomUnit (){
        return Units.values()[(int)(Math.random() * Units.values().length)];
    }   
    public Units getRandomUnit (MeasurementUnitTypes type){
        ArrayList<Units> u = Units.getAllUnitsOfType(type);
        Random rnd = new Random();
        return u.get(Util.Rndm.showRandomInteger(0, u.size() - 1, rnd, true));
    } 
    public Units getRandomUnitExceptMaximal (MeasurementUnitTypes type){
        ArrayList<Units> u = Units.getAllUnitsOfType(type);
        Random rnd = new Random();
        return u.get(Util.Rndm.showRandomInteger(1, u.size() - 1, rnd, true));
    } 
    public MeasurementUnitTypes getRandomUnitType (){
        return MeasurementUnitTypes.values()[(int)(Math.random() * MeasurementUnitTypes.values().length)];
    }    
    @Override
    public String toString(){
        String result = Integer.toString(value);
        if (units != Units.ones){
            result +=  " " + units.getName();
        }
        return result;
    }
    
    public static enum Units{
        //numbers
        ones (1, "ед.", MeasurementUnitTypes.number),
        tens (10, "дес.", MeasurementUnitTypes.number),
        hundreds (100, "сот.", MeasurementUnitTypes.number),
        thousands(1000, "тыс.", MeasurementUnitTypes.number),
        tenthousands(10000, "дес. тыс.", MeasurementUnitTypes.number),
        hundredthousands (100000, "сот. тыс.", MeasurementUnitTypes.number),
        millions (1000000, "млн.", MeasurementUnitTypes.number),
        tenmillions (10000000, "дес. млн.", MeasurementUnitTypes.number),
        hundredmillions (100000000, "сот. млн.", MeasurementUnitTypes.number),
        billions (1000000000, "млрд.", MeasurementUnitTypes.number),
        //Mass
        grams (1, "г.", MeasurementUnitTypes.mass),
        kilograms (1000, "кг.", MeasurementUnitTypes.mass),
        centners (100000, "ц.", MeasurementUnitTypes.mass),
        tons (1000000, "т.", MeasurementUnitTypes.mass),
        //length
        milimeters(1, "мм.", MeasurementUnitTypes.length),
        cantimeters(10, "см.", MeasurementUnitTypes.length),
        decemeters(100, "дм.", MeasurementUnitTypes.length),
        meters(1000, "м.", MeasurementUnitTypes.length),
        kiilometers(1000000, "км.", MeasurementUnitTypes.length),        
        //time
        seconds (1, "сек.", MeasurementUnitTypes.time),
        minutes (60, "мин.", MeasurementUnitTypes.time),
        hours (360, "ч.", MeasurementUnitTypes.time),
        days (8640, "сут.", MeasurementUnitTypes.time),                
        ;
        private final int inMinimalUnits;
        private final String name;
        private final MeasurementUnitTypes typeOfUnit;
        Units (int inMinimalUnits, String name, MeasurementUnitTypes unitType){
            this.inMinimalUnits = inMinimalUnits;
            this.name = name;
            this.typeOfUnit = unitType;
        }
        public int getInMinimalUnits() {return inMinimalUnits;}
        public String getName () {return name;}        
        public MeasurementUnitTypes getUnitType (){return typeOfUnit;}
        public static ArrayList<Units> getAllUnitsOfType (MeasurementUnitTypes type){
            ArrayList<Units> resut = new ArrayList<Units>();
            for(Units u :values()){
                if (u.getUnitType() == type)
                    resut.add(u);
            }
            return resut;
        }
    }
     public static enum MeasurementUnitTypes{
        number (2000000000),
        mass (200000000),
        length (100000000),
        //area,
        time (70000);
        private final int maxValueInMinimalUnits;

        private MeasurementUnitTypes(int maxinMinUnits) {
            this.maxValueInMinimalUnits = maxinMinUnits;
        }
        public int getMaxValueInMinimalUnits (){
            return maxValueInMinimalUnits;
        }
        
    }    
}
