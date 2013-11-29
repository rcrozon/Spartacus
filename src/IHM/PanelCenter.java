/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import imageslider.SliderImpl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;

/**
 *
 * @author Romain
 */
public class PanelCenter extends JPanel {

    private final int           TREE_WIDTH          = 290;
    private final int           TREE_HEIGHT         = 1000;
    private ExploratorTree      tree                ;
    private Box                 boxCenter           = Box.createHorizontalBox();
    private Box                 boxTreeH            = Box.createHorizontalBox();
    private Box                 boxTree             = Box.createVerticalBox();
    private ImagePreview        imagePreview        = new ImagePreview();
    private Box                 boxImage            = Box.createVerticalBox();
    private FileTransferProgressBar progressBar     = new FileTransferProgressBar();
    
    public PanelCenter(){
        this.setLayout(new BorderLayout());
        //this.boxTop = boxTop;
        this.setOpaque(false);
        this.tree = new ExploratorTree(this);
        this.tree.setVisible(false);
        ///////////////////////////////////////////////////////
        boxTreeH.setPreferredSize(new Dimension(TREE_WIDTH+50, TREE_HEIGHT));
        boxTree.setPreferredSize(new Dimension(TREE_WIDTH+50, TREE_HEIGHT));
        ///////////////////////////////////////////////////////
                        
        boxTreeH.add(tree);
        boxTreeH.add(Box.createHorizontalGlue());
        boxTree.add(Box.createVerticalStrut(4));
        boxTree.add(progressBar);
        boxTree.add(Box.createVerticalStrut(9));
        boxTree.add(boxTreeH);
        boxTree.add(Box.createVerticalStrut(10));
        
        boxCenter.add(Box.createHorizontalStrut(5));
        boxCenter.add(boxTree);
        
    }

    public Box getBoxTree(){
        return boxTree;
    }
    public void animeTopToBottom() {
        this.tree.animeTopToBottom();
    }
    public void animeImagePreview(File file){
        imagePreview.setFile(file);
        this.boxImage.add(Box.createHorizontalStrut(400));
        this.boxImage.add(this.imagePreview);
        this.boxCenter.add(boxImage);
        this.imagePreview.setVisible(true);
    }
    
    public void setUnvisible() {
        this.tree.setUnvisible();
    }
    
    public void setListUnvisible() {
        this.tree.setUnvisible();
    }

    public ImagePreview getImagePreview() {
        return imagePreview;
    }

    public void setProgressBarFile(File file){
        this.progressBar.setProgressBarFile(file);
    }
    
    public void updateClient(long i) {
        progressBar.updateClient(i);
    }


}
