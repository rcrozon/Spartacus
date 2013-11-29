/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WindowPilot;

import javax.swing.JFrame;

/**
 *
 * @author Romain
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.add(new KeyboardImpl());
        frame.setVisible(true);
    }
}
