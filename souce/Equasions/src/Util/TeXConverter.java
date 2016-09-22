
package Util;

import java.awt.Insets;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Shape;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import javax.swing.JLabel;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;
import org.scilab.forge.jlatexmath.DefaultTeXFont;

import org.scilab.forge.jlatexmath.cyrillic.CyrillicRegistration;
import org.scilab.forge.jlatexmath.greek.GreekRegistration;

import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.fop.svg.AbstractFOPTranscoder;
import org.apache.fop.svg.PDFTranscoder;
import org.apache.fop.render.ps.PSTranscoder;
import org.apache.fop.render.ps.EPSTranscoder;
import org.apache.avalon.framework.configuration.DefaultConfiguration;

import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.dom.GenericDOMImplementation;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

/**
 *
 * @author venikka
 */
public class TeXConverter {
     public static final int PDF = 0;
    public static final int PS = 1;
    public static final int EPS = 2;
    public static final int PDFWIDTH = 1654;
    public static final int PDFHeight = 2339;
    public static void toSVG(String latex, String file, boolean fontAsShapes) throws IOException {
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);
        SVGGeneratorContext ctx = SVGGeneratorContext.createDefault(document);

        SVGGraphics2D g2 = new SVGGraphics2D(ctx, fontAsShapes);
        DefaultTeXFont.registerAlphabet(new CyrillicRegistration());
        DefaultTeXFont.registerAlphabet(new GreekRegistration());

        TeXFormula formula = new TeXFormula(latex);
        
        TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
        icon.setInsets(new Insets(5, 5, 5, 5));
        g2.setSVGCanvasSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        g2.setColor(Color.white);
        g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());

        JLabel jl = new JLabel();
        jl.setForeground(new Color(0, 0, 0));
        icon.paintIcon(jl, g2, 0, 0);

        boolean useCSS = true;
        FileOutputStream svgs = new FileOutputStream(file);
        Writer out = new OutputStreamWriter(svgs, "UTF-8");
        g2.stream(out, useCSS);
        svgs.flush();
        svgs.close();
    }
    
    public static void toSVGComplexFormula(ArrayList<TeXFormula> formulas, String file, boolean fontAsShapes) throws IOException {
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);
        SVGGeneratorContext ctx = SVGGeneratorContext.createDefault(document);
        
        SVGGraphics2D g2 = new SVGGraphics2D(ctx, fontAsShapes);
        DefaultTeXFont.registerAlphabet(new CyrillicRegistration());
        DefaultTeXFont.registerAlphabet(new GreekRegistration());        
        
        ArrayList<TeXIcon> icons = new ArrayList<TeXIcon>();
        int docHeight = 0;
        int docWidth = 1654;
        for (TeXFormula formula : formulas){            
            TeXIcon icon;
            if (formula.textStyle == "center"){
                icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 40, TeXConstants.UNIT_PIXEL, (float)PDFWIDTH - 40f, TeXConstants.ALIGN_CENTER);
                icon.setInsets(new Insets(20, 10, 20, 10));
            }
            else{               
                icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 40);
            }
            int leftMargin = 0;
            icon.setInsets(new Insets(40, 40, 40, 10));
            /*if (formula.textStyle == "center"){
                leftMargin = Math.abs((PDFWIDTH - icon.getIconWidth()))/2;
                icon.setIconWidth(PDFWIDTH/2, TeXConstants.ALIGN_CENTER);
            } */          
            docHeight += icon.getIconHeight();
            docWidth = Math.max(icon.getIconWidth(), PDFWIDTH);
            icons.add(icon);
        }
        
        g2.setSVGCanvasSize(new Dimension(docWidth,Math.max(docHeight, PDFHeight)));
        g2.setColor(Color.white);
        docHeight = 0;
        JLabel jl = new JLabel();
        jl.setForeground(new Color(0, 0, 0));
        for (TeXIcon icon : icons){
            g2.fillRect(0, docHeight, icon.getIconWidth(), icon.getIconHeight());
            icon.paintIcon(jl, g2, 0, docHeight);
            docHeight += icon.getIconHeight();
        }
        
        boolean useCSS = true;
        FileOutputStream svgs = new FileOutputStream(file);
        Writer out = new OutputStreamWriter(svgs, "UTF-8");
        g2.stream(out, useCSS);
        svgs.flush();
        svgs.close();
    }

    public static void SVGTo(String inSVG, String out, int type) {
        AbstractFOPTranscoder trans;
        switch (type) {
            case PDF:
                trans = new PDFTranscoder();
                break;
            case PS:
                trans = new PSTranscoder();
                break;
            case EPS:
                trans = new EPSTranscoder();
                break;
            default:
                trans = null;
        }
           
        try {
            FileInputStream fi = new FileInputStream(inSVG);
            TranscoderInput input = new TranscoderInput(fi);
            OutputStream os = new FileOutputStream(out);
            TranscoderOutput output = new TranscoderOutput(os);
            trans.transcode(input, output);
            os.flush();
            os.close();
        } catch (Exception e) {
            System.out.println("Problem when exporting " + inSVG + " to " + out + "!\n" + e.toString());
        }
    }

}

