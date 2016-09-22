/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algebra;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 *
 * @author venikka
 */
public class Polynome extends PolynomeMember{    
    ArrayList<PolynomeMember> members;
    
    public Polynome(){        
        super("",1,1);
        //PolynomeMember blankMember = new PolynomeMember(new PowerFunction("x"), 0, 0);
        members = new ArrayList<PolynomeMember>();
        //addMember(blankMember);
        type = "polynome";
    }
    public Polynome(String variable, int power, int coeff){
       super(variable,1,1);
       //PolynomeMember firstMember = new PolynomeMember(new PowerFunction(variable), power, coeff);
       members = new ArrayList<PolynomeMember>();
       //addMember(firstMember);        
    }
    /*public Polynome (String variable, int power, int coeff, int varValue, int depth, int numberOfBrackets, int supposedNumberOfMembers , Random random){
        super(variable, 1, 1);
        boolean success = false;
        //if (numberOfBrackets < supposedNumberOfMembers + 1){
        if (depth != 0 && numberOfBrackets > 0){
            while (!success){
                
            }
        }
    }*/
    
    
    @Override
    public String toString(){
        String result = "";
        for (PolynomeMember i:members){ 
             if (i.power != 0){
                if ("".equals(result)){
                    result += Util.StringBiulding.FirstCoeffSignWithVar(i.coeff, i.func.toString());                    
                }else{
                    result += Util.StringBiulding.CoeffSignWithVar(i.coeff, i.func.toString());
                }
                if (i.power != 1){
                    result += "^" + i.power;
                }
            }else{
                if ("".equals(result)){
                    result += Util.StringBiulding.FirstCoeffSign(i.coeff);
                }else{
                    result += Util.StringBiulding.CoeffSign(i.coeff);
                }
            }
        }
        return result;
    }
    /*
   
    */
  
    /*public String toString(){
        String result = "";
        for (PolynomeMember i:members){          
            if (i.coeff != 0){
                if (result != ""){
                    if (i.coeff > 0){
                            result += " + ";
                    }else{
                            result += " - ";
                    }
                }
                if (Math.abs(i.coeff) != 1){                    
                    result += Math.abs(i.coeff);
                }else if (i.power == 0){
                    result += Math.abs(i.coeff);
                }                        
                if (i.power != 0){
                    if (i.getClass().equals(Polynome.class)){
                        result += "(" + i.toString() + ")";
                    }else{
                        result += i.func;  
                    }
                    if (i.power != 1){
                        if (i.power > 0){
                            result += "^" + i.power;           
                        }else{
                            result += "^(" + i.power + ")";
                        }
                    }
                }                    
            }         
        }
        return result;
    }*/
    public String membersToString (){
        return toString();
    }
    public PolynomeMember multiplyTwoMembers (PolynomeMember firstMember, PolynomeMember secondMember){
        PolynomeMember result;
        if (firstMember.func == secondMember.func){
            result = new PolynomeMember(firstMember.func, firstMember.power + secondMember.power, firstMember.coeff * secondMember.coeff);
        }else{
            //need to rethink the model of polynome member or the whole polynome concept            
            ArrayList<MathFunction> functions = new ArrayList<MathFunction>();
            functions.add(firstMember.func);
            functions.add(secondMember.func);
            MathFunction function = FunctionFactory.multiplyFunctions(functions);
            result = new PolynomeMember(function, 1, firstMember.coeff * secondMember.coeff);
        }
        return result;
    }
    public void addMember(PolynomeMember newMember){
        members.add(newMember);
    } 
    public void addMember(MathFunction function, int power, int coeff){
        PolynomeMember newMember = new PolynomeMember(function, power, coeff);
        members.add(newMember);
    } 
    public static Polynome Multiply(Polynome firstPolynome, Polynome secondPolynome){
        Polynome result = new Polynome();
        for (PolynomeMember firstFactor : firstPolynome.members){
            //firstFactor.func.power = firstFactor.power;
            for (PolynomeMember secondFactor : secondPolynome.members){
                //secondFactor.func.power = secondFactor.power;
                PolynomeMember composition;
                ArrayList<MathFunction> vars = new ArrayList<MathFunction>();
                vars.add(firstFactor.func);
                vars.add(secondFactor.func);
                //FunctionMultiplication twoVarsCombination = new FunctionMultiplication(vars);
                composition = new PolynomeMember(FunctionFactory.multiplyFunctions(vars), 1, firstFactor.coeff * secondFactor.coeff);              
                if (composition.coeff != 0){
                    result.addMember(composition);
                }
            }
        }
        //result = GroupLikeTerms(result);
        return result;
    }
    public static Polynome GroupLikeTerms(Polynome initialPolynome){
        Polynome groupedPolynome = new Polynome();
        for (PolynomeMember ungroupedMember : initialPolynome.members){
            boolean funcMatch = false;
            for (PolynomeMember groupedMember : groupedPolynome.members){
                if (ungroupedMember.func.Equals(groupedMember.func)){
                    funcMatch = true;
                    groupedMember.coeff += ungroupedMember.coeff;
                    break;
                }
            }
            if (!funcMatch && ungroupedMember.coeff != 0){
                groupedPolynome.addMember(ungroupedMember);
            }
        }
        return groupedPolynome;
    }
    public static Polynome SortMembersByPower (Polynome initialPolynome){
        Polynome sortedPolynome = initialPolynome;
        Collections.sort(sortedPolynome.members, new Comparator<PolynomeMember>(){
            @Override 
            public int compare(PolynomeMember p1, PolynomeMember p2) {
            return p2.func.power - p1.func.power; // Ascending
        }
        });
        return sortedPolynome;
    }
    public static Polynome Canonize (Polynome initialPolynime){
        return Polynome.SortMembersByPower(Polynome.GroupLikeTerms(initialPolynime));        
    }    
    
    //TODO implement "equals" method
    //@Override
    //public boolean Equals(MathFunction)
}
