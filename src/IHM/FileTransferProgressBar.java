/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

/**
 *
 * @author Romain
 */
public class FileTransferProgressBar extends JPanel{
    
    private JProgressBar    progressBar         = new JProgressBar(SwingConstants.HORIZONTAL);
    private JLabel          labelTransfer       = new JLabel();
    private Color           translucent         = new Color(0, 0, 0, 0);
    private int             PROGRESSBAR_WIDTH   = 300;
    private int             PROGRESSBAR_HEIGHT  = 43;
    private Box             boxProgressBarH     = Box.createHorizontalBox();
    private Box             boxlabelH           = Box.createHorizontalBox();
    private Box             boxV                = Box.createVerticalBox();
    
    public FileTransferProgressBar(){
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int y = (int)(dimension.getHeight() - 300) / 2;
        int x  = (int)(dimension.getWidth() - 300) / 2;
        
        this.labelTransfer.setForeground(Color.white);
        this.progressBar.setBorderPainted(false);
        this.progressBar.setForeground(new Color(158, 209, 236));
        this.progressBar.setBackground(translucent);
        this.progressBar.setPreferredSize(new Dimension(PROGRESSBAR_WIDTH, PROGRESSBAR_HEIGHT));
        
        this.boxlabelH.add(Box.createHorizontalStrut(14));
        this.boxlabelH.add(labelTransfer);
        this.boxlabelH.add(Box.createHorizontalGlue());
        this.boxProgressBarH.add(Box.createHorizontalStrut(8));
        this.boxProgressBarH.add(progressBar);
        this.boxProgressBarH.add(Box.createHorizontalStrut(55));
        this.boxV.add(boxlabelH);
        this.boxV.add(boxProgressBarH);
        
        this.progressBar.setMinimumSize(new Dimension(292, 30));
        this.progressBar.setMaximumSize(new Dimension(292, 30));
        this.setOpaque(false);
        this.add(this.boxV);
    }
    
    public void setProgressBarFile(File file) {
        this.labelTransfer.setText("Transfert du fichier : " + (this.progressBar.getValue()/file.length()) * 100);
        this.progressBar.setMaximum((int)file.length());
    }
    
    public void updateClient(long i) {
        this.progressBar.setValue((int)i);
        if (i >= this.progressBar.getMaximum()){
            this.progressBar.setValue(0);
            this.labelTransfer.setText("Transfert du fichier : terminé");
        }
    }

    
}
