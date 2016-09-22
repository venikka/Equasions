/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.util.Random;

/**
 *
 * @author venikka
 */
public class Rndm {

    public static int showRandomInteger(int aStart, int aEnd, Random aRandom, boolean includingNil) {
        if (aStart == aEnd)
            return aStart;
        if (aStart > aEnd) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        long range = (long) aEnd - (long) aStart + 1;
        long fraction = (long) (range * aRandom.nextDouble());
        int randomNumber = (int) (fraction + aStart);
        if (randomNumber == 0 && !includingNil) {
            return showRandomInteger(aStart, aEnd, aRandom, includingNil);
        }
        return randomNumber;
    }
    
}
