/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Console;

import PersoComponent.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author Romain
 */
public class ConsoleImpl extends JTabbedPane implements Console{
    private TextAreaConsoleImpl textAreaConsole     ;
    private JScrollPane         scrollPane          ;
    private String              startLine           = "$"; 
    private File                backgroundFile      = new File("C:\\Users\\Romain\\Desktop\\vlc.png");
//    private File                backgroundFile      = new File("./IconsWin8/android2.jpg");
    private JPanel panelCenter = new JPanel();
    private Button button      = new Button();
    private Box    vBox        = Box.createVerticalBox();
    
    public ConsoleImpl(){
        super(JTabbedPane.LEFT);
        textAreaConsole = new TextAreaConsoleImpl(false, backgroundFile);
//        button.setPreferredSize(new Dimension(40, 100));
//        button.setMaximumSize(new Dimension(40, 100));
//        vBox.add(button);
//        vBox.add(Box.createVerticalGlue());
//        this.add(this.vBox);
        this.scrollPane = new JScrollPane(this.textAreaConsole);
        this.scrollPane.setPreferredSize(new Dimension(600, 400));
        this.add(this.scrollPane);
        this.textAreaConsole.requestFocusInWindow();
        this.setBackground(Color.BLACK);
        this.scrollPane.setBackground(Color.BLACK);
        this.textAreaConsole.setBackground(Color.BLACK);
        this.textAreaConsole.setForeground(Color.GREEN);
        this.addTab("<html>T<br>i<br>t<br>t<br>l<br>e<br>1</html>", this.scrollPane);
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
