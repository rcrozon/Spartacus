/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageslider;

import java.awt.Component;
import java.awt.FlowLayout;
import java.io.IOException;
import javax.swing.JPanel;

/**
 *
 * @author Romain
 */
public class Pilotage extends JPanel{
    
    private Slider slider ;
    
    public Pilotage() throws IOException{
        super(new FlowLayout());
        slider = new SliderImpl();
        this.add((Component) this.slider);
    }
}
