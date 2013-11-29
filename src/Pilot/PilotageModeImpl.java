/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pilot;

import javax.swing.Box;
import javax.swing.BoxLayout;

/**
 *
 * @author Romain
 */
public class PilotageModeImpl extends Box implements PilotageMode{
    
    private Pilot   pilot           ;
    
    public PilotageModeImpl(Pilot pilot){
        super(BoxLayout.X_AXIS);
        this.pilot = pilot;
        this.add(Box.createHorizontalGlue());
        this.add(Box.createHorizontalGlue());
        
    }

}
