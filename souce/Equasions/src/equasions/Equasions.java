/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equasions;

import javax.swing.JFrame;

/**
 *
 * @author venikka
 */
public class Equasions {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StartApp();
    }
    public static void StartApp(){
        JFrame guiScreen = new EqGui();
        guiScreen.setVisible(true);
    }
    
}
