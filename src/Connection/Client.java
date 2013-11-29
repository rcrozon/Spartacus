/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import IHM.FileTransferProgressBar;
import IHM.InfoDialog;
import IHM.PanelCenter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Romain
 */

public class Client {
    
    private int                     port                   = 8000;
    private final String            ADRESS_HACKBERRY      = "192.168.0.10";
    private String                  adress                = ADRESS_HACKBERRY;
    private Socket                  socket                 ;
    private final int               BUFFER_SIZE            = 1024;
    private PanelCenter             panelCenter;
    
    public Client() throws UnknownHostException{
        this("192.168.0.10", 8000);
    }
    
    public Client(String adress, int port) throws UnknownHostException{
        this.adress = adress;
        this.port = port;
    }
    
    public void setIPAdress(String adress){
        this.adress = adress;
    }
    
    public BufferedOutputStream prepareToSend(final String mode){
        BufferedOutputStream bos = null;
        try {
            byte[] bMode = mode.getBytes();
            socket = new Socket(adress, port);
            //panelCenter.setProgressBarFile(src);
            //ECRIRE SUR LA SOCKET
            bos = new BufferedOutputStream(socket.getOutputStream());
            /////////////////////////////////////
            bos.write(bMode);
            bos.flush();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bos;
    }
    
    public void sendCommand(final String command ){
        Thread threadSending = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedOutputStream bos = prepareToSend("0");
                    byte[] bCommand = new byte[56];
                    System.err.println("command" + command);
                    byte[] srcBytes = command.getBytes();
                    for (int i = 0; i < srcBytes.length ; ++i){
                        bCommand[i] = srcBytes[i];
                    }
                    bos.write(bCommand);
                    bos.flush();
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });    
        threadSending.start();
    }
    
    public void sendFile(final File src) {
        Thread threadSending = new Thread(new Runnable() {
            @Override
            public void run() {
                if(src.exists()) { 
                    BufferedOutputStream bos = prepareToSend("1");
                    byte[] buffer   = new byte[BUFFER_SIZE];
                    byte[] fileName = new byte[BUFFER_SIZE];
                    byte[] srcBytes = new String(src.getName()).getBytes();
                    for (int i = 0; i < srcBytes.length ; ++i){
                        fileName[i] = srcBytes[i];
                    }
                    long len = src.length();                
                    long i = 0;
                    BufferedInputStream bis =null;
                    try{
                        //ENVOIE DU NOM DE FICHIER
                        bos.write(fileName);
                        bos.flush();
                        //ENVOIE DU FICHIER
                        //OUVERTURE EN LECTURE DU FICHIER A ENVOYER
                        bis = new BufferedInputStream(new FileInputStream(src));
                        while(bis.read(buffer, 0, BUFFER_SIZE) != -1){
                            i += BUFFER_SIZE;
                            //panelCenter.updateClient(i);
                            bos.write(buffer, 0, BUFFER_SIZE);
                            bos.flush();
                        }
                        //FERMETURE STREAM
                        bis.close();
                        bos.close();
                        socket.close();
                        //EXCEPTION NON GéRéeS

                    } catch (ConnectException ex) {
                        InfoDialog infoDialog = new InfoDialog(ex);
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NullPointerException ex){
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        threadSending.start();
    }
    
    public boolean isClosed() {
        if (socket == null)
            return true;
        else if (socket.isClosed())
            return true;
        else 
            return false;
    }

    public void setPanelCenter(PanelCenter panelCenter) {
        this.panelCenter = panelCenter;
    }
    
}
