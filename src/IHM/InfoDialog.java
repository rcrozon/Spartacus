/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.net.ConnectException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Romain
 */
public class InfoDialog extends JDialog{
    
    private JLabel              labelInfo           = new JLabel("", SwingConstants.CENTER);
    private JButton             buttonClose         = new JButton("Fermer");
    
    public InfoDialog(Exception ex){
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int y = (int)(dimension.getHeight() - 80) / 2;
        int x  = (int)(dimension.getWidth() - 500) / 2;
        this.labelInfo.setForeground(Color.white);
        this.labelInfo.setText("Envoie impossible. Le serveur ne semble pas connecté.");
        this.setLayout(new BorderLayout());
        this.add(this.labelInfo, BorderLayout.CENTER);
        this.add(this.buttonClose, BorderLayout.SOUTH);
        
        this.setUndecorated(true);
        this.setLocation(x, y);
        this.setVisible(true);
        this.setSize(400, 50);
        this.setBackground(new Color(0, 0, 0, 100));
        this.setAlwaysOnTop(true);
    }
}
