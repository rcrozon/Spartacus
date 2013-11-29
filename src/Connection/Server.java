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
                String command = "";
                //////////////////////////////
                String mode = "0";
                byte bufMode[] = new byte[56];
                if ( (bis.read(bufMode)) != -1) {
                    mode = new String(bufMode);
                }
                if (mode.equals("0")){
                    String filePath = "D:/machin";
                    byte bufFile[] = new byte[1024];
                    if ( (bis.read(bufFile)) != -1) {
                        filePath = "D:/" + new String(bufFile).trim();
                    }
                    //////////////////////////////
                    //ECRITURE DANS LE NOUVEAU FICHIER
                    BufferedOutputStream bos =new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                    //RéCEPTION DU FICHIER
                    int len;
                    while ( (len=bis.read(bufFile)) != -1) {
                        bos.write(bufFile, 0, len);
                        bos.flush();
                    }
                    bos.close();
                }else{
                    byte bufCommand[] = new byte[56];
                    if ( (bis.read(bufCommand)) != -1) {
                        command = new String(bufCommand);
                    }
                    executeCommand(command);
                }
                //FERMETURE STREAM
                bis.close();
                ssock.close();
                csock.close();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void executeCommand(String command){
        if (System.getProperties().get("os.name").equals("Windows 8")){ //WINDOWS
            System.err.println("exec" + command);
            try {
                Runtime.getRuntime().exec("C:\\Windows\\SysWOW64\\cmd.exe " + command ); 
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{ //LINUX
            try {  
                Runtime.getRuntime().exec("cmd.exe " + command );
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
