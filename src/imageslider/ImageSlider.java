/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageslider;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;

/**
 *
 * @author Romain
 */
public class ImageSlider {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 400);
        frame.setBackground(Color.black);
        Slider slider;
        slider = new SliderImpl();
        frame.add((Component) slider);
        frame.validate();
    }
}
