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
    public static String generateLongDivisionTaskSingleDigitDivisor(int dividedNumberLength, int divisorLength){
        String result = "";
        Random rnd = new Random();
        int minDivisor = Math.max((int)Math.pow(10, divisorLength - 1), 2);
        int maxDivisor = (int)Math.pow(10, divisorLength) - 1;
        int divisor = Util.Rndm.showRandomInteger(minDivisor, maxDivisor, rnd, false);
        int minRatio = (int)Math.pow(10, dividedNumberLength - 1)/divisor ;        
        int maxRatio = (int)Math.pow(10, dividedNumberLength) / divisor;        
        int ratio = Util.Rndm.showRandomInteger(minRatio, maxRatio, rnd, false);
        result += ratio*divisor + " : " + divisor;
        return result;
    }
}
