/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Explorator.FileItem;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Romain
 */
public class PanelItemList extends JPanel {
    
    private FileItem fileItem ;
    
    public PanelItemList(FileItem fileItem){
        this.fileItem = fileItem;
        this.setLayout(new FlowLayout());
        this.add(new JLabel(fileItem.getFileIcon()));
        this.add(new JLabel(fileItem.getFileName()));
    }
            
    public FileItem getFileItem(){
        return fileItem;
    }
}
