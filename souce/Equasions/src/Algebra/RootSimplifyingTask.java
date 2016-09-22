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
public class RootSimplifyingTask {
    public static String GenerateRootSimlifyinrTask(){
        String result = "\\sqrt{";
        Random rand = new Random();
        int quadraticPart = Util.Rndm.showRandomInteger(2, 7, rand, false);
        int restPart = Util.Rndm.showRandomInteger(2, 5, rand, false);
        result += quadraticPart * quadraticPart * restPart + "}";
        return result;
    }
    
}
