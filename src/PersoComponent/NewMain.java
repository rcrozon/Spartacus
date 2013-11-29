/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PersoComponent;

import Console.ConsoleImpl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Romain
 */
public class NewMain extends JPanel{

    /**
     * @param args the command line arguments
     */
    public NewMain(){
        super(new BorderLayout());
        this.setSize(600, 300);
        Box box = Box.createVerticalBox();
        JPanel p = new JPanel();
        Button b = new Button();
        int x = 60;
        int y = 50;
        b.setMinimumSize(new Dimension(x, y));
        b.setSize(new Dimension(x, y));
        b.setPreferredSize(new Dimension(x, y));
        b.setMaximumSize(new Dimension(x, y));
        box.add(b);
        box.add(Box.createVerticalGlue());
        p.setBorder(BorderFactory.createLineBorder(Color.yellow));
        b.setBorder(BorderFactory.createLineBorder(Color.red));
        this.add(box, BorderLayout.WEST);
        this.add(p, BorderLayout.CENTER);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setBackground(Color.black);
        frame.add(new NewMain());
        //BUG : ecrit en dessous de l'image en mode painted
        frame.pack();
    }
}
