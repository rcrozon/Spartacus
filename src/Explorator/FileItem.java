/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Explorator;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Romain
 */
public class FileItem {
    
    private File        file;
    private String      path;
    private String      fileName;
    private ImageIcon   icon ;
    private boolean     isPlaying = false;
    
    public FileItem(File file){
        if (file != null){
            this.file       = file;
            this.path       = file.getAbsolutePath();
            this.fileName   = file.getName();  
            setIcon();
        }
    }
    
    public void setPlaying(boolean isPlaying){
        this.isPlaying = isPlaying;
    }
    
    public boolean isPlaying(){
        return this.isPlaying;
    }
    
    public FileItem(String filePath){
        this(new File(filePath));
    }
    
    public boolean isDirectory(){
        return file.isDirectory();
    }
    
    public void setIcon(){
        ImageIcon iconTmp = null ;
        if (file.isDirectory()){
            switch(fileName){
                case "Desktop" : iconTmp = new ImageIcon("./IconsWin8/desktop.png");break;
                case "Pictures" : iconTmp = new ImageIcon("./IconsWin8/photos.png");break;
                case "Music" : iconTmp = new ImageIcon("./IconsWin8/music.png");break;
                default: iconTmp = new ImageIcon("./IconsWin8/folder_blue.png");break;
            }
        }else{
            try{
                String extension = fileName.substring(fileName.indexOf(".", fileName.length() - 5));
                switch(extension){
                    case ".avi" : case ".mp4" : case ".mkv" : iconTmp = new ImageIcon("./IconsWin8/largeVLC.png");break;
                    case ".exe" : case ".EXE" : iconTmp = new ImageIcon("./IconsWin8/exe.png");break;
                    case ".pdf" : iconTmp = new ImageIcon("./IconsWin8/adobe.png");break;
                    case ".txt" : iconTmp = new ImageIcon("./IconsWin8/blocNote.png");break;
                    case ".mp3" : case ".mpg" : case ".m4a" : iconTmp = new ImageIcon("./IconsWin8/music.png");break;
                    case ".xls" : case ".xlsm" : iconTmp = new ImageIcon("./IconsWin8/excel.png");break;
                    case ".docx" : iconTmp = new ImageIcon("./IconsWin8/word.png");break;
                    case ".xml" : case ".html" : iconTmp = new ImageIcon("./IconsWin8/chrome.png");break;
                    case ".png" : case ".JPEG" : case ".JPG" : case ".jpg" : case ".jpeg" : case ".gif" : iconTmp = new ImageIcon("./IconsWin8/photos.png");break;
                    default: FileSystemView view = FileSystemView.getFileSystemView();
                             iconTmp = (ImageIcon)view.getSystemIcon(file);break;
                }
            }catch(StringIndexOutOfBoundsException ex){}
        }
        if (iconTmp != null){
            Image img = iconTmp.getImage();
            BufferedImage bufferedImage = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB); 
            Graphics g = bufferedImage.createGraphics(); 
            g.drawImage(img, 0, 0, 20, 20, null); 
            icon = new ImageIcon(bufferedImage);
        } 
    }
    
    public String getApplication(){
        String extension = fileName.substring(fileName.indexOf(".", fileName.length() - 5)).toLowerCase();
        String applicationPath = "" ;
        switch (extension){
            case ".mp3" : case ".mpg" : case ".m4a" : case ".avi" : case ".mp4" : case ".mkv" : applicationPath = "\"C:\\Program Files (x86)\\VideoLAN\\VLC\\vlc.exe\"" + " --fullscreen ";break;
            case ".pdf" : applicationPath = "\"C:\\Program Files (x86)\\Adobe\\Reader 10.0\\Reader\\AcroRd32.exe\"";break;
            case ".txt" : case ".ini" : applicationPath = "\"C:\\Program Files (x86)\\Notepad++\\notepad++.exe\"";break;
            case ".docx" : applicationPath = "\"C:\\Program Files (x86)\\Microsoft Office\\Office14\\WINWORD.EXE\"";break;
            case ".xml" : case ".html" : applicationPath = "\"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe\"";break;
            case ".xls" : case ".xlsm" : applicationPath = "\"C:\\Program Files (x86)\\Microsoft Office\\Office14\\XLICONS.EXE\"";break;
            case ".png" : case ".jpg" : case ".bmp" : case ".jpeg" : case ".gif" : applicationPath = "\"C:\\Program Files (x86)\\Windows Live\\Photo Gallery\\WLXPhotoGallery.exe\"";break;
        }
        return applicationPath ;//applicationPath;
    }
    
    public boolean isAnImage(){
        String s = path.toLowerCase();
        if (s.endsWith(".bmp") || s.endsWith(".png") || 
            s.endsWith(".jpeg") || s.endsWith(".jpg") || 
            s.endsWith(".gif")){
            return true;
        }else
            return false;
    }
    
    public boolean isAVideo(){
        String s = path.toLowerCase();
        if (s.endsWith(".avi") || s.endsWith(".mp3") ||
            s.endsWith(".mpg") || s.endsWith(".m4a") || 
            s.endsWith(".mp4") || s.endsWith(".mkv")){
            return true;
        }else
            return false;
    }
    // Accesseurs
    public String getFilePath(){
        return this.path;
    }
    public String getFileName(){
        return this.fileName;
    }
    public ImageIcon getFileIcon(){
        return this.icon;
    }
    public void setFilePath(String path){
        this.path = path;
    }
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    public void setFileIcon(String iconPath){
        this.icon = new ImageIcon(iconPath);
    }
    public File getFile(){
        return this.file;
    }
    
    @Override
    public String toString(){
        if (fileName.equals("Desktop"))
            return "Bureau";
        else
            return fileName;
    }
}
