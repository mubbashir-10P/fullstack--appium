package com.qa.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Utilities {
    public static final long WAIT = 10;

    public HashMap<String, String> parseStringXML(InputStream file) throws Exception {
        HashMap<String,String> stringMap = new HashMap<>();

        //Get Document Builder.

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Build Document.

        Document document = builder.parse(file);

        // Normalize the XML structure.
        document.getDocumentElement().normalize();
        Element root = document.getDocumentElement();
        log().info(root.getNodeName());

        //Get all elements.
        NodeList nodeList = document.getElementsByTagName("string");
        log().info("-------------------");

        for (int temp = 0; temp< nodeList.getLength(); temp++){
            Node node = nodeList.item(temp);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;
                // Store each element value in the Map.

                stringMap.put(element.getAttribute("name"), element.getTextContent());
            }
        }
        return stringMap;
    }

    public String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public boolean checkIfAppiumServerIsRunning(int port){
        boolean isAppiumServerRunning = false;
        ServerSocket socket;

        try{
            socket = new ServerSocket(port);
            socket.close();
        }
        catch (IOException ex){
            isAppiumServerRunning = true;
            log().info("Appium server running = true.");
        }
        finally {
            socket = null;
        }
        return isAppiumServerRunning;
    }

    public Logger log(){
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
    }
}
