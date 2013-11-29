/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Explorator;

import java.io.File;

/**
 *
 * @author Romain
 */
public class Explorator {
    
    public String     desktopPath     = System.getProperty("user.home") + "/Desktop";
    public String     documentsPath   = System.getProperty("user.home") + "/Documents";
    public String     path            ;  
    
    public Explorator(String path){
        this.path = path;
    }

    public Explorator() {
        this.path = documentsPath;
    }
     
    public static FileItem[] getFileItemList(String path){
        assert(path != null);
        File[] f = getFileList(path);
        if (f!= null){
            FileItem[] fileItem = new FileItem[f.length];
            for (int i = 0; i < f.length; ++i)
                fileItem[i] = new FileItem(f[i]);
            return fileItem;
        }
        return null;
    }
    public static File[] getFileList(String directoryPath){
        File[] files = null; 
        File directoryToScan = new File(directoryPath); 
        files = directoryToScan.listFiles(); 
        return files; 
    }
}
