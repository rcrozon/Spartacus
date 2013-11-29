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
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Romain
 */
public class CellRenderer extends DefaultTreeCellRenderer {

    public ImageIcon icon ;
    
    public CellRenderer(){
        ImageIcon iconTmp ;
        iconTmp = new ImageIcon("./IconsWin8/folder_blue.png");
        Image img = iconTmp.getImage(); 
        BufferedImage bufferedImage = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB); 
        Graphics g = bufferedImage.createGraphics(); 
        g.drawImage(img, 0, 0, 20, 20, null); 
        icon = new ImageIcon(bufferedImage); 
        this.setOpaque(false);
    }
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        if (value != null && value instanceof DefaultMutableTreeNode) {
            try{
                FileItem fileName = (FileItem)((DefaultMutableTreeNode)value).getUserObject();
                this.setIcon(fileName.getFileIcon());
            }catch(ClassCastException ex){
                this.setIcon(icon);
            }
        }else{
            this.setIcon(icon);
            if (value.toString().equals("")){    
                this.setIcon(null);
            }
        }
        Font font = this.getFont();
        if (selected){
            this.setFont(new Font("TimesRoman",font.getStyle(),16));
        }else{
            this.setFont(new Font("TimesRoman",Font.BOLD,12));
        }
        this.setPreferredSize(new Dimension(200, 30));
        this.setBackgroundNonSelectionColor(new Color(0, 0, 0, 0));
        this.setBackgroundSelectionColor(new Color(0, 0, 0, 0));
        this.setForeground(Color.WHITE);
        this.setBorderSelectionColor(null);
        return this;  
    }

}
