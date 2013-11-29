/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pilot;

import PersoComponent.Button;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

/**
 *
 * @author Romain
 */
public class PilotageModeImpl extends Box implements PilotageMode{
    
    private JButton buttonConsole   = new Button();
    private JButton buttonKeyboard  = new Button();
    private Pilot   pilot           ;
    
    public PilotageModeImpl(Pilot pilot){
        super(BoxLayout.X_AXIS);
        this.pilot = pilot;
        this.add(Box.createHorizontalGlue());
        this.buttonConsole.addActionListener(this);
        this.buttonKeyboard.addActionListener(this);
        this.add(buttonConsole);
        this.add(buttonKeyboard);
        this.add(Box.createHorizontalGlue());
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.err.println("passe");
        pilot.setContent(((JButton)e.getSource()).equals(buttonConsole));
    }
}
