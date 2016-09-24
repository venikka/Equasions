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
   
    public static String generateMeasurementUnitsTask (){
        String result = "";
        Random rnd = new Random();        
        MeasurementUnit unit = new MeasurementUnit();                
        ArrayList<String> finalUnits = unit.getDecompositionUnits(2);
        result = "Разложите " + unit.toString() + " на ";
        if (finalUnits.size() == 1){
            result += finalUnits.get(0);
        }else{
            for (int i = 0; i < finalUnits.size(); i++){
                if (i == finalUnits.size() - 1){
                    result += " и ";
                }else{
                    result += ", ";
                }
                result += finalUnits.get(i);
            }
        }
        return result;
    }
    public static String generateTexMeasurementUnitsTask (){
        String result = "\\mbox{ }";
        Random rnd = new Random();        
        MeasurementUnit unit = new MeasurementUnit();                
        ArrayList<String> finalUnits = unit.getDecompositionUnits(2);
        result = unit.toString() + " = ";        
        for (int i = 0; i < finalUnits.size(); i++){            
            result += "\\fbox{\\rule{5in}{0pt}\\rule[-0.1ex]{0pt}{1.5ex}}" + "  " + finalUnits.get(i) + "  ";
        }        
        return result + "\\\\";
    }
    /*public String decomposeAs(MeasurementUnits units, Integer numberToDecompose){
        return null;
    } */   
}
