/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import Explorator.FileItem;
import IHM.PanelItemList;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Romain
 */



public class XMLWriter {
    
    DocumentBuilderFactory  docBuilderFactory   ;
    DocumentBuilder         docBuilder          = null;
    Document                doc                 = null;
    File                    file                = new File("./config.xml");
    public XMLWriter(){
        
        docBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = docBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            doc = docBuilder.parse("./config.xml");
        } catch (SAXException ex) {
            Logger.getLogger(XMLWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addToFavorites(FileItem item){
        
        Element config = doc.getDocumentElement();
        Element favorites = doc.createElement("Favorites");
        favorites.setAttribute("filepath", item.getFilePath());
        favorites.setAttribute("filename", item.getFileName());
        config.appendChild(favorites);
        write();
    } 
    
    public void removeFromFavorites(FileItem item){
        Element config = doc.getDocumentElement();
        NodeList elements = doc.getElementsByTagName("Favorites"); 
	for ( int i = 0; i < elements.getLength() ; ++i){
            Element elementCl 	= (Element)elements.item(i);
            if (elementCl.getAttribute("filename").equals(item.getFileName()) && elementCl.getAttribute("filepath").equals(item.getFilePath())){
                config.removeChild(elementCl);
            }
        }
        write();
    }
    
    public boolean isInFavorites(FileItem item){
        Element config = doc.getDocumentElement();
        NodeList elements = doc.getElementsByTagName("Favorites"); 
	for ( int i = 0; i < elements.getLength() ; ++i){
            Element elementCl 	= (Element)elements.item(i);
            if (elementCl.getAttribute("filename").equals(item.getFileName()) && elementCl.getAttribute("filepath").equals(item.getFilePath())){
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<FileItem> getFavorites(){
        ArrayList<FileItem> list = new ArrayList<>();
        Element config = doc.getDocumentElement();
        NodeList elements = doc.getElementsByTagName("Favorites"); 
	for ( int i = 0; i < elements.getLength() ; ++i){
            Element elementCl 	= (Element)elements.item(i);
            list.add(new FileItem(elementCl.getAttribute("filepath")));
        }
        return list;
    }
    
    public ArrayList<PanelItemList> getHistory(){
        ArrayList<PanelItemList> list = new ArrayList<>();
        Element config = doc.getDocumentElement();
        NodeList elements = doc.getElementsByTagName("History"); 
	for ( int i = 0; i < elements.getLength() ; ++i){
            Element elementCl 	= (Element)elements.item(i);
            list.add(new PanelItemList(new FileItem(elementCl.getAttribute("filepath"))));
        }
        return list;
    }
    
    public void addToHistory(FileItem item){
        Element config = doc.getDocumentElement();
        NodeList elements = doc.getElementsByTagName("History"); 
        boolean isAlreadyInHistory = false;
	int historyCount = elements.getLength();
        if (historyCount >= 10){
            for (int i = 0; i < historyCount - 10; ++i){
                Element elementCl = (Element)elements.item(i);
                config.removeChild(elementCl);
            }
        }
        elements = doc.getElementsByTagName("History"); 
	historyCount = elements.getLength();
        for (int i = 0; i < historyCount; ++i){
            Element elementCl = (Element)elements.item(i);
            if (elementCl.getAttribute("filepath").equals(item.getFilePath()))
                isAlreadyInHistory = true;
        }
        if (!isAlreadyInHistory){
            Element history = doc.createElement("History");
            history.setAttribute("filepath", item.getFilePath());
            history.setAttribute("filename", item.getFileName());
            config.appendChild(history);
            write();
        }
    }
    
    
    public void write(){
        
        // writing the content into the XML file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult("./config.xml");
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
