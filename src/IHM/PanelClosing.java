/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author Romain
 */
public class PanelClosing extends JPanel{
    
    private JButton buttonClose = new JButton();
    private JButton buttonReduce = new JButton();
    private JFrame  mainFrame;
    
    public PanelClosing(final JFrame mainFrame){
        this.mainFrame = mainFrame;
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setMaximumSize(new Dimension(200, 40));
        
        this.buttonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Spartacus.client.isClosed())
                    System.exit(0);
            }
        });
        this.buttonReduce.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setExtendedState(JFrame.ICONIFIED);
            }
        });
        
        this.buttonClose.setIcon(new ImageIcon("./IconsWin8/close_button.png"));
        this.buttonReduce.setIcon(new ImageIcon("./IconsWin8/reduce_button.png"));
        
        this.buttonClose.setBorderPainted(false);
        this.buttonClose.setOpaque( false );
        this.buttonClose.setContentAreaFilled(false);
        this.buttonClose.setMargin(new Insets(0, 0, 0, 0));
        
        this.buttonReduce.setBorderPainted(false);
        this.buttonReduce.setOpaque( false );
        this.buttonReduce.setContentAreaFilled(false);
        this.buttonReduce.setMargin(new Insets(0, 0, 0, 0));

        this.add(buttonReduce);
        this.add(buttonClose);
    }
}
