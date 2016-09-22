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
public class EquasionSystem {
    

    public static String GenerateEquasionSystem3vars(){
    
    Random random = new Random();    
    int varRange = 5;
    int coeffRange = 5;
    
    int x = Util.Rndm.showRandomInteger(-varRange, varRange, random, true);
    int y = showRandomInteger(-varRange, varRange, random);
    int z = showRandomInteger(-varRange, varRange, random);
    int a1 = showRandomInteger(-coeffRange, coeffRange, random);
    int a2 = showRandomInteger(-coeffRange, coeffRange, random);
    int a3 = showRandomInteger(-coeffRange, coeffRange, random);
    int b1 = showRandomInteger(-coeffRange, coeffRange, random);
    int b2 = showRandomInteger(-coeffRange, coeffRange, random);
    int b3 = showRandomInteger(-coeffRange, coeffRange, random);
    int c1 = showRandomInteger(-coeffRange, coeffRange, random);
    int c2 = showRandomInteger(-coeffRange, coeffRange, random);
    int c3 = showRandomInteger(-coeffRange, coeffRange, random);
    
    int a0 = a1*x + a2*y + a3*z;
    int b0 = b1*x + b2*y + b3*z;
    int c0 = c1*x + c2*y + c3*z;
    
    String eq1 = a1 + "x " + signum(a2) + " " + Math.abs(a2) + "y " + signum(a3) + " " + Math.abs(a3) + "z = " + a0;
    String eq2 = b1 + "x " + signum(b2) + " " + Math.abs(b2) + "y " + signum(b3) + " " + Math.abs(b3) + "z = " + b0;
    String eq3 = c1 + "x " + signum(c2) + " " + Math.abs(c2) + "y " + signum(c3) + " " + Math.abs(c3) + "z = " + c0;
    
    log (eq1);
    log (eq2);
    log (eq3);
    log (x + " " + y + " "+ z);
    String result = "";
    result += "\\begin{cases}\n" +
    eq1 + "\\\\\n" +
    eq2 + "\\\\\n" +
    eq3 +"\n" +
    "\\end{cases}";
    return result;
    //log("Done.");
  }
    public static String GenerateEquasionSystem2vars(){
    
    Random random = new Random();    
    int varRange = 5;
    int coeffRange = 5;
    
    int x = showRandomInteger(-varRange, varRange, random);
    int y = showRandomInteger(-varRange, varRange, random);
    
    int a1 = Util.Rndm.showRandomInteger(-coeffRange, coeffRange, random, false);
    int a2 = Util.Rndm.showRandomInteger(-coeffRange, coeffRange, random, false);
    int b1 = Util.Rndm.showRandomInteger(-coeffRange, coeffRange, random, false);
    int b2 = Util.Rndm.showRandomInteger(-coeffRange, coeffRange, random, false);
    
    
    int a0 = a1*x + a2*y;
    int b0 = b1*x + b2*y;
    
    String eq1 = Util.StringBiulding.FirstCoeffSignWithVar(a1, "x") + Util.StringBiulding.CoeffSignWithVar(a2, "y") + " = " + a0 ; //a1 + "x " + signum(a2) + " " + Math.abs(a2) + "y = " + a0;
    String eq2 = Util.StringBiulding.FirstCoeffSignWithVar(b1, "x") + Util.StringBiulding.CoeffSignWithVar(b2, "y") + " = " + b0; //b1 + "x " + signum(b2) + " " + Math.abs(b2) + "y = " +  b0;    
    
    log (eq1);
    log (eq2);    
    log (x + " " + y);
    String result = "";
    result += "\\begin{cases}\n" +
    eq1 + "\\\\\n" +
    eq2 + "\n" +    
    "\\end{cases}";
    return result;
    //log("Done.");
  }
    public static String GenerateEquasionSystem(int numberOfVars){
        String resultTex = "";
        Random random = new Random();    
        int varRange = 5;
        int coeffRange = 5;
        ArrayList<Integer> vars = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> equasions = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < numberOfVars; i++){
            vars.add(showRandomInteger(-varRange, varRange, random));            
        }
        resultTex += "\\begin{cases}\n";
        for (int i = 0; i< numberOfVars; i++){
            ArrayList<Integer> equasion = new ArrayList<Integer>();
            Integer equasionSum = 0;            
            for (int j = 0; j < numberOfVars; j ++){
                Integer coeff = Util.Rndm.showRandomInteger(-coeffRange, coeffRange, random, true);
                equasion.add(coeff);
                equasionSum += vars.get(j) * coeff;
                if (j == 0){
                    resultTex += Util.StringBiulding.FirstCoeffSignWithVar(coeff, systemVarName(numberOfVars, j)) + " ";
                }else{
                    resultTex += Util.StringBiulding.CoeffSignWithVar(coeff, systemVarName(numberOfVars, j)) + " ";                    
                }
                equasion.add(-equasionSum);                
            }            
            resultTex += " = "+ -equasionSum + "\\\\\n";
            
        }   
        resultTex += "\\end{cases}";
        return resultTex;
    }
    
    public static String systemVarName(int numberOfVars, int currentVarIndex){
        if (numberOfVars <= 3){
            switch (currentVarIndex){
                case 0 : return "x";
                case 1 : return "y";
                case 2 : return  "z";
            }
        }else{
            return "x_" + currentVarIndex;
        }
        return null;
    }
  
  private static int showRandomInteger(int aStart, int aEnd, Random aRandom){
    if (aStart > aEnd) {
      throw new IllegalArgumentException("Start cannot exceed End.");
    }
    //get the range, casting to long to avoid overflow problems
    long range = (long)aEnd - (long)aStart + 1;
    // compute a fraction of the range, 0 <= frac < range
    long fraction = (long)(range * aRandom.nextDouble());
    int randomNumber =  (int)(fraction + aStart);    
    return  randomNumber;
  }
  
  private static void log(String aMessage){
    System.out.println(aMessage);
  }
  public static String signum (int number){
      if (number < 0){
          return "-";
      }
      else return "+";
  }
}
