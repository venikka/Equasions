/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algebra;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author venikka
 */
public class MeasurementUnitsTask {
    public static enum MeasurementUnits{
        number,
        mass,
        length,
        area,
        time
    }    
    public static String generateMeasurementUnitsTask (){
        String result = "";
        Random rnd = new Random();        
        NumberUnit.Units unit = NumberUnit.Units.getRandom();
        int maxNumber = 2000000000 / unit.getInMinimalUnits();
        Integer numberToDecompose = Util.Rndm.showRandomInteger(1, maxNumber, rnd, false);
        int rndUnit = Util.Rndm.showRandomInteger(0, NumberUnit.Units.values().length - 1, rnd, true);
        
        ArrayList<String> unitNames = NumberUnit.getDecompositionUnits(numberToDecompose, unit);
        ArrayList<String> finalUnits = new ArrayList<String>();        
        finalUnits.add(unitNames.get(Util.Rndm.showRandomInteger(0, unitNames.size() - 1,  rnd, false)));
        finalUnits.add(unitNames.get(0));
        result = "Разложите " + numberToDecompose + " " + unit.getName() + " на " + finalUnits.get(0) + " и " + finalUnits.get(1);
        return result;
    }
    public String decomposeAs(MeasurementUnits units, Integer numberToDecompose){
        return null;
    }    
}
