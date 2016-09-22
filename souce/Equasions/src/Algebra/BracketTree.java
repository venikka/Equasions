/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algebra;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author venikka
 */
public class BracketTree{
    public ArrayList<BracketTreeNode> children;
    public BracketTreeNode root;
    ArrayList<BracketTreeNode> allBrackets;
    public BracketTree(MathFunction function, int numberOfBranches, int depth, int summaryWeight, Random random, int variableValue){        super ();
        GenerateTree(numberOfBranches, depth, random);
        
        DistributeWeight(function, summaryWeight, random, EvaluateCoeffRange(variableValue));              
    }
    int EvaluateCoeffRange (int value){
        int factorsRange;
        if (value == 0 || Math.abs(value) == 1){
            factorsRange = 10;            
        }else{
            if (Math.abs(value) <= 4){
                factorsRange = 7;
            }else
                factorsRange = value;
        }       
        return factorsRange;
    }
    void GenerateTree (int numberOfBranches, int depth, Random random){
        root = new BracketTreeNode(null, random);
        children = new ArrayList<BracketTreeNode>();
        allBrackets = new ArrayList<BracketTreeNode>();
        allBrackets.add(root);
        int[] treeLevels = new int[depth];
        
        int freeBranchesLeft = numberOfBranches - depth;        
        for (int i = 0; i < depth; i ++){
            treeLevels[i] = Util.Rndm.showRandomInteger(0, freeBranchesLeft, random, true) + 1;
            freeBranchesLeft -= treeLevels[i] - 1;
        }
        if (freeBranchesLeft != 0)
            treeLevels[treeLevels.length - 1] += freeBranchesLeft;
        ArrayList<BracketTreeNode> parentalLevelNodes = new ArrayList<BracketTreeNode>();        
        for (int i = 0; i < depth; i ++){
            ArrayList<BracketTreeNode> currentLevelNodes = new ArrayList<BracketTreeNode>();
            for (int j = 0; j < treeLevels[i]; j++){                
                BracketTreeNode node;                
                if (parentalLevelNodes.size() == 0){
                    node = new BracketTreeNode(root, random);
                    node.coeff = Util.Rndm.showRandomInteger(-2, 2, random, false);
                    currentLevelNodes.add(node);
                    children.add(node);
                    allBrackets.add(node);
                }else{
                        int parentIndex = Util.Rndm.showRandomInteger(0, parentalLevelNodes.size() - 1, random, true);
                    node = new BracketTreeNode(parentalLevelNodes.get(parentIndex), random);
                    parentalLevelNodes.get(parentIndex).children.add(node);
                    currentLevelNodes.add(node);
                    allBrackets.add(node);
                }               
            }
            parentalLevelNodes = currentLevelNodes;
        }
        root.children = children;
    }
    
    void DistributeWeight (MathFunction function, int summaryWeight, Random random, int coeffRange){
        int minSummaryWeight = 0;
        for (BracketTreeNode bracket : allBrackets){
            int minWeight = 0;
            if (bracket.children.size() == 0){
                minWeight = 2;
            }else{
                minWeight = 1;
            }
            minSummaryWeight += minWeight;
        }
        int weightToDitribute = summaryWeight - minSummaryWeight;
        for (BracketTreeNode bracket : allBrackets){
            int minWeight;
            if (bracket.children.size() == 0){
                minWeight = 2;
            }else{
                minWeight = 1;
            }
            int currentWeight = minWeight;
            if (weightToDitribute > 0){
                int currentDistribution = Util.Rndm.showRandomInteger(0, weightToDitribute, random, true);
                currentWeight += currentDistribution;
                weightToDitribute -= currentDistribution;
            }            
            if (allBrackets.indexOf(bracket) == allBrackets.size() - 1){
                currentWeight += Math.max(weightToDitribute, 0); 
                weightToDitribute -= Math.max(weightToDitribute, 0);
            }                   
            for (int i = 0; i < currentWeight; i++){
                int coeff = Util.Rndm.showRandomInteger(-coeffRange, coeffRange, random, false);
                int power = Util.Rndm.showRandomInteger(0, 1, random, true);
                MathFunction coreFunction = new MathFunction();
                if (power != 0)
                    coreFunction = function;
                PolynomeMember member = new PolynomeMember(coreFunction, power, coeff);
                bracket.members.add(member);
            }            
        }                  
    }  
    @Override
    public String toString (){
        return root.toString();
    }
    public void BalanceExpression(int value){
        root.members.add(new PolynomeMember("x", 0, -root.evaluate(value)));
    }
    public static ArrayList<BracketTreeNode> CreateEquasion (BracketTree treeToEqualise, int value){
        Random rnd = new Random();
        ArrayList<BracketTreeNode> equasionParts = new ArrayList<BracketTreeNode>();
        equasionParts.add(new BracketTreeNode(null, rnd));
        equasionParts.add(new BracketTreeNode(null, rnd));
        BracketTree tree = treeToEqualise;
        tree.BalanceExpression(value);
        //distributing root members to different equasion sides
        for (PolynomeMember member : tree.root.members){
            int side = Util.Rndm.showRandomInteger(0, 1, rnd, true);
            equasionParts.get(side).members.add(member);
        }        
        //switching signs of the rirgt side members
        for (PolynomeMember member : equasionParts.get(1).members){
            member.coeff = - member.coeff;
        }
        //distributing brackets to different equasion sides
        for (BracketTreeNode bracket : tree.root.children){
            int side = Util.Rndm.showRandomInteger(0, 1, rnd, true);
            equasionParts.get(side).children.add(bracket);
        }
        //switching signs of the rirgt side brackets
        for (BracketTreeNode bracket : equasionParts.get(1).children){            
            bracket.coeff = - bracket.coeff;
        }
        return equasionParts;
    }
    public static String EquasionToString(ArrayList<BracketTreeNode> sides){
        String result = "";
        if ("".equals(sides.get(0).toString())){
            result += "0";
        }else{
            result += sides.get(0).toString();
        }
        result += " = ";
        if ("".equals(sides.get(1).toString())){
            result += "0";
        }else{
            result += sides.get(1).toString();
        }
        return result;
    }
   /* public Polynome getPolynomeFromFlatTree(){
        Polynome result = new Polynome();
        for (BracketTreeNode node : children){
            result.members.add(node);
        }        
        return result;
    }*/
 
}
