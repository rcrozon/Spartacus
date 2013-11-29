/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WindowPilot;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ButtonModel;
import javax.swing.JPanel;

/**
 *
 * @author Romain
 */
public class KeyboardImpl extends JPanel implements Keyboard {
    
    Box                                 boxLine1    = Box.createHorizontalBox();
    Box                                 boxLine2    = Box.createHorizontalBox();
    Box                                 boxLine3    = Box.createHorizontalBox();
    Box                                 boxLine4    = Box.createHorizontalBox();
    Box                                 boxLine5    = Box.createHorizontalBox();
    Box                                 boxLine6    = Box.createHorizontalBox();
    HashMap<Integer, KeyboardButtonImpl> buttonMap  = new HashMap<>();
    ArrayList<KeyboardButtonImpl>       buttonList  = new ArrayList<>();
    
    public KeyboardImpl(){
        super(new FlowLayout());
        initButtonsList();
//        createLine2();
//        createLine3();
//        createLine4();
//        createLine5();
//        createLine6();
        this.add(this.boxLine1);
        this.add(this.boxLine2);
//        this.add(this.boxLine2);
//        this.add(this.boxLine3);
//        this.add(this.boxLine4);
//        this.add(this.boxLine5);
//        this.add(this.boxLine6);
        
    }
    private void initButtonsList(){
        KeyboardButtonImpl buttonEscape  = new KeyboardButtonImpl(KeyEvent.VK_ESCAPE, "Echap");
        KeyboardButtonImpl buttonF1      = new KeyboardButtonImpl(KeyEvent.VK_F1, "F1");
        KeyboardButtonImpl buttonF2      = new KeyboardButtonImpl(KeyEvent.VK_F2, "F2");
        KeyboardButtonImpl buttonF3      = new KeyboardButtonImpl(KeyEvent.VK_F3, "F3");
        KeyboardButtonImpl buttonF4      = new KeyboardButtonImpl(KeyEvent.VK_F4, "F4");
        KeyboardButtonImpl buttonF5      = new KeyboardButtonImpl(KeyEvent.VK_F5, "F5");
        KeyboardButtonImpl buttonF6      = new KeyboardButtonImpl(KeyEvent.VK_F6, "F6");
        KeyboardButtonImpl buttonF7      = new KeyboardButtonImpl(KeyEvent.VK_F7, "F7");
        KeyboardButtonImpl buttonF8      = new KeyboardButtonImpl(KeyEvent.VK_F8, "F8");
        KeyboardButtonImpl buttonF9      = new KeyboardButtonImpl(KeyEvent.VK_F9, "F9");
        KeyboardButtonImpl buttonF10     = new KeyboardButtonImpl(KeyEvent.VK_F10, "F10");
        KeyboardButtonImpl buttonF11     = new KeyboardButtonImpl(KeyEvent.VK_F11, "F11");
        KeyboardButtonImpl buttonF12     = new KeyboardButtonImpl(KeyEvent.VK_F12, "F12");
        KeyboardButtonImpl buttonPause   = new KeyboardButtonImpl(KeyEvent.VK_PAUSE, "Pause");
        KeyboardButtonImpl buttonPrScr   = new KeyboardButtonImpl(KeyEvent.VK_PRINTSCREEN, "ImpEcr");
        KeyboardButtonImpl buttonDel     = new KeyboardButtonImpl(KeyEvent.VK_DELETE, "Suppr");
        KeyboardButtonImpl buttonUpp     = new KeyboardButtonImpl(KeyEvent.VK_PREVIOUS_CANDIDATE, "↑↑");
        KeyboardButtonImpl buttonUp      = new KeyboardButtonImpl(KeyEvent.VK_PAGE_UP, "↑");
        KeyboardButtonImpl buttonDown    = new KeyboardButtonImpl(KeyEvent.VK_PAGE_DOWN, "↓");
        KeyboardButtonImpl buttonEnd     = new KeyboardButtonImpl(KeyEvent.VK_END, "Fin");
        KeyboardButtonImpl buttonSquare  = new KeyboardButtonImpl(0, "²");
        KeyboardButtonImpl button1       = new KeyboardButtonImpl(KeyEvent.VK_1, "&");
        KeyboardButtonImpl button2       = new KeyboardButtonImpl(KeyEvent.VK_2, "é");
        KeyboardButtonImpl button3       = new KeyboardButtonImpl(KeyEvent.VK_3, "\"");
        KeyboardButtonImpl button4     = new KeyboardButtonImpl(KeyEvent.VK_4, "'");
        KeyboardButtonImpl button5     = new KeyboardButtonImpl(KeyEvent.VK_5, "(");
        KeyboardButtonImpl button6     = new KeyboardButtonImpl(KeyEvent.VK_6, "-");
        KeyboardButtonImpl button7     = new KeyboardButtonImpl(KeyEvent.VK_7, "è");
        KeyboardButtonImpl button8     = new KeyboardButtonImpl(KeyEvent.VK_8, "_");
        KeyboardButtonImpl button9     = new KeyboardButtonImpl(KeyEvent.VK_9, "ç");
        KeyboardButtonImpl button0     = new KeyboardButtonImpl(KeyEvent.VK_0, "à");
        KeyboardButtonImpl buttonRightPar     = new KeyboardButtonImpl(KeyEvent.VK_RIGHT_PARENTHESIS, ")");
        KeyboardButtonImpl buttonEqual     = new KeyboardButtonImpl(KeyEvent.VK_EQUALS, "=");
        KeyboardButtonImpl buttonBack     = new KeyboardButtonImpl(KeyEvent.VK_BACK_SPACE, "←");
        KeyboardButtonImpl buttonNum     = new KeyboardButtonImpl(KeyEvent.VK_NUM_LOCK, "num");
        KeyboardButtonImpl buttonSlash     = new KeyboardButtonImpl(KeyEvent.VK_SLASH, "/");
        KeyboardButtonImpl buttonFois     = new KeyboardButtonImpl(0, "*");
        KeyboardButtonImpl buttonMoins     = new KeyboardButtonImpl(0, "-");
        
        buttonMap.put(KeyEvent.VK_ESCAPE, buttonEscape);
        buttonMap.put(KeyEvent.VK_DELETE, buttonDel);
        buttonMap.put(KeyEvent.VK_PAGE_DOWN, buttonDown);
        buttonMap.put(KeyEvent.VK_END, buttonEnd);
        buttonMap.put(KeyEvent.VK_F1, buttonF1);
        buttonMap.put(KeyEvent.VK_F10, buttonF10);
        buttonMap.put(KeyEvent.VK_F11, buttonF11);
        buttonMap.put(KeyEvent.VK_F12, buttonF12);
        buttonMap.put(KeyEvent.VK_F2, buttonF2);
        buttonMap.put(KeyEvent.VK_F3, buttonF3);
        buttonMap.put(KeyEvent.VK_F4, buttonF4);
        buttonMap.put(KeyEvent.VK_F5, buttonF5);
        buttonMap.put(KeyEvent.VK_F6, buttonF6);
        buttonMap.put(KeyEvent.VK_F7, buttonF7);
        buttonMap.put(KeyEvent.VK_F8, buttonF8);
        buttonMap.put(KeyEvent.VK_F9, buttonF9);
        buttonMap.put(KeyEvent.VK_PAUSE, buttonPause);
        buttonMap.put(KeyEvent.VK_PRINTSCREEN, buttonPrScr);
        buttonMap.put(KeyEvent.VK_PREVIOUS_CANDIDATE, buttonUpp);
        buttonMap.put(KeyEvent.VK_PAGE_UP, buttonUp);
        //////////////////////
        buttonMap.put(0, buttonSquare);
        buttonMap.put(KeyEvent.VK_1, button1);
        buttonMap.put(KeyEvent.VK_2, button2);
        buttonMap.put(KeyEvent.VK_3, button3);
        buttonMap.put(KeyEvent.VK_4, button4);
        buttonMap.put(KeyEvent.VK_5, button5);
        buttonMap.put(KeyEvent.VK_6, button6);
        buttonMap.put(KeyEvent.VK_7, button7);
        buttonMap.put(KeyEvent.VK_8, button8);
        buttonMap.put(KeyEvent.VK_9, button9);
        buttonMap.put(KeyEvent.VK_0, button0);
        buttonMap.put(KeyEvent.VK_RIGHT_PARENTHESIS, buttonRightPar);
        buttonMap.put(KeyEvent.VK_EQUALS, buttonEqual);
        buttonMap.put(KeyEvent.VK_BACK_SPACE, buttonBack);
        buttonMap.put(KeyEvent.VK_NUM_LOCK, buttonNum);
        buttonMap.put(KeyEvent.VK_SLASH, buttonSlash);
        buttonMap.put(0, buttonFois);
        buttonMap.put(0, buttonMoins);
        ////////////////////////////////////////////////////////////////////
        buttonList.add(buttonEscape);
        buttonList.add(buttonF1);
        buttonList.add(buttonF2);
        buttonList.add(buttonF3);
        buttonList.add(buttonF4);
        buttonList.add(buttonF5);
        buttonList.add(buttonF6);
        buttonList.add(buttonF7);
        buttonList.add(buttonF8);
        buttonList.add(buttonF9);
        buttonList.add(buttonF10);
        buttonList.add(buttonF11);
        buttonList.add(buttonF12);
        buttonList.add(buttonPause);
        buttonList.add(buttonPrScr);
        buttonList.add(buttonDel);
        buttonList.add(buttonUpp);
        buttonList.add(buttonUp);
        buttonList.add(buttonDown);
        buttonList.add(buttonEnd);
        
        buttonList.add(buttonSquare);
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);
        buttonList.add(button5);
        buttonList.add(button6);
        buttonList.add(button7);
        buttonList.add(button8);
        buttonList.add(button9);
        buttonList.add(button0);
        buttonList.add(buttonRightPar);
        buttonList.add(buttonEqual);
        buttonList.add(buttonBack);
        buttonList.add(buttonNum);
        buttonList.add(buttonSlash);
        buttonList.add(buttonFois);
        buttonList.add(buttonMoins);
        ///////////////
        for (int key : buttonMap.keySet()) {
            buttonMap.get(key).addKeyListener(this);
        }  
        for (int i = 0; i < 20; ++i) {
            this.boxLine1.add(buttonList.get(i));
        }       
        for (int i = 20; i < 38; ++i) {
            this.boxLine2.add(buttonList.get(i));
        }       
//        for (int i = 20; i < 38; ++i) {
//            this.boxLine2.add(buttonList.get(i));
//        }
//        for (int i = 38; i < 56; ++i) {
//            this.boxLine3.add(buttonList.get(i));
//        }       
    }
