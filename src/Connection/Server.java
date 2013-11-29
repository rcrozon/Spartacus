/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;


import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Romain
 */
public class Server {
    
    public Server(int port){
        while(true){
            try {
//                System.out.println("Serveur lancé!");
                ServerSocket ssock =new ServerSocket(port, 1);
                Socket csock =ssock.accept();
//                System.out.println("Une connexion entrante...\n\tRéception du fichier en cours...");
                //LECTURE DU SOCKET
                BufferedInputStream bis =new BufferedInputStream(csock.getInputStream());

                //////////////////////////////
                String filePath = "C:/Users/Romain/Desktop/machin";
                byte buf[] = new byte[4096];
                if ( (bis.read(buf)) != -1) {
                    filePath = "C:/Users/Romain/Desktop/" + new String(buf).trim();
                }
                //////////////////////////////
                //ECRITURE DANS LE NOUVEAU FICHIER
                BufferedOutputStream bos =new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                //RéCEPTION DU FICHIER
                int len;
                while ( (len=bis.read(buf)) != -1) {
                    bos.write(buf, 0, len);
                    bos.flush();
                }
                //FERMETURE STREAM
                bos.close();
                bis.close();
                ssock.close();
                csock.close();
//                System.out.println("Fichier réceptionné !\n\n");
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
