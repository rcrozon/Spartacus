/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.File;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Romain
 */
public class ConnexionMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, Exception {
        // TODO code application logic here
        Thread threadServer = new Thread(new Runnable() {

            @Override
            public void run() {
                Server server = new Server(8000);
            }
        });
        threadServer.start();
        Thread threadClient = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Client client = new Client(); 
                    File f = new File("C:/Users/Romain/Desktop/vlc.png");
                    //client.sendFile(f);
                    client.sendCommand("dir");
                } catch (UnknownHostException ex) {
                    Logger.getLogger(ConnexionMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        threadClient.start();
    }
}
