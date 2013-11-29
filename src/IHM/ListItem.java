/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Explorator.FileItem;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Romain
 */
public class ListItem extends JPanel{
    
    private JLabel      labelName ;
    private FileItem    file ;
    private ImageIcon   icon ;
    
    public ListItem(FileItem file){
        this.file = file;
        this.setSize(new Dimension(172, 30));
        this.icon = file.getFileIcon();
        this.labelName = new JLabel(this.file.getFileName(), icon, SwingConstants.LEFT);
        this.labelName.setForeground(Color.WHITE);
        this.labelName.setPreferredSize(new Dimension(172, 30));
        this.add(this.labelName);
    }
    
    public FileItem getFileItem(){
        return file;
    }
    
}
