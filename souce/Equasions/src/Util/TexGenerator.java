/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author venikka
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import org.scilab.forge.jlatexmath.TeXConstants; 
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;
public class TexGenerator {
    public static void GeneratePng(String equasion){    
        String latex = "$" + equasion + "$";
        TeXFormula formula = new TeXFormula(latex);
        
        TeXIcon icon = formula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(20).build();
        icon.setInsets(new Insets(5, 5, 5, 5));
	
	BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
	Graphics2D g2 = image.createGraphics();
	g2.setColor(Color.white);
	g2.fillRect(0,0,icon.getIconWidth(),icon.getIconHeight());
	JLabel jl = new JLabel();
	jl.setForeground(new Color(0, 0, 0));
	icon.paintIcon(jl, g2, 0, 0);
	File file = new File( "Example1.png");
	try {
	    ImageIO.write(image, "png", file.getAbsoluteFile());
	} catch (IOException ex) { }
	       
    }
}
