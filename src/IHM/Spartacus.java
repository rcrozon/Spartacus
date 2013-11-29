/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Connection.Client;
import effects.*;
import imageslider.Slider;
import imageslider.SliderImpl;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Romain
 */
public class Spartacus extends JFrame{
    
    private PanelBackground panelBackground = new PanelBackground(this);
    private Image           img             ;
    public  static Client   client          ;
    private LoadFrame       loadFrame       ;
    private Slider          slider          = new SliderImpl();
    private JButton         buttonChange    = new JButton();
    private CardLayout      cardLayout      = new AnimatingCardLayout(new CubeAnimation());
    private JPanel          mainPanel       = new JPanel(cardLayout);
    
    public Spartacus() throws InterruptedException {
        this.setLayout(new BorderLayout());
        BoxTop boxTop = new BoxTop(this);
        this.add(boxTop, BorderLayout.NORTH);
        this.add(this.mainPanel, BorderLayout.CENTER);
        this.add(this.buttonChange, BorderLayout.EAST);
        this.mainPanel.add(this.panelBackground, "SendPanel");
        this.mainPanel.add((Component) this.slider, "pilotePanel");
        this.buttonChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(mainPanel);
            }
        });
        Thread threadLoader = new Thread(new Runnable() {
            @Override
            public void run() {
                loadFrame = new LoadFrame();
            } 
        });
        threadLoader.start();
     
        try {
            client = new Client("192.168.0.13", 8000);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Spartacus.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        try{
            File input = new File("./IconsWin8/piss-on-apple.jpg");
            img = ImageIO.read(input);
        }
        catch(IOException ex){
            Logger.getLogger(Spartacus.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setIconImage(img);
        this.loadFrame.dispose();
//    
//        final FileTransferProgressBar bar = new FileTransferProgressBar(new File("Coucou"));
//        Thread tBar = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                int i = 0;
//                bar.setClientFileLength(200);
//                while(i <= 200){
//                    try {
//                        Thread.sleep(500);
//                        i = 50;
//                        bar.updateClient(i);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(Spartacus.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }
//        });
//        tBar.start();
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Spartacus spartacus = new Spartacus();
        } catch (InterruptedException ex) {
            //server.closeSocket();
            Logger.getLogger(Spartacus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
