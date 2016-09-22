/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algebra;

/**
 *
 * @author venikka
 */
public class PowerFunction extends MathFunction {
    public String variable;    
    public PowerFunction (String var, int pow){
        variable = var;
        if (var == ""){
            power = 0;
        }else
            power = pow;
        type = "power function";
    }    
    public boolean EqualBases (MathFunction functionToCompare){
        if (functionToCompare instanceof PowerFunction){
            PowerFunction f = (PowerFunction)functionToCompare;
            if (f.variable.equals(variable))
                return true;
        }
        return false;
    }
    @Override
    public boolean Equals(MathFunction functionToCompare){
        if (functionToCompare instanceof PowerFunction){
            PowerFunction funcToCompare = (PowerFunction)functionToCompare;
            if (variable == funcToCompare.variable && power == funcToCompare.power){
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString (){
        if (variable == "" || power == 0)
            return "";        
        if (power != 1)
            return variable + "^" + power;
        else{
            return  variable;
        }
    }
   
}
