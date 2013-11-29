/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Romain
 */
public class ImagePreview extends JPanel {
    
    private Image       img                 = null  ;
    private int         imgWidth            = 300   ;
    private int         imgHeight           = 300   ;
    private int         PANEL_WIDTH         = 700   ;
    private int         PANEL_HEIGHT        = 500   ;
    
    public ImagePreview(){
        this.setVisible(false);
        this.setMaximumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setMinimumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        //this.setBackground(new Color(0, 0, 0, 100));
        
    }
    public void setFile(File imgFile){
        try{
            File input = new File(imgFile.getAbsolutePath());
            img = ImageIO.read(input);
        }
        catch(IOException ie){
            Logger.getLogger(PanelBackground.class.getName()).log(Level.SEVERE, null, ie);
        }
        PANEL_HEIGHT = this.img.getHeight(this);
    }
    
    @Override
    public void paintComponent(Graphics g){
        if (img != null){
            double ratio = 600 / (double)this.img.getWidth(this);
            if (this.img.getWidth(this) > this.getWidth() || this.img.getHeight(this) > this.getHeight()){
                imgWidth  = (int)Math.ceil(this.img.getWidth(this) * ratio);
                imgHeight = (int)Math.ceil(this.img.getHeight(this) * ratio);
            }
            g.drawImage(img, 0, 0, imgWidth, imgHeight, this);
        }
    }
    
    public void setImagePreviewVisible(){
        this.setVisible(true);
    }
}
