/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pilot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint;
import java.awt.RadialGradientPaint;
import javax.swing.JButton;

/**
 *
 * @author Romain
 */
public class ButtonPerso extends JButton {
    
    public ButtonPerso(String text){
        super(text);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Color[] colors = {Color.BLUE, Color.black};
        float[] dist = {0.0f, 0.9f};
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(new RadialGradientPaint(500, 10, 500, 10, 120,dist, colors, MultipleGradientPaint.CycleMethod.NO_CYCLE));
        
        
    }
}
