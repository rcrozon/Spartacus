/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Explorator.Explorator;
import Explorator.FileItem;
import XML.XMLWriter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Romain
 */
public class ExploratorTree extends JScrollPane implements MouseListener {
    
    private DefaultMutableTreeNode  dmtn                = new DefaultMutableTreeNode("");
    private DefaultMutableTreeNode  dmtnComputer        = new DefaultMutableTreeNode("Ordinateur");
    private DefaultMutableTreeNode  dmtnResearch        ;
    private DefaultMutableTreeNode  dmtnFavorites       = new DefaultMutableTreeNode("Favoris");
    private DefaultTreeModel        dtm                 = new DefaultTreeModel(dmtn);
    private JTree                   tree                = new JTree(dtm);
    private PopUpMenuTree           menu                = new PopUpMenuTree();
    private File[]                  roots               = File.listRoots();
    private FileItem[]              rootsFileItem       = new FileItem[roots.length];
    private PanelCenter             panelCenter         ;
    private final int               TREE_WIDTH          = 293;
    private final int               TREE_HEIGHT         = 650;
    
    public ExploratorTree(final PanelCenter panelCenter){
        this.panelCenter = panelCenter;
        this.setViewportView(tree);
        this.tree.addMouseListener(this);
        this.treeInitialization();
        this.dmtn.add(dmtnFavorites);
        this.dmtn.add(dmtnComputer);
        this.setOpaque(false);
        this.tree.setOpaque(false);
        this.setBorder(BorderFactory.createLineBorder(new Color(158, 209, 236), 2));
        this.getViewport().setOpaque(false);
        for (int i = 0; i < roots.length; ++i){
            rootsFileItem[i] = new FileItem(roots[i]);
            fillNode(rootsFileItem[i]);
        }
        this.tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (node != null){
                    if (!node.equals(dmtnComputer) && !node.equals(dmtnResearch) && !node.equals(dmtnFavorites) && !node.equals(dmtn)){
                        node.removeAllChildren();
                    }
                }fillNode(node);
            }
        });
        this.tree.addTreeWillExpandListener(new ExpandListener());
        this.tree.setCellRenderer(new CellRenderer());
        MouseListener ml = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selRow = tree.getRowForLocation(e.getX(), e.getY());
                if(selRow != -1) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    if (e.getClickCount() == 1){
                        try{
                            FileItem item = (FileItem)node.getUserObject();
                            if (item.isAnImage())
                                panelCenter.animeImagePreview(item.getFile());
                        }catch(ClassCastException ex){}   
                    }else if(e.getClickCount() == 2) {
                        try{
                            FileItem item = (FileItem)node.getUserObject();
                            if (!item.getFile().isDirectory()){
                                Spartacus.client.setPanelCenter(panelCenter);
                                Spartacus.client.sendFile(item.getFile());
                                XMLWriter writer = new XMLWriter();
                                writer.addToHistory(item);
                            }
                        }catch(ClassCastException ex){} catch (Exception ex) {
                            Logger.getLogger(ExploratorTree.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        };
        this.tree.addMouseListener(ml);
        this.tree.expandRow(0);
    }
    
    private void treeInitialization(){
        this.dmtnFavorites.removeAllChildren();
        XMLWriter writer = new XMLWriter();
        ArrayList<FileItem> favoritesFileItems = writer.getFavorites();
        for(int i = 0; i < favoritesFileItems.size(); ++i){
            if (favoritesFileItems.get(i).getFile().exists()){
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(favoritesFileItems.get(i));
                node.add(new DefaultMutableTreeNode(new FileItem((File)null)));
                this.dmtnFavorites.add(node);
            }
        }    
    }
    private void fillNode(DefaultMutableTreeNode nodeParent){
        try{
            FileItem item = (FileItem)nodeParent.getUserObject();
            FileItem[] items = Explorator.getFileItemList(item.getFilePath());
            try{
                for(int i = 0; i < items.length ; ++i){
                    if (items[i].isDirectory()){
                        DefaultMutableTreeNode emptyNode = new DefaultMutableTreeNode(new FileItem((File)null));
                        DefaultMutableTreeNode node = new DefaultMutableTreeNode(items[i]);
                        node.add(emptyNode);
                        nodeParent.add(node);
                    }else{
                        nodeParent.add(new DefaultMutableTreeNode(items[i]));
                    }
                }
            }catch(NullPointerException ex){}
        }catch(  ClassCastException | NullPointerException ex){}
    }  
    private void fillNode(FileItem item){
        if (item.getFile().isDirectory()){
            item.setFileName(FileSystemView.getFileSystemView().getSystemDisplayName(item.getFile()));
            DefaultMutableTreeNode emptyNode = new DefaultMutableTreeNode(new FileItem((File)null));
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(item);
            node.add(emptyNode);
            dmtnComputer.add(node);
        }
    }
    public JTree getTree(){
        return this.tree;
    }
    
    public void addNode(DefaultMutableTreeNode newNode){
        this.dmtnResearch = newNode;
        this.dtm.insertNodeInto(this.dmtnResearch, this.dmtn, 0);
    }
    
    public void animeTopToBottom() {
        this.tree.expandRow(1);
        this.setSize(new Dimension(TREE_WIDTH, 0));
        this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (getHeight() < TREE_HEIGHT){
                    setSize(new Dimension(getWidth(), getHeight() + 1));
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ExploratorTree.class.getName()).log(Level.SEVERE, null, ex);
                    }  
                    revalidate();
                }  
                setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            }
        });
        this.setVisible(true);
        t.start();
    }

    public void setUnvisible() {
        this.setVisible(false);
        this.menu.setVisible(false);
        this.panelCenter.getImagePreview().setVisible(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton()== MouseEvent.BUTTON3) {
            TreePath curPath = null;
            if (tree.getRowForLocation(e.getX(), e.getY()) != -1){
                curPath = tree.getPathForLocation(e.getX(), e.getY());
                try{
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)curPath.getLastPathComponent();
                    FileItem item = (FileItem)node.getUserObject();
                    menu.setFileItem(item);
                    menu.setTree(dtm, node, dmtnFavorites);
                    menu.show(tree, e.getX(), e.getY());
                    menu.setVisible(true);
                }catch(ClassCastException ex){}    
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
}
