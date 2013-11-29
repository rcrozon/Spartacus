/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageslider;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;

/**
 *
 * @author Romain
 */
public class SliderImpl extends JPanel implements Slider{

    private ArrayList<Reflection>   listImage       = new ArrayList<>();
    private JPanel                  panelImage      = new JPanel(new GridLayout(1, 8));
    private File                    fileEmptyImage  = new File("C:\\Users\\Romain\\Desktop\\empty.png");
    private int                     length          = 9;
    
    public SliderImpl() {
        this.setBackground(Color.black);
        this.panelImage.setBackground(Color.black);
        listImage.add(new Reflection(new File("C:\\Users\\Romain\\Desktop\\vlc.png")));
        listImage.add(new Reflection(new File("C:\\Users\\Romain\\Desktop\\itunes.png")));
        listImage.add(new Reflection(new File("C:\\Users\\Romain\\Desktop\\chrome.png")));
        listImage.add(new Reflection(new File("C:\\Users\\Romain\\Desktop\\itunes.png")));
        listImage.add(new Reflection(new File("C:\\Users\\Romain\\Desktop\\chrome.png")));
        for(int i =0; i < (length - listImage.size())/2; ++i){
            this.panelImage.add(new Reflection(fileEmptyImage));
        }
        for(Reflection image : listImage){
            this.panelImage.add(image);
            image.addMouseListener(this);
        }
        for(int i = ((length - listImage.size())/2) + listImage.size(); i < length; ++i){
            this.panelImage.add(new Reflection(fileEmptyImage));
        }
        this.add(this.panelImage);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point imagePoint = e.getPoint();
        Reflection image = (Reflection)e.getSource();
        for(Reflection img : listImage){
            img.setSelected(false);
            img.repaint();
        }
        image.setSelected(true);
        image.repaint();
        imagesPosition(listImage.indexOf(image));
        revalidate();
    }
    
    private void imagesPosition(int index){
        this.panelImage.removeAll();
        int j;
        if (index < listImage.size()/2 - 1 )
            j = listImage.size()/2;
        else
            j = listImage.size()/2 - index ;
        System.err.println(j);
        for(int i =0; i < (length - listImage.size())/2 + j; ++i){
            this.panelImage.add(new Reflection(fileEmptyImage));
        }
        for(Reflection image : listImage){
            this.panelImage.add(image);
        }
        for(int i = ((length - listImage.size())/2) + listImage.size() + j; i < length; ++i){
            this.panelImage.add(new Reflection(fileEmptyImage));
        }
        this.add(this.panelImage);
        this.panelImage.revalidate();
    }
        
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}
