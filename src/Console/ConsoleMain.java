/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Console;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author Romain
 */
public class ConsoleMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setBackground(Color.black);
        //BUG : ecrit en dessous de l'image en mode painted
        ConsoleImpl console = new ConsoleImpl(Color.BLACK, Color.GREEN, false);
        frame.add(console);
        console.requestFocusTextArea();
        frame.pack();
    }
}
