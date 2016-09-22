package equasions;

import Algebra.Polynome;
import Util.Rndm;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;



public class Equasion {
    public String eqText;
    public int answer;
    private int factorsRange;
    public Equasion (int answerLowerBound, int answerUpperBound,int numberOfBrackets, int bracketsDepth, int numberOfMembers){
        Random random = new Random();
        answer = Rndm.showRandomInteger(answerLowerBound, answerUpperBound, random,true);
        
        if (answer == 0 || Math.abs(answer) == 1){
            factorsRange = 10;            
        }else{
            if (Math.abs(answer) <= 4){
                factorsRange = 7;
            }else
                factorsRange = 4;
        }                
        
        LinearExpression exp = new LinearExpression(answer, bracketsDepth + 1, numberOfBrackets, numberOfMembers - 1, random);
        int difference = exp.sum;        
        //if (difference != 0){
            exp.members.add(difference);
            exp.sum -= difference;
            exp.numberOfMembers ++;
            if (difference < 0){
                exp.expressionTokens.add(" - " + Math.abs(difference));            
            }else{
                exp.expressionTokens.add(" + " + difference);            
            }
        //}
        exp.shuffle();
        int divisionIndex = Rndm.showRandomInteger(1, exp.expressionTokens.size() - 1, random, true);       
        exp.divide(divisionIndex);
        eqText = exp.expressionText;
    }
    
    
    
    
    public String stringifyExpressionArrayList (ArrayList<String> list){
            String result = "";
            for (String s : list){
                if (result == ""){
                    String s1;
                    if (s.contains("+"))
                        s1 = s.substring(3);
                    else
                        s1 = s.substring(1);
                    result += s1;
                }else                    
                    result += s;
            }
            return result;
    }
    
    public class LinearExpression{
        public int sum;
        public int numberOfMembers;
        public String expressionText;
        public ArrayList<Integer> members;  
        public Polynome polynome;
        ArrayList<String> expressionTokens;   
        int minNumberOfMembers;
        public LinearExpression (int x, int depth, int numberOfBrackets, int membersCount , Random random){
            members = new ArrayList<Integer>();            
            expressionTokens = new ArrayList<>();
            numberOfMembers = 0;
            
                    //showRandomInteger(numberOfBrackets + 2, numberOfBrackets + 5, random, false); 
            if (depth != 0 && numberOfBrackets > 0){
                minNumberOfMembers = 1 + numberOfBrackets*(depth + 1);
                int numberOfExtraMembers = membersCount - minNumberOfMembers;
                for (int i = 0;  i < numberOfBrackets; i++){                    
                    int numberOfNestedMembers = 2;
                    int numberOfNestedBrackets = 0;
                    LinearExpression bracket;                    
                    
                    if (depth == 1){
                        if (numberOfBrackets > 2){
                            numberOfNestedBrackets = Rndm.showRandomInteger(0, numberOfBrackets - 2, random, true);                        
                        }
                    }else{
                        if (numberOfBrackets > 2)
                            numberOfNestedBrackets = Rndm.showRandomInteger(1, numberOfBrackets - 2, random, true);
                        else{
                            numberOfNestedBrackets = 1;
                        }
                    }
                    
                    if (numberOfExtraMembers == 0){
                        //numberOfNestedMembers = 1 + 
                         bracket = new LinearExpression(x, depth-1, numberOfNestedBrackets, numberOfNestedMembers, random);
                    }else{
                         numberOfNestedMembers = Rndm.showRandomInteger(1, numberOfExtraMembers + 2, random, true);                                                                           
                         bracket = new LinearExpression(x, depth-1, numberOfNestedBrackets, numberOfNestedMembers, random);
                    }
                    int bracketFactor = Rndm.showRandomInteger(-factorsRange, factorsRange, random, false);
                    sum += bracket.sum * bracketFactor;
                    members.add(bracket.sum * bracketFactor);
                    numberOfExtraMembers -= bracket.numberOfMembers - bracket.minNumberOfMembers;
                    numberOfMembers += bracket.numberOfMembers;
                    if (Math.abs(bracketFactor) == 1){
                        if (bracketFactor < 0 ){                                                
                            expressionTokens.add(" - (" + bracket.expressionText + ")");
                        }else{                                            
                            expressionTokens.add(" + (" + bracket.expressionText + ")");
                        }
                    }else{
                        if (bracketFactor < 0 ){                                                
                            expressionTokens.add(" - " + Math.abs(bracketFactor) + "(" + bracket.expressionText + ")");
                        }else{                                            
                            expressionTokens.add(" + " + bracketFactor + "(" + bracket.expressionText + ")");
                        }
                    }
                }
            }else{
                minNumberOfMembers = 2;
            }
            int numberOfXMultiples = Rndm.showRandomInteger(1, membersCount - numberOfMembers, random, false);
            for (int i = 0; i < numberOfXMultiples; i++){
                int xFactor = Rndm.showRandomInteger(-factorsRange, factorsRange, random, false);
                int member = x*xFactor;
                sum += member;
                members.add(member); 
                if (xFactor > 0){
                    if (xFactor == 1)
                        expressionTokens.add(" + x");
                    else
                        expressionTokens.add(" + " + xFactor + "x");                        
                }
                if (xFactor < 0){
                    if (xFactor == -1)
                        expressionTokens.add(" - x");
                    else
                        expressionTokens.add(" - " + Math.abs(xFactor) + "x");
                }            
                numberOfMembers ++;
            }
            for (int i = 0; i < membersCount - numberOfMembers; i++){
                int member = Rndm.showRandomInteger(-factorsRange, factorsRange, random, false);
                sum += member;
                members.add(member); 
                if (member > 0){
                    expressionTokens.add(" + " + member);
                }
                if (member < 0){
                    expressionTokens.add(" - " + Math.abs(member));
                }
                numberOfMembers ++;
            }            
            shuffle();
        }
        public void shuffle(){
            int seed = Rndm.showRandomInteger(-1000, 1000, new Random(),true);
            Collections.shuffle(expressionTokens, new Random(seed));   
            Collections.shuffle(members, new Random(seed));   
            expressionText = stringifyExpressionArrayList(expressionTokens);
        }
        public void divide (int divisionIndex){
            String result = "";
            for (int i = 0; i < divisionIndex; i++){
                String s = this.expressionTokens.get(i);
                if (result == ""){
                    String s1;
                    if (s.contains("+"))
                        s1 = s.substring(3);
                    else
                        s1 = s.substring(1);
                    result += s1;
                }else                    
                    result += s;
            }        
            String result1 = "";
            for (int i = divisionIndex; i < expressionTokens.size(); i++){
                String s = expressionTokens.get(i);
                if (result1 == ""){
                    String s1;
                    if (s.contains("+"))
                        s1 = s.substring(3);
                    else
                        s1 = s.substring(1);
                    result1 += s1;
                }else                    
                    result1 += s;
            }                    
            expressionText = result + " = " + result1;
        }
    }
}
