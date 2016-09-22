/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algebra;

import java.util.ArrayList;

/**
 *
 * @author venikka
 */
public class FunctionMultiplication extends MathFunction {
    public ArrayList<MathFunction> factors;
    public FunctionMultiplication (ArrayList<MathFunction> facs){
        type = "function multiplication";
        factors = new ArrayList<MathFunction>();
        for (MathFunction newFactor: facs){
            boolean funcMatch = false;            
            for (MathFunction processedFactor : factors){
                if (newFactor  instanceof PowerFunction){
                    PowerFunction newFunction = (PowerFunction)newFactor;
                    if (newFunction.EqualBases(processedFactor)) {
                        processedFactor.power += newFactor.power;
                        funcMatch = true;
                        break;
                    }
                }
            }
            if (!funcMatch && newFactor.power != 0 && newFactor instanceof PowerFunction){
                PowerFunction newPowerFuncFactor = new PowerFunction(((PowerFunction)newFactor).variable, newFactor.power);
                factors.add(newPowerFuncFactor);
            }
        }
    }
    
    @Override
    public String toString (){
        String result = "";
        for (int i = 0; i < factors.size(); i ++ ){
            MathFunction function = factors.get(i);
            result += function.toString();
            if (factors.indexOf(function) != factors.size() - 1 && !factors.get(i+1).toString().equals(""))
                result += " * ";
        }
        return result;
    }
}
