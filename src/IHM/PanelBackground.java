package IHM;

import Explorator.FileItem;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Romain
 */
public class PanelBackground extends JPanel implements MouseMotionListener, MouseListener{
    
    private final int X_IMGTV = 480;
    private final int Y_IMGTV = 315;
    private final int X_IMGPC = 739;
    private final int Y_IMGPC = 316;
    private final int WIDTH_IMGTV = 138;
    private final int HEIGHT_IMGTV = 130;
    private final int WIDTH_IMGPC = 138;
    private final int HEIGHT_IMGPC = 129;
    
    private Image               img                 ;
    private Image               imgTele             ;
    private Image               imgPC               ;
    private Image               imgTele2            ;
    private Image               imgPC2              ;
    private String              imgPath             = "./IconsWin8/android2.jpg";
    private String              telePath            = "./IconsWin8/animTV.png";
    private String              PCPath              = "./IconsWin8/animPC.png";
    private String              telePath2           = "./IconsWin8/animTV2.png";
    private String              PCPath2             = "./IconsWin8/animPC2.png";
    private int                 screenWidth         ;
    private int                 screenHeight        ;
    private int                 xpointsLeft[]       = {5, 296, 296, 5};
    private int                 ypointsLeft[]       = {73, 73, 101, 101};
    private Polygon             rectLeft            = new Polygon(xpointsLeft, ypointsLeft, 4);
    private Polygon             rectRight           ;
    private boolean             isInsideRectLeft    = false;
    private boolean             isInsideRectRight   = false;
    private boolean             isInsideAndroid     = false;
    private boolean             isAndroidSelected   = false;
    public static boolean       isTVSelected        = true;
    public static boolean       isPCSelected        = false;
    private PanelCenter         panelCenter         ;
    private Image               imgAndroid          ;
    private BufferedImage       imgBuffered         ;
    private String              imgPathAndroid      = "./IconsWin8/bonhomme_android.png" ;
    private BoxListHistory      boxListHistory      = new BoxListHistory();
    private ArrayList<Integer>  listOfPixels        = new ArrayList<>();
    private ArrayList<Integer>  listOfInitialPixels = new ArrayList<>();
    private Date                startDate           ;           
    public static ArrayList<FileItem> listOfFileItems = new ArrayList<>();
    
