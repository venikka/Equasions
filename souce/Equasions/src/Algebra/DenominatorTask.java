/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algebra;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author venikka
 */
public class DenominatorTask {
    public static String GenerateTask(int numberOfFractions, int numbersRange){
        String result = "";
        ArrayList<Integer> denominatorPool = new ArrayList<Integer>();
        Random rand = new Random();
        int commonFactor = Util.Rndm.showRandomInteger(2, 10, rand, false);
        for (int i = 0; i < numberOfFractions; i++){            
            Integer denominator = Util.Rndm.showRandomInteger(1, numbersRange, rand, false) * commonFactor;
            while (denominatorPool.contains(denominator)){
                denominator = Util.Rndm.showRandomInteger(1, numbersRange, rand, false) * commonFactor;
            }
            denominatorPool.add(denominator);
            int numenator = Util.Rndm.showRandomInteger(1, numbersRange, rand, false);            
            while (BigInteger.valueOf(numenator).gcd(BigInteger.valueOf(denominator)).intValue()!= 1){
                numenator = Util.Rndm.showRandomInteger(1, numbersRange, rand, false);
            }
            int signum = Util.Rndm.showRandomInteger(-1, 1, rand, false);
            if (signum == -1){
                result += " - ";
            }else if (i != 0){
                result += " + ";
            }
            result += "\\frac {" + numenator + "} {" + denominator + "}";
        }
        return result;
    }
}
