/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algebra;

import java.util.Random;

/**
 *
 * @author venikka
 */
public class FullSquareTask {
    public static String GenerateFullSquareTask (boolean squareDifferenceSolvable, int coeffRange){
        String result = "";
        Random rand = new Random();
        int firstCoeff = Util.Rndm.showRandomInteger(-coeffRange, coeffRange, rand, false);
        int secondCoeff = Util.Rndm.showRandomInteger(-coeffRange, coeffRange, rand, false);
        int lastCoeff = Util.Rndm.showRandomInteger(-coeffRange, coeffRange, rand, false);        
        if (squareDifferenceSolvable){
            lastCoeff = (int)Math.pow(secondCoeff, 2) - (int)Math.pow(lastCoeff, 2);
        }
        while (lastCoeff == 0){
        lastCoeff = Util.Rndm.showRandomInteger(-coeffRange, coeffRange, rand, false);        
            if (squareDifferenceSolvable){
                lastCoeff = (int)Math.pow(secondCoeff, 2) - (int)Math.pow(lastCoeff, 2);
            }
        }
        result += Util.StringBiulding.FirstCoeffSignWithVar((int)Math.pow(firstCoeff, 2),"x^2 ") + Util.StringBiulding.CoeffSignWithVar(2*firstCoeff*secondCoeff, "x") + " " + Util.StringBiulding.CoeffSign(lastCoeff);
        return result;
    }
}
