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
public class LongDivisionTask {
    public static String generateLongDivisionTaskSingleDigitDivisor(int dividedNumberLength){
        String result = "";
        Random rnd = new Random();
        int divisor = Util.Rndm.showRandomInteger(2, 9, rnd, false);
        int minRatio = 100/divisor ;        
        int maxRatio = 1000 / divisor;        
        int ratio = Util.Rndm.showRandomInteger(minRatio, maxRatio, rnd, false);
        result += ratio*divisor + " : " + divisor;
        return result;
    }
}
