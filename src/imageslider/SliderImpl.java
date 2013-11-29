/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageslider;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicArrowButton;

/**
 *
 * @author Romain
 */
public class SliderImpl extends JPanel implements Slider{

    private ArrayList<Reflection>   listImage       = new ArrayList<>();
    private JPanel                  panelImage      = new JPanel(new GridLayout(1, 8));
    private File                    fileEmptyImage  = new File("C:\\Users\\Romain\\Desktop\\empty.png");
    private int                     length          = 9;
    private int                     index           = 0;
    
    public SliderImpl() {
        this.setBackground(Color.black);
        this.addKeyListener(this);
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
        Reflection image = (Reflection)e.getSource();
        for(Reflection img : listImage){
            img.setSelected(false);
            img.repaint();
        }
        image.setSelected(true);
        image.repaint();
        index = listImage.indexOf(image);
        imagesPosition();
        revalidate();
    }
    
    private void imagesPosition(){
        this.panelImage.removeAll();
        int j;
        if (index < listImage.size()/2 - 1 )
            j = listImage.size()/2;
        else
            j = listImage.size()/2 - index ;
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

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        System.err.println("e.getKeyCode()" + e.getKeyCode());
        if ((e.getKeyCode() == KeyEvent.VK_TAB) /*&& ((e.getModifiers() & KeyEvent.ALT_MASK) != 0)*/) {
            if (index != listImage.size() - 1)
                index++;
            else
                index = 0;
            imagesPosition();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

}