//    
//    private void createLine2(){
//        
//    }
//    
//    private void createLine3(){
//        this.boxLine3.add(new KeyboardButtonImpl("↔", 0));
//        this.boxLine3.add(new KeyboardButtonImpl("A", 0));
//        this.boxLine3.add(new KeyboardButtonImpl("Z", 0));
//        this.boxLine3.add(new KeyboardButtonImpl("E", 0));
//        this.boxLine3.add(new KeyboardButtonImpl("R", 0));
//        this.boxLine3.add(new KeyboardButtonImpl("T", 0));
//        this.boxLine3.add(new KeyboardButtonImpl("Y", 0));
//        this.boxLine3.add(new KeyboardButtonImpl("U", 0));
//        this.boxLine3.add(new KeyboardButtonImpl("I", 0));
//        this.boxLine3.add(new KeyboardButtonImpl("O", 0));
//        this.boxLine3.add(new KeyboardButtonImpl("P", 0));
//        this.boxLine3.add(new KeyboardButtonImpl("^", 0));
//        this.boxLine3.add(new KeyboardButtonImpl("$", 0));
//        this.boxLine3.add(new KeyboardButtonImpl("ENTREE", 0));
//        this.boxLine3.add(new KeyboardButtonImpl("7", 0));
//        this.boxLine3.add(new KeyboardButtonImpl("8", 0));
//        this.boxLine3.add(new KeyboardButtonImpl("9", 0));
//        this.boxLine3.add(new KeyboardButtonImpl("+", 0));
//    }
//    
//    private void createLine4(){
//        this.boxLine4.add(new KeyboardButtonImpl("MAJ", 0));
//        this.boxLine4.add(new KeyboardButtonImpl("Q", 0));
//        this.boxLine4.add(new KeyboardButtonImpl("S", 0));
//        this.boxLine4.add(new KeyboardButtonImpl("D", 0));
//        this.boxLine4.add(new KeyboardButtonImpl("F", 0));
//        this.boxLine4.add(new KeyboardButtonImpl("G", 0));
//        this.boxLine4.add(new KeyboardButtonImpl("H", 0));
//        this.boxLine4.add(new KeyboardButtonImpl("J", 0));
//        this.boxLine4.add(new KeyboardButtonImpl("K", 0));
//        this.boxLine4.add(new KeyboardButtonImpl("L", 0));
//        this.boxLine4.add(new KeyboardButtonImpl("M", 0));
//        this.boxLine4.add(new KeyboardButtonImpl("ù", 0));
//        this.boxLine4.add(new KeyboardButtonImpl("*", 0));
//        this.boxLine4.add(new KeyboardButtonImpl("4", 0));
//        this.boxLine4.add(new KeyboardButtonImpl("5", 0));
//        this.boxLine4.add(new KeyboardButtonImpl("6", 0));
//    }
//    
//    private void createLine5(){
//        this.boxLine5.add(new KeyboardButtonImpl("↑", 0));
//        this.boxLine5.add(new KeyboardButtonImpl("<", 0));
//        this.boxLine5.add(new KeyboardButtonImpl("W", 0));
//        this.boxLine5.add(new KeyboardButtonImpl("X", 0));
//        this.boxLine5.add(new KeyboardButtonImpl("C", 0));
//        this.boxLine5.add(new KeyboardButtonImpl("V", 0));
//        this.boxLine5.add(new KeyboardButtonImpl("B", 0));
//        this.boxLine5.add(new KeyboardButtonImpl("N", 0));
//        this.boxLine5.add(new KeyboardButtonImpl(",", 0));
//        this.boxLine5.add(new KeyboardButtonImpl(";", 0));
//        this.boxLine5.add(new KeyboardButtonImpl(":", 0));
//        this.boxLine5.add(new KeyboardButtonImpl("!", 0));
//        this.boxLine5.add(new KeyboardButtonImpl("↑", 0));
//        this.boxLine5.add(new KeyboardButtonImpl("▲", 0));
//        this.boxLine5.add(new KeyboardButtonImpl("1", 0));
//        this.boxLine5.add(new KeyboardButtonImpl("2", 0));
//        this.boxLine5.add(new KeyboardButtonImpl("3", 0));
//    }
//    
//    private void createLine6(){
//        this.boxLine6.add(new KeyboardButtonImpl("ctrl", 0));
//        this.boxLine6.add(new KeyboardButtonImpl("fn", 0));
//        this.boxLine6.add(new KeyboardButtonImpl("♦", 0));
//        this.boxLine6.add(new KeyboardButtonImpl("alt", 0));
//        this.boxLine6.add(new KeyboardButtonImpl("space", 0));
//        this.boxLine6.add(new KeyboardButtonImpl("alt gr", 0));
//        this.boxLine6.add(new KeyboardButtonImpl("ctrl", 0));
//        this.boxLine6.add(new KeyboardButtonImpl("◄", 0));
//        this.boxLine6.add(new KeyboardButtonImpl("▼", 0));
//        this.boxLine6.add(new KeyboardButtonImpl("►", 0));
//        this.boxLine6.add(new KeyboardButtonImpl("0", 0));
//        this.boxLine6.add(new KeyboardButtonImpl(".", 0));
//        this.boxLine6.add(new KeyboardButtonImpl("ENTREE", 0));
//    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        ButtonModel model = buttonMap.get(e.getKeyCode()).getModel();
        //buttonMap.get(e.getKeyCode()).revalidate();
        System.err.println("yyyyyyyyyyy");
        try {
            model.setPressed(true);
            Thread.sleep(1000);
            System.err.println("vvvvvvvvv");
        } catch (InterruptedException ex) {
            Logger.getLogger(KeyboardImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        model.setPressed(false);
        //buttonMap.get(e.getKeyCode()).revalidate();
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
