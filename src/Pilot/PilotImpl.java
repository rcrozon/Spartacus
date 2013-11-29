/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pilot;

import Console.Console;
import Console.ConsoleImpl;
import IHM.AnimatingCardLayout;
import WindowPilot.Keyboard;
import WindowPilot.KeyboardImpl;
import effects.SlideAnimation;
import imageslider.Slider;
import imageslider.SliderImpl;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Romain
 */
public class PilotImpl extends JPanel implements Pilot{
    
    private SliderImpl      imageSlider     = new SliderImpl();
    private CardLayout      cardlayout      = new AnimatingCardLayout(new SlideAnimation());
    private JPanel          panelCenter     = new JPanel(cardlayout);
    private PilotageMode    pilotageBox     ;
    private Console         console         = new ConsoleImpl();
    private Keyboard        keyboard        = new KeyboardImpl();
    private ButtonPerso     buttonConsole   = new ButtonPerso("Console");
    
    public PilotImpl(){
        super(new BorderLayout());
        this.pilotageBox = new PilotageModeImpl(this);
        this.setBackground(Color.BLACK);
        this.add((Component) imageSlider, BorderLayout.NORTH);
        this.add(buttonConsole, BorderLayout.SOUTH);
        panelCenter.setVisible(false);
        this.buttonConsole.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                panelCenter.setVisible(!panelCenter.isVisible());
            }
        });
        this.add(panelCenter, BorderLayout.CENTER);
        this.panelCenter.add((Component) this.console, "console");
        this.panelCenter.add((Component) this.keyboard, "keyboard");
        
        this.buttonConsole.setBorder(null);
        this.buttonConsole.setBorderPainted(false);
        this.buttonConsole.setContentAreaFilled(false);
        this.buttonConsole.setFocusPainted(false);
    }

    @Override
    public void setContent(boolean console) {
        if (console)
            this.cardlayout.show(this.panelCenter, "console");
        else
            this.cardlayout.show(this.panelCenter, "keyboard");
    }
}
