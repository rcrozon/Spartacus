/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Romain
 */
public class LoadFrame extends JPanel{
    
    private ImageIcon loadIcon = new ImageIcon("./IconsWin8/350.gif");
    private JDialog   dialog    = new JDialog();
    
    public LoadFrame(){}
    
    public void launchFrame(){
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
        GraphicsDevice gd =ge.getDefaultScreenDevice(); 
        GraphicsConfiguration gc =gd.getDefaultConfiguration(); 
        Rectangle bounds = gc.getBounds();
        
        this.dialog.setUndecorated(true);
        this.dialog.setSize(200, 200);
        this.dialog.setLocation(bounds.width / 2 - 50, bounds.height / 2 - 50);
        this.dialog.setBackground(new Color(0, 0, 0, 0));
        this.dialog.add(this);
        this.dialog.setVisible(true);
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        g.drawImage((loadIcon).getImage(), 0, 0, 100, 100, this);
    }
    public void dispose(){
        this.dialog.dispose();
    }
}
