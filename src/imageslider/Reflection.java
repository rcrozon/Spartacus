/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageslider;

/**
 *
 * @author Romain
 */
import java.awt.*;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
 
 
public class Reflection extends JComponent {
 
    private BufferedImage image;
    private boolean       selected = false;
    private Color         gradientColor ;
    
    public Reflection(File file) {
        try {
            image = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    @Override
    public void paintComponent(Graphics g) {
        if (this.selected){
            gradientColor = Color.gray;
        }else{
            gradientColor = Color.black;
        }
        Graphics2D g2d = (Graphics2D)g;
        int width = getWidth();
        int height = getHeight();
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        int gap = 20;
        float opacity = 0.4f;
        float fadeHeight = 0.3f;
        Color[] colors = {gradientColor, Color.black};
        float[] dist = {0.0f, 0.9f};
        g2d.setPaint(new RadialGradientPaint(65, 75, 80, 65, 75,dist, colors, CycleMethod.NO_CYCLE));
        g2d.fillRect(0, 0, width, height);
        g2d.translate((width - imageWidth) / 2, height / 2 - imageHeight);
        g2d.drawRenderedImage(image, null);
        g2d.translate(0, 1.8 * imageHeight + gap);
        g2d.scale(1, -1);
 
        BufferedImage reflection = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D rg = reflection.createGraphics();
        rg.drawRenderedImage(image, null);
        rg.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
        rg.setPaint(new GradientPaint(0, imageHeight * fadeHeight,
                                      new Color(0.0f, 0.0f, 0.0f, 0.0f), 0,
                                      imageHeight,
                                      new Color(0.0f, 0.0f, 0.0f, opacity)));
 
        rg.fillRect(0, 0, imageWidth, imageHeight);
        rg.dispose();
        g2d.drawRenderedImage(reflection, null);
    }
 
    public Dimension getPreferredSize() {
        return new Dimension(130, 250);
    }
    
    public void setSelected(boolean selected){
        this.selected = selected;
    }
}