    public PanelBackground(JFrame mainFrame){
        this.setLayout(new BorderLayout());
        this.panelCenter = new PanelCenter();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
        GraphicsDevice gd =ge.getDefaultScreenDevice(); 
        GraphicsConfiguration gc =gd.getDefaultConfiguration(); 
        Rectangle bounds = gc.getBounds();
        
        this.screenWidth = bounds.width; 
        this.screenHeight = bounds.height;
        int xpointsRight[] = {this.screenWidth-3, this.screenWidth - 57, this.screenWidth - 57, this.screenWidth-3};
        int ypointsRight[] = {50, 50, this.screenHeight-2, this.screenHeight-2};
        this.rectRight = new Polygon(xpointsRight, ypointsRight, 4);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.add(panelCenter, BorderLayout.CENTER);
        this.add(boxListHistory, BorderLayout.EAST);
        
        try{
            File input = new File(imgPath);
            img = ImageIO.read(input);
            input = new File(PCPath);
            imgPC = ImageIO.read(input);
            input = new File(telePath);
            imgTele = ImageIO.read(input);
            input = new File(PCPath2);
            imgPC2 = ImageIO.read(input);
            input = new File(telePath2);
            imgTele2 = ImageIO.read(input);
            input = new File(imgPathAndroid);
            imgAndroid = ImageIO.read(input);
        }
        catch(IOException ie){
            Logger.getLogger(PanelBackground.class.getName()).log(Level.SEVERE, null, ie);
        }
        Thread androidAnimation = new Thread(new Runnable() {
            @Override
            public void run() {
                getPixelsInAndroidCharacter();
                startDate = new Date();
                while(true){
                    try {
                        animate();
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PanelBackground.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        androidAnimation.start();
    }
    
    public void getPixelsInAndroidCharacter(){
        imgBuffered = new BufferedImage(imgAndroid.getWidth(null), imgAndroid.getHeight(null), BufferedImage.TYPE_INT_RGB); 
        imgBuffered.getGraphics().drawImage(imgAndroid, 0, 0, null); 
        if(imgBuffered != null) {
            int w = imgBuffered.getWidth();
            int h = imgBuffered.getHeight();
            int rgb[] = new int[w*h];
            imgBuffered.getRGB(0, 0, w, h, rgb, 0, w);
            for(int i=0;i<w*h;i++)
            {
                int r = (rgb[i])&0xFF;
                int g = (rgb[i]>>8)&0xFF;
                int b = (rgb[i]>>16)&0xFF;
                if ((r < 95) && (g < 95) && (b < 95))
                    listOfPixels.add(i);
                listOfInitialPixels.add(i);
            }
        }
    }
    public void animate(){
        
        imgBuffered = new BufferedImage(imgAndroid.getWidth(null), imgAndroid.getHeight(null), BufferedImage.TYPE_INT_RGB); 
        imgBuffered.getGraphics().drawImage(imgAndroid, 0, 0, null); 
        if(imgBuffered != null) {
            int w = imgBuffered.getWidth();
            int h = imgBuffered.getHeight();
            BufferedImage buffer = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);  
            int rgb[] = new int[w*h];
            imgBuffered.getRGB(0, 0, w, h, rgb, 0, w);
            int p;
            Date d = new Date();
            if (d.getTime() < startDate.getTime() + 5000){//300000){ // 5 min
                for(int i = 0 ; i < listOfPixels.size() ; ++i){
                    p = listOfPixels.get(i);
                    rgb[p] = rgb[p] + 0x000001;
                }
                buffer.setRGB(0, 0, w, h, rgb, 0, w);
                imgAndroid = buffer;
                repaint();
            }else{
                startDate = d;
                for(int i = 0 ; i < listOfInitialPixels.size() ; ++i){
                    p = listOfInitialPixels.get(i);
                }
                buffer.setRGB(0, 0, w, h, rgb, 0, w);
                imgAndroid = buffer;
                repaint();
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        g.drawImage(img, 0, 0, screenWidth, screenHeight, null);
        g.drawImage(imgAndroid, 620, 322, 115, 115, this);
        g.setColor(new Color(158, 209, 236));
        g.drawPolygon(rectLeft);
        g.setColor(new Color(158, 209, 236));
        g.drawPolygon(rectRight);
        if (isInsideAndroid || isAndroidSelected){
            if (isTVSelected){
                g.drawImage(imgTele2, X_IMGTV, Y_IMGTV, WIDTH_IMGTV, HEIGHT_IMGTV, this);
                g.drawImage(imgPC, X_IMGPC, Y_IMGPC, WIDTH_IMGPC, HEIGHT_IMGPC, this);
            } else if (isPCSelected){
                g.drawImage(imgPC2, X_IMGPC, Y_IMGPC, WIDTH_IMGPC, HEIGHT_IMGPC, this);
                g.drawImage(imgTele, X_IMGTV, Y_IMGTV, WIDTH_IMGTV, HEIGHT_IMGTV, this);
            }else{
                g.drawImage(imgTele, X_IMGTV, Y_IMGTV, WIDTH_IMGTV, HEIGHT_IMGTV, this);
                g.drawImage(imgPC, X_IMGPC, Y_IMGPC, WIDTH_IMGPC, HEIGHT_IMGPC, this);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        Point p = e.getPoint();
        if (rectLeft.contains(p)){
            if (!isInsideRectLeft){
                isInsideRectLeft = true;
                panelCenter.animeTopToBottom();
            }
        }else if (rectRight.contains(p)){
            if (!isInsideRectRight){
                isInsideRectRight = true;
                boxListHistory.animate();
            }
        }else if ((p.x >= 620) && (p.x <= 735) && (p.y >= 322) && (p.y < 437)){
            if (!isInsideAndroid){
                isInsideAndroid = true;
                repaint();
            }
        }else if (isInsideAndroid && (p.x >= 480) && (p.x <= 877) && (p.y >= 315) && (p.y <= 445)){
            isAndroidSelected = true;
            repaint();
        }else if (!panelCenter.getBoxTree().contains(p)){
            isInsideRectRight = false;
            isInsideRectLeft = false;
            isInsideAndroid = false;
            isAndroidSelected = false;
            panelCenter.setListUnvisible();
            boxListHistory.setUnvisible();
        }
    }   

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        if (p.x >= X_IMGTV && p.x <= X_IMGTV + WIDTH_IMGTV && p.y >= Y_IMGTV && p.y <= Y_IMGTV + HEIGHT_IMGTV){
             isPCSelected = false;
             isTVSelected = true;
             Spartacus.client.setIPAdress("192.168.0.13"); // IP du hackBerry Pi
        }else if (p.x >= X_IMGPC && p.x <= X_IMGPC + WIDTH_IMGPC && p.y >= Y_IMGPC && p.y <= Y_IMGPC + HEIGHT_IMGPC){
             isPCSelected = true;
             isTVSelected = false;
             Spartacus.client.setIPAdress("192.168.0.10"); // IP du PC
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
