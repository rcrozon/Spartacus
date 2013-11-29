/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Console;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Romain
 */
public class ConsoleImpl extends JPanel implements Console{
    private TextAreaConsoleImpl textAreaConsole     ;
    private JScrollPane         scrollPane          ;
    private String              startLine           = "$"; 
    private File                backgroundFile      = new File("C:\\Users\\Romain\\Desktop\\vlc.png");
//    private File                backgroundFile      = new File("./IconsWin8/android2.jpg");
    
    public ConsoleImpl(){
        super(new BorderLayout());
        this.textAreaConsole = new TextAreaConsoleImpl(false, backgroundFile);
        this.scrollPane = new JScrollPane(this.textAreaConsole);
        this.add(this.scrollPane, BorderLayout.CENTER);
        this.textAreaConsole.requestFocusInWindow();
        
        this.setBackground(Color.BLACK);
        this.scrollPane.setBackground(Color.BLACK);
        this.textAreaConsole.setBackground(Color.BLACK);
        this.textAreaConsole.setForeground(Color.GREEN);
        
    }
    public ConsoleImpl(Color background, Color foreground){
        this();
        this.setBackground(background);
        this.scrollPane.setBackground(background);
        this.textAreaConsole.setBackground(background);
        this.textAreaConsole.setForeground(foreground);
        
    }
    
    public ConsoleImpl(Color background, Color foreground, boolean painted){
        this(background, foreground);
        //this.painted = painted;
        setTextAreaPainted(painted);
    }
    public void requestFocusTextArea(){
        this.textAreaConsole.requestFocusInWindow();
    }
    public void setTextAreaPainted(boolean painted){
        if (painted){
            textAreaConsole.setPainted(painted);
            textAreaConsole.repaint();
        }
    }
    
    

}
