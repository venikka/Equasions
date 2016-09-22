/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algebra;

import java.util.Random;
import org.apache.batik.css.engine.value.css2.SrcManager;

/**
 *
 * @author venikka
 */
public class NegativeBracets {
    public static String generateNegativeString(int numberOfMembers){
        String answer = "";
        int numberOfBrackets = 2;        
        Random rand = new Random();
        int indexofFirstBracket = Util.Rndm.showRandomInteger(0, numberOfMembers - 6, rand, true);
        int indexofSecondBracket = Util.Rndm.showRandomInteger(indexofFirstBracket + 3, numberOfMembers - 3, rand, false);
        for (int i = 0; i < numberOfMembers; i++){            
            int member = Util.Rndm.showRandomInteger(-30, 30, rand, false);
            if (i == indexofFirstBracket + 3 || i == indexofSecondBracket + 3){
                answer += ")";
            }
            if (i == indexofFirstBracket || i == indexofSecondBracket){
                answer += " - (";
            }            
            if (member < 0)
                answer += " - " + Math.abs(member);
            else{
                if (i != 0 || i != indexofFirstBracket || i != indexofSecondBracket){
                    
                    answer += " + " + Math.abs(member);
                }else{
                    answer += Math.abs(member);
                }
            }
            
        }
        if (numberOfMembers == indexofSecondBracket){
            answer += ")";
        }
        return answer;
    }
}
