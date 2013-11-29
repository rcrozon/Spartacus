/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import XML.XMLWriter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Romain
 */
public class ListHistory extends JScrollPane {
    
    private DefaultListModel    model       = new DefaultListModel();
    private ListRenderer        renderer    = new ListRenderer(true);
    private XMLWriter           writer      = new XMLWriter();
    private JList<PanelItemList> listHistory = new JList<>();
    private Font                font        = new Font("TimesRoman", this.listHistory.getFont().getStyle(), 16);
    
    public ListHistory() {
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setBorder(null);
        this.setViewportView(listHistory);
        this.getViewport().setOpaque(false);
        this.listHistory.setModel(model);
        this.setOpaque(false);
        this.setVisible(false);
        this.listHistory.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(158, 209, 236), 2), 
                                    "Historique", TitledBorder.LEFT, TitledBorder.TOP, font, Color.WHITE));
        this.listHistory.setOpaque(false);
        this.listHistory.setVisible(true);
        this.listHistory.setCellRenderer(renderer);
        this.listHistory.setPreferredSize(new Dimension(240, 530));
        this.setSize(new Dimension(0, 550));
        
        fillList();
        
        listHistory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2){
                    PanelItemList item = (PanelItemList)listHistory.getModel().getElementAt(listHistory.locationToIndex(evt.getPoint()));
                    try {
                        Spartacus.client.sendFile(item.getFileItem().getFile());
                    } catch (Exception ex) {
                        Logger.getLogger(ListHistory.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    
    public void fillList(){
        this.model.removeAllElements();
        ArrayList<PanelItemList> items = writer.getHistory();
        for (int i = 0; i < items.size(); ++i){
            if(items.get(i).getFileItem().getFile().exists())
                this.model.addElement(items.get(i));
        }
        this.listHistory.revalidate();
    }
   
    public void setUnvisible(){
        this.setSize(new Dimension(0, 0));
        this.setVisible(false);
        this.listHistory.clearSelection();
    }
}
