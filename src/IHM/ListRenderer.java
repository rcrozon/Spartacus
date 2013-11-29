/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Explorator.FileItem;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.plaf.ScrollBarUI;

/**
 *
 * @author Romain
 */
public class ListRenderer extends DefaultListCellRenderer {
    
    private boolean isListHistory = true ;
    public ListRenderer(boolean isListHistory){
        this.isListHistory = isListHistory;
    }
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        PanelItemList item = (PanelItemList)value;
        Font font = this.getFont();
        if (isSelected){
            this.setFont(new Font("TimesRoman",font.getStyle(),16));
        }else{
            this.setFont(new Font("TimesRoman",font.getStyle(),12));
        }
        this.setText(item.getFileItem().getFileName());
        if (isListHistory){
            this.setBackground(new Color(0,0,0,0));
            this.setPreferredSize(new Dimension(300, 40));
            this.setForeground(Color.white);
            this.setIcon(item.getFileItem().getFileIcon());
        }else{
            this.setBackground(new Color(0,0,0,0));
            this.setPreferredSize(new Dimension(300, 30));
            this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
            this.setForeground(Color.BLACK);
        }
        return this;
    }
    
}
