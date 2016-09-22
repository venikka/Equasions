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
public class PolynomeGenerator {
    public static Polynome IntegerRoots(String var, ArrayList<Integer> roots){
        Polynome result = new Polynome();
        for (Integer root : roots){
            if (result.members.isEmpty()){
                result = BasicBinome(var, root);
            }else{
                result = Polynome.Multiply(result, BasicBinome(var, root));
                result = Polynome.Canonize(result);
            }
        }
        return result;
    }
    public static Polynome BasicBinome (String var, Integer root){
        Polynome basicBinome = new Polynome();
        basicBinome.members.add(new PolynomeMember(var, 1, 1));
        basicBinome.members.add(new PolynomeMember(new MathFunction(), 0, root));            
        return basicBinome;
    }
    public static Polynome randomPolynome(String var, int power, int coeffRange, boolean monic){
        Polynome randomPolynome = new Polynome();
        Random rnd = new Random();
        for (int i = power; i > 0; i--){
            if (monic && i == power){
                randomPolynome.addMember(new PolynomeMember(var, i, 1));
            }else{
                randomPolynome.addMember(new PolynomeMember(var, i, Util.Rndm.showRandomInteger(-coeffRange, coeffRange, rnd, false)));
            }
        }
        randomPolynome.addMember(new MathFunction(), 0, Util.Rndm.showRandomInteger(-coeffRange, coeffRange, rnd, false));        
        return randomPolynome;
    }
    
}
