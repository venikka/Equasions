/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algebra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author venikka
 */
public class BracketTreeNode extends Polynome  {
    ArrayList<BracketTreeNode> children;
    BracketTreeNode parent;        
    public BracketTreeNode (BracketTreeNode par, Random random){
        parent = par;
        children = new ArrayList<BracketTreeNode>();                
    }
     public String toString (){
        String result = "";
        ArrayList<PolynomeMember> memberTokens = new ArrayList<>();
        memberTokens.addAll(members);
        memberTokens.addAll(children);
        long seed = System.nanoTime();
        Collections.shuffle(memberTokens, new Random(seed));
        for (PolynomeMember token : memberTokens){
            if (token.getClass().equals(this.getClass())){
                BracketTreeNode bracket = (BracketTreeNode)token;
                if ("".equals(result)){
                    result += " " + Util.StringBiulding.FirstCoeffSignForBracket(bracket.coeff) + " (" + bracket.toString() + ")";
                }else{
                    result += " " + Util.StringBiulding.CoeffSignForBracket(bracket.coeff) + " (" + bracket.toString() + ")";
                }
            }
            else{
                PolynomeMember coefflessToken = new PolynomeMember(token.func, token.power, 1);                
                if ("".equals(result)){
                    result += " " + Util.StringBiulding.FirstCoeffSignWithVar(token.coeff, coefflessToken.toString());
                }else{
                    result += " " + Util.StringBiulding.CoeffSignWithVar(token.coeff, coefflessToken.toString());
                }
            }
        }        
        return result;
    }
     public int evaluate (int value){
        int overallValue = 0;
        for (PolynomeMember member : members){
            overallValue += member.coeff * Math.pow(value, member.power);
        }
        for (BracketTreeNode bracket : children){            
            overallValue += bracket.coeff * Math.pow(bracket.evaluate(value), bracket.power);
            //overallValue += bracket.evaluate(value);
        }
        return overallValue;
    }
}
/*if (depth != 0 && numberOfBranches > 0){
            children = new ArrayList<>();
            int numberOfChildren = Util.Rndm.showRandomInteger(1, numberOfBranches - depth + 1, random, false);
            int numberOfLeaves = Util.Rndm.showRandomInteger(0, numberOfBranches - numberOfChildren - depth + 1, random, true);
            int numberOfBranchesLeft = numberOfBranches - numberOfLeaves - numberOfChildren;
            int nextBracketChildren = 0;
            if (numberOfBranchesLeft > 0){
                    nextBracketChildren = Util.Rndm.showRandomInteger(depth - 1, numberOfBranchesLeft, random, false);
            }
            numberOfBranchesLeft -= nextBracketChildren + depth - 1;
            BracketTreeNode deepNode = new BracketTreeNode(this, nextBracketChildren, depth - 1, 0, random);
            children.add(deepNode);
            for (int i = 0; i < numberOfChildren - 1; i ++){                         
                nextBracketChildren = Util.Rndm.showRandomInteger(1, numberOfBranchesLeft, random, true);
                BracketTreeNode node = new BracketTreeNode(this, nextBracketChildren, depth - 1, 0, random);
                children.add(node);
                numberOfBranchesLeft -= nextBracketChildren;
            }
            for (int i = 0; i < numberOfLeaves; i++){
                BracketTreeNode leaf = new BracketTreeNode(this, 0, 0, 0, random);
                children.add(leaf);
            }
        }*/