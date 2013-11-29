/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

/**
 *
 * @author Romain
 */
public class ExpandListener implements TreeWillExpandListener {
    
    @Override
    public void treeWillExpand(TreeExpansionEvent evt) throws ExpandVetoException {
        JTree tree = (JTree) evt.getSource();
        TreePath path = evt.getPath();
        tree.setSelectionPath(path);
    }

    @Override
    public void treeWillCollapse(TreeExpansionEvent evt) throws ExpandVetoException {}
}
