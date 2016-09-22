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
public class MathFunction {
    public int power;
    public String type;

    public MathFunction() {
        super();
        type = "MathFuncion";
    }
    
    @Override
    public String toString(){
        return "";
    }
    public boolean Equals(MathFunction functionToCompare){        
        if (this.type == functionToCompare.type)
            return true;
        else return false;
    }
    //this is a bullshit method body. It must be overridden int all of the children classes. Otherwise things might go wrong.
    public boolean EqualBases (MathFunction functionToCompare){
        if (this.getClass() == functionToCompare.getClass()){                        
            return true;
        }
        return false;
    }
}
