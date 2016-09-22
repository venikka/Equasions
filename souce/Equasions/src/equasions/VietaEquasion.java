/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equasions;

import Util.Rndm;
import Util.StringBiulding;
import java.util.Random;
/**
 *
 * @author venikka
 */
public class VietaEquasion {    
    public static String generateVietaEquasion (int range, boolean includingNilRoot){
        Random rand = new Random();
        int root1 = Rndm.showRandomInteger(-range, range, rand, includingNilRoot);
        int root2 = Rndm.showRandomInteger(-range, range, rand, includingNilRoot);
        int b = -(root1 + root2);        
        while (b == 0){
            root1 = Rndm.showRandomInteger(-range, range, rand, includingNilRoot);
            root2 = Rndm.showRandomInteger(-range, range, rand, includingNilRoot);
             b = -(root1 + root2);
        }
        return "x^2 " + StringBiulding.CoeffSignWithVar(root1+root2, "x") + StringBiulding.CoeffSign(root1 * root2) + " = 0";
    }
    public static String generateQuadraticEquasion (int range, boolean includingNilRoot){
        Random rand = new Random();
        
        int a = Rndm.showRandomInteger(-range, range, rand, false);
        int b = Rndm.showRandomInteger(-range, range, rand, true);
        int c = Rndm.showRandomInteger(-range, range, rand, true);        
        return StringBiulding.FirstCoeffSignWithVar(a, "x^2 ")  +  StringBiulding.CoeffSignWithVar(b, "x") + StringBiulding.CoeffSign(c) + " = 0";
    }
}
