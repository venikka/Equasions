/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author venikka
 */
public class StringBiulding {
    public static String CoeffSign (int coeff){        
        if (coeff > 0 ){
            return " + " + Math.abs(coeff);
        }
        if (coeff < 0 ){
            return " - " + Math.abs(coeff);
        }
        return "";
    }
    public static String CoeffSignWithVar (int coeff, String var){   
        if (var == ""){
            return CoeffSign(coeff);
        }
        String finalCoeff = "" + Math.abs(coeff);
        if (Math.abs(coeff) == 1){
            finalCoeff = "";
        }
        if (coeff > 0 ){
            return " + " + finalCoeff + var;
        }
        if (coeff < 0 ){
            return " - " + finalCoeff + var;
        }
        return "";
    }
    public static String CoeffSignForBracket (int coeff){           
        String finalCoeff = "" + Math.abs(coeff);
        if (Math.abs(coeff) == 1){
            finalCoeff = "";
        }
        if (coeff > 0 ){
            return " + " + finalCoeff;
        }
        if (coeff < 0 ){
            return " - " + finalCoeff;
        }
        return "";
    }
    
    public static String FirstCoeffSignWithVar (int coeff, String var){   
        if (var == ""){
            return FirstCoeffSign(coeff);
        }
        String finalCoeff = "" + Math.abs(coeff);
        if (Math.abs(coeff) == 1){
            finalCoeff = "";
        }
        if (coeff > 0 ){
            return finalCoeff + var;
        }
        if (coeff < 0 ){
            return "- " + finalCoeff + var;
        }
        return "";
    }
    public static String FirstCoeffSignForBracket (int coeff){           
        String finalCoeff = "" + Math.abs(coeff);
        if (Math.abs(coeff) == 1){
            finalCoeff = "";
        }
        if (coeff > 0 ){
            return finalCoeff;
        }
        if (coeff < 0 ){
            return "- " + finalCoeff;
        }
        return "";
    }
    public static String FirstCoeffSign (int coeff){     
        if (coeff > 0 ){
            return "" + Math.abs(coeff);
        }
        if (coeff < 0 ){
            return "- " + Math.abs(coeff);
        }
        return "";
    }    
}
