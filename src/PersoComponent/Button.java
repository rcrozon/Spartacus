/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PersoComponent;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import javax.swing.JButton;

/**
 *
 * @author Romain
 */
public class Button extends JButton {
    
    public Button(){
        super("Console");
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2=(Graphics2D)g;
        g2.transform(AffineTransform.getRotateInstance(-Math.PI/2.0d,getWidth()/2.0d,getHeight()/2.0d));
        super.paintComponent(g);
    }
}
