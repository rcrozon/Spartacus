/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Explorator.FileItem;
import XML.XMLWriter;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Romain
 */
public class PopUpMenuTree extends JPopupMenu implements ActionListener{
    
    private String[]                iconPaths             = {"./IconsWin8/fleche.png", "./IconsWin8/plus.png","./IconsWin8/moins.png","./IconsWin8/loupe.png","./IconsWin8/addList2.png"};
    private ImageIcon[]             icons                 = new ImageIcon[5];
    private JMenuItem               itemLaunch            ;
    private JMenuItem               itemAddToFavorites    ;
    private JMenuItem               itemRemoveToFavorites ;
    private JMenuItem               itemProperties        ;
    private JMenuItem               itemAddIntoList       ;
    private FileItem                item                  ;
    private XMLWriter               xmlWriter             = new XMLWriter();
    private DefaultMutableTreeNode  node                  ;
    private DefaultTreeModel        model                 ;
    private DefaultMutableTreeNode  nodeParent            ;
    
    public PopUpMenuTree() {
        ImageIcon iconTmp ;
        for (int i = 0; i < iconPaths.length; ++i){
            iconTmp = new ImageIcon(iconPaths[i]);
            Image img = iconTmp.getImage(); 
            BufferedImage bufferedImage = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB); 
            Graphics g = bufferedImage.createGraphics(); 
            g.drawImage(img, 0, 0, 20, 20, null); 
            icons[i] = new ImageIcon(bufferedImage);
        }
        itemLaunch            = new JMenuItem("Lancer"                      , icons[0]);
        itemAddToFavorites    = new JMenuItem("Ajouter au favoris"          , icons[1]);
        itemRemoveToFavorites = new JMenuItem("Supprimer des favoris"       , icons[2]);
        itemProperties        = new JMenuItem("Propriétés"                  , icons[3]);
        itemAddIntoList       = new JMenuItem("Ajouter à la liste lecture"  , icons[4]);
        
        this.add(itemLaunch);
        this.add(itemAddIntoList);
        this.add(itemAddToFavorites);
        this.add(itemRemoveToFavorites);
//        this.add(itemProperties);
        
        this.itemLaunch.addActionListener(this);
        this.itemAddToFavorites.addActionListener(this);
        this.itemRemoveToFavorites.addActionListener(this);
        this.itemProperties.addActionListener(this);
        this.itemAddIntoList.addActionListener(this);
    }

    public void setFileItem(FileItem item){
        this.item = item;
        if (xmlWriter.isInFavorites(item))
            this.itemRemoveToFavorites.setEnabled(true);
        else
            this.itemRemoveToFavorites.setEnabled(false);
        if (item.getFile().isDirectory()){
            this.itemLaunch.setEnabled(false);
            this.itemAddIntoList.setEnabled(false);
        }else{
            this.itemLaunch.setEnabled(true);
            this.itemAddIntoList.setEnabled(true);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem = (JMenuItem)e.getSource();
        if (menuItem == itemLaunch){    
            try {
                Spartacus.client.sendFile(item.getFile());
                xmlWriter.addToHistory(item);
            } catch (Exception ex) {
                Logger.getLogger(PopUpMenuTree.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (menuItem == itemAddToFavorites){
            if (!xmlWriter.isInFavorites(item) && this.node != null ){
                xmlWriter.addToFavorites(item);
                model.insertNodeInto(node, nodeParent, nodeParent.getChildCount()); 
            }
        }else if (menuItem == itemRemoveToFavorites){
            if (xmlWriter.isInFavorites(item) && this.node != null ){
                xmlWriter.removeFromFavorites(item);    
                model.removeNodeFromParent(node);
            }
        }else if (menuItem == itemAddIntoList){
            PanelBackground.listOfFileItems.add(item);
        }
    }

    public void setTree(DefaultTreeModel model, DefaultMutableTreeNode node, DefaultMutableTreeNode nodeParent) {
        this.model = model;
        this.nodeParent = nodeParent;
        this.node = node;
    }
}
