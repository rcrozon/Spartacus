/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

/**
 *
 * @author Romain
 */
public class PopUpMenuList extends JPopupMenu implements ActionListener, MouseListener{
    
    public PopUpMenuList(BoxTop boxTop) {
        this.setVisible(true);
        Box boxTitle = Box.createHorizontalBox();
        ImageIcon iconTmp = new ImageIcon("./IconsWin8/list.png");
        boxTitle.add(new JLabel("Liste de lecture", iconTmp, SwingConstants.CENTER));
        boxTitle.add(Box.createHorizontalGlue());
        ////////////////////////////
        ImageIcon iconTmp2 = new ImageIcon("./IconsWin8/separateur.png");
        Image img = iconTmp2.getImage(); 
        BufferedImage bufferedImage = new BufferedImage(200, 5, BufferedImage.TYPE_INT_ARGB); 
        Graphics g = bufferedImage.createGraphics(); 
        g.drawImage(img, 0, 0, 200, 5, null); 
        ImageIcon iconList = new ImageIcon(bufferedImage);
        ////////////////////////////
        ImageIcon iconPlay = new ImageIcon("./IconsWin8/play.png");
        img = iconPlay.getImage(); 
        bufferedImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB); 
        g = bufferedImage.createGraphics(); 
        g.drawImage(img, 0, 0, 16, 16, null); 
        ImageIcon iconPlay2 = new ImageIcon(bufferedImage);
        ////////////////////////////
        ImageIcon iconEmpty = new ImageIcon("./IconsWin8/empty.png");
        img = iconEmpty.getImage(); 
        bufferedImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB); 
        g = bufferedImage.createGraphics(); 
        g.drawImage(img, 0, 0, 16, 16, null); 
        ImageIcon iconEmpty2 = new ImageIcon(bufferedImage);
        ////////////////////////////
        this.add(boxTitle);
        this.add(new JLabel(iconList));
        this.addMouseListener(this);
        for (int i = 0; i < PanelBackground.listOfFileItems.size(); ++i){
            JMenuItem item ;
            if (PanelBackground.listOfFileItems.get(i).isPlaying()){
                item = new JMenuItem(PanelBackground.listOfFileItems.get(i).getFileName(), iconPlay2); 
            }else{
                item = new JMenuItem(PanelBackground.listOfFileItems.get(i).getFileName(), iconEmpty2);
            }
            item.addActionListener(this);
            this.add(item);
            this.add(new JLabel(iconList));
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem = (JMenuItem)e.getSource();
        boolean contain = false;
        int index = 0 ;
        for(int i = 0; i < PanelBackground.listOfFileItems.size(); ++i){
            if (menuItem.getText().equals(PanelBackground.listOfFileItems.get(i).getFileName())){
                contain = true;
                index = i;
            }
        }
        if (contain){
            try {
                for(int i = 0; i < PanelBackground.listOfFileItems.size(); ++i)
                    PanelBackground.listOfFileItems.get(i).setPlaying(false);
                PanelBackground.listOfFileItems.get(index).setPlaying(true);
                Spartacus.client.sendFile(PanelBackground.listOfFileItems.get(index).getFile());
            } catch (Exception ex) {
                Logger.getLogger(PopUpMenuList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void update(){
        this.removeAll();
        for( int i = 0; i < PanelBackground.listOfFileItems.size(); ++i)
            if (PanelBackground.listOfFileItems.get(i).isPlaying())
                this.add(new JMenuItem(PanelBackground.listOfFileItems.get(i).getFileName()), 
                                       PanelBackground.listOfFileItems.get(i).getFileIcon());
            else
                this.add(new JMenuItem(PanelBackground.listOfFileItems.get(i).getFileName()));
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
