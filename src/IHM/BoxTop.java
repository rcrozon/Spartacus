/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 *
 * @author Romain
 */
public class BoxTop extends Box implements ActionListener{
    
    private PanelClosing        panelClosing        ;
    private JButton             buttonPlay          = new JButton(new ImageIcon("./IconsWin8/play.png"));
    private JButton             buttonNext          = new JButton(new ImageIcon("./IconsWin8/next.png"));
    private JButton             buttonPrev          = new JButton(new ImageIcon("./IconsWin8/prev.png"));
    private JButton             buttonList          = new JButton(new ImageIcon("./IconsWin8/list1.png"));
    private boolean             selected            = false;
    private PopUpMenuList       popupMenuList       ;
    
    public BoxTop(JFrame mainFrame){
        super(BoxLayout.X_AXIS);
        this.panelClosing = new PanelClosing(mainFrame);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
        GraphicsDevice gd =ge.getDefaultScreenDevice(); 
        GraphicsConfiguration gc =gd.getDefaultConfiguration(); 
        Rectangle bounds = gc.getBounds(); 
        this.setPreferredSize(new Dimension(bounds.width, 40));
        this.buttonList.addActionListener(this);
        this.buttonNext.addActionListener(this);
        this.buttonPrev.addActionListener(this);
        this.buttonPlay.addActionListener(this);
        this.buttonList.setBorderPainted(false);
        this.buttonPlay.setBorderPainted(false);
        this.buttonNext.setBorderPainted(false);
        this.buttonPrev.setBorderPainted(false);
        this.buttonList.setBackground(Color.BLACK);
        this.buttonPlay.setBackground(Color.BLACK);
        this.buttonNext.setBackground(Color.BLACK);
        this.buttonPrev.setBackground(Color.BLACK);
        this.buttonList.setBorder(null);
        this.buttonPlay.setBorder(null);
        this.buttonNext.setBorder(null);
        this.buttonPrev.setBorder(null);
        this.add(this.buttonList);
        this.add(this.buttonPrev);
        this.add(this.buttonPlay);
        this.add(this.buttonNext);
        this.add(Box.createHorizontalGlue());
        this.add(this.panelClosing);
        this.add(Box.createHorizontalStrut(5));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton)e.getSource();
        if (!PanelBackground.listOfFileItems.isEmpty()){
            if (src == buttonPlay){
                try {
                    Spartacus.client.sendFile(PanelBackground.listOfFileItems.get(0).getFile());
                    for(int i = 0 ; i < PanelBackground.listOfFileItems.size(); ++i)
                        PanelBackground.listOfFileItems.get(i).setPlaying(false);                    
                    PanelBackground.listOfFileItems.get(0).setPlaying(true);
                 } catch (Exception ex) {
                    Logger.getLogger(BoxTop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if (src == buttonNext){
                try {
                    int index = 0;
                    for(int i = 0 ; i < PanelBackground.listOfFileItems.size(); ++i){
                        if (PanelBackground.listOfFileItems.get(i).isPlaying())
                            if (i + 1 < PanelBackground.listOfFileItems.size())
                                index = i + 1;
                            else
                                index = 0;
                    }
                    for(int i = 0 ; i < PanelBackground.listOfFileItems.size(); ++i)
                        PanelBackground.listOfFileItems.get(i).setPlaying(false);                    
                    PanelBackground.listOfFileItems.get(index).setPlaying(true);   
                    Spartacus.client.sendFile(PanelBackground.listOfFileItems.get(index).getFile());
                } catch (Exception ex) {
                    Logger.getLogger(BoxTop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if (src == buttonPrev){
                try {
                    int index = -1;
                    try{
                        while (!PanelBackground.listOfFileItems.get(index+1).isPlaying())
                            index++;
                    }catch (IndexOutOfBoundsException ex) {} 
                    if (index == -1)
                        index = 0;
                    for(int i = 0 ; i < PanelBackground.listOfFileItems.size(); ++i)
                            PanelBackground.listOfFileItems.get(i).setPlaying(false);                    
                    PanelBackground.listOfFileItems.get(index).setPlaying(true);
                    Spartacus.client.sendFile(PanelBackground.listOfFileItems.get(index).getFile());
                }
                catch (Exception ex) {
                    Logger.getLogger(BoxTop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(src == buttonList){
                if (!PanelBackground.listOfFileItems.isEmpty()){
                    popupMenuList = new PopUpMenuList(this);
                    popupMenuList.addPopupMenuListener(new PopupMenuListener() {
                        @Override
                        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                            selected = true;
                        }
                        @Override
                        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                            selected = false;
                        }
                        @Override
                        public void popupMenuCanceled(PopupMenuEvent e) {}
                    });
                    popupMenuList.show(src, 0, src.getY()+24);
                }
            }
        }
    }
    public void setSelected(boolean selected){
        this.selected = selected;
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (selected && !PanelBackground.listOfFileItems.isEmpty()){
            int[] xpoints = {buttonList.getX() + 5 , buttonList.getX() + 15 ,  buttonList.getX() + 25};
            int[] ypoints = {buttonList.getY() + 33, buttonList.getY() +23, buttonList.getY() + 33};
            Polygon polygon = new Polygon(xpoints, ypoints, 3);
            g.setColor(new Color(99, 130, 191));
            g.drawPolygon(polygon);
            g.setColor(this.getBackground());
            g.fillPolygon(polygon);
        }
    }
}