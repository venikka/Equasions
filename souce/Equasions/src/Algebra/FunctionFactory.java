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
public class FunctionFactory {
    public static MathFunction multiplyFunctions (ArrayList<MathFunction> facs){
        ArrayList<MathFunction> factors = new ArrayList<MathFunction>();
        for (MathFunction newFactor: facs){
            boolean funcMatch = false;            
            for (MathFunction processedFactor : factors){
                if (newFactor instanceof PowerFunction){
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
        if (factors.size() == 0)
            return new MathFunction();
        if (factors.size() == 1)
            return factors.get(0);
        return new FunctionMultiplication(factors);
    }
   
}
