/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WindowPilot;

import java.awt.Font;
import javax.swing.JButton;

/**
 *
 * @author Romain
 */
public class KeyboardButtonImpl extends JButton implements KeyboardButton {

    private int     VK      ;
    private String  letter  ;
    
    public KeyboardButtonImpl(String letter){
        Font font = new Font("font", Font.BOLD, 14);
        this.setFont(font);
        this.setText(letter);
    } 
    
    public KeyboardButtonImpl(int VK, String letter){
        this.VK = VK;
        this.letter = letter;
        Font font = new Font("font", Font.BOLD, 14);
        this.setFont(font);
        this.setText(letter);
    }

    public int getVK(){
        return this.VK;
    }
}
