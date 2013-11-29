/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Console;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JTextArea;

/**
 *
 * @author Romain
 */
public class TextAreaConsoleImpl extends JTextArea implements TextAreaConsole{
    
    private boolean painted = false ;
    private File    backgroundImage ;
    
    public TextAreaConsoleImpl(boolean painted){
        this.painted = painted;
    }
    public TextAreaConsoleImpl(boolean painted, File backgroundImage){
        this(painted);
        this.backgroundImage = backgroundImage;
    }
    public void setPainted(boolean painted){
        this.painted = painted;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (painted){
            try {
                Image img = ImageIO.read(backgroundImage);
                g.drawImage(img, 0, 0, 600, 400, null);
            } catch (IOException ex) {
                Logger.getLogger(TextAreaConsoleImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_ENTER){
//            textAreaConsole.setText(textAreaConsole.getText() + startLine);
//        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
}
