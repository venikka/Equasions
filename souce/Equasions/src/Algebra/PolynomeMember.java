
package Algebra;

/**
 *
 * @author venikka
 */

public class PolynomeMember extends MathFunction{
    MathFunction func;    
    int coeff;
//TODO: remove power from constructor
    public PolynomeMember(MathFunction function, int pow, int coef) {        
        func = function;
        power = 1;
        coeff = coef;
        type = "polynome member";
    }     
    public PolynomeMember(String variableName, int pow, int coef) {        
        func = new PowerFunction(variableName, pow);
        power = 1;
        coeff = coef;
        type = "polynome member";
    }     
    @Override
    public String toString (){
        String res = "";
        if (coeff == 0)
            return res;
        if (coeff != 1){
            res += coeff;
        }
        if (power != 0){
            res += func.toString();
            if (power != 1){
                res += "^" + power;
            }
        }                        
        return res;
    }
}
