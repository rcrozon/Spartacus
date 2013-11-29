/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;

/**
 *
 * @author Romain
 */
public class BoxListHistory extends Box{
    
    private ListHistory listHistory     = new ListHistory();
    private Box         boxHList        = Box.createHorizontalBox();
    
    public BoxListHistory(){
        super(BoxLayout.Y_AXIS);
        this.listHistory.setVisible(false);
        this.setPreferredSize(new Dimension(300, 700));
        this.boxHList.setMinimumSize(new Dimension(300, 700));
        this.listHistory.setMinimumSize(new Dimension(0, 530));
        this.listHistory.setMaximumSize(new Dimension(235, 1000));
        this.boxHList.setMaximumSize(new Dimension(300, 1000));
        this.setMaximumSize(new Dimension(235, 1000));
        for(int i = 0; i < 240; ++i)
            this.boxHList.add(Box.createHorizontalStrut(1));
        this.boxHList.add(listHistory);
        this.boxHList.add(Box.createHorizontalStrut(55));
        this.add(boxHList);
    }
    
    public void animate(){
        this.listHistory.fillList();
        if (!this.listHistory.isVisible()){
            this.listHistory.setVisible(true);
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    listHistory.setSize(new Dimension(0, 700));
                    boxHList.removeAll();
                    boxHList.add(Box.createHorizontalStrut(240));
                    boxHList.add(listHistory);
                    boxHList.add(Box.createHorizontalStrut(55));
                    while (listHistory.getWidth() < 235){
                        boxHList.add(Box.createHorizontalStrut(-1), 0);
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(BoxListHistory.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        repaint();
                        revalidate();
                    }
                }
            });
            t.start();
        }
    }
    
    public void setUnvisible(){
        this.listHistory.setVisible(false);
        this.boxHList.removeAll();
        for(int i = 0; i < 240; ++i)
            this.boxHList.add(Box.createHorizontalStrut(1));
        this.boxHList.add(listHistory);
        this.boxHList.add(Box.createHorizontalStrut(55));
    }
}
