/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.ini.xml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import at.kaindorf.ini.pojos.Window;
import java.awt.Dimension;
import java.awt.Point;
import java.io.PrintWriter;
import javax.swing.JDialog;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author kainz
 */
public class XMLIniApp {

    private Document doc;
    private static XMLIniApp instance;
    private static List<Window> windowList = new ArrayList<>();
    private static final int DEFAULT_WIDTH = 250;
    private static final int DEFAULT_HEIGHT = 150;
    private static final int DEFAULT_XPOS = 50;
    private static final int DEFAULT_YPOS = 50;
    private boolean fileExists = true;
    private Path xmlFilePath = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "at", "kaindorf", "ini", "res", "Ini.xml");

    public XMLIniApp() {
    }

    public XMLIniApp(Document doc) {
        this.doc = doc;
    }

    public static XMLIniApp getInstance() {
        if (XMLIniApp.instance == null) {
            XMLIniApp.instance = new XMLIniApp();
        }
        return XMLIniApp.instance;
    }

    //window methods
    public void loadIniFile() throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        try {
            doc = builder.parse(xmlFilePath.toFile());
        } catch (FileNotFoundException ex) {
            doc = builder.newDocument();
            doc.appendChild(doc.createElement("windows"));
            fileExists = false;
        }
        windowList = getWindowList();
    }

    public void initWindow(JFrame jframe, String name) {
        boolean newWindow = true;
        Window window = null;
        if (windowList.size() == 0) {
            window = new Window(name, DEFAULT_XPOS, DEFAULT_YPOS, DEFAULT_WIDTH, DEFAULT_HEIGHT);
            windowList.add(window);
            insertWindow(window, name);
        } else {
            for (int i = 0; i < windowList.size(); i++) {
                if (windowList.get(i).getName().equals(name)) {
                    window = windowList.get(i);
                    newWindow = false;
                    break;
                }
            }
            if (newWindow) {
                window = new Window(name, DEFAULT_XPOS, DEFAULT_YPOS, DEFAULT_WIDTH, DEFAULT_HEIGHT);
                windowList.add(window);
                insertWindow(window, name);
            }
        }
        jframe.setSize(window.getWidth(), window.getHeight());
        jframe.setLocation(window.getXpos(), window.getYpos());
    }

    public void updateWindow(JFrame jframe, String name) {
        Window window = null;
        for (int i = 0; i < windowList.size(); i++) {
            if (windowList.get(i).getName().equals(name)) {
                window = windowList.get(i);
                break;
            }
        }

        Dimension dimension = jframe.getSize();
        Point point = jframe.getLocation();

        window.setHeight((int) dimension.getHeight());
        window.setWidth((int) dimension.getWidth());
        window.setXpos((int) point.getX());
        window.setYpos((int) point.getY());
    }

    public void initWindow(JDialog jdialog, String name) {
        boolean newWindow = true;
        Window window = null;
        if (windowList.size() == 0) {
            window = new Window(name, DEFAULT_XPOS, DEFAULT_YPOS, DEFAULT_WIDTH, DEFAULT_HEIGHT);
            windowList.add(window);
            insertWindow(window, name);
        } else {
            for (int i = 0; i < windowList.size(); i++) {
                if (windowList.get(i).getName().equals(name)) {
                    window = windowList.get(i);
                    newWindow = false;
                    break;
                }
            }
            if (newWindow) {
                window = new Window(name, DEFAULT_XPOS, DEFAULT_YPOS, DEFAULT_WIDTH, DEFAULT_HEIGHT);
                windowList.add(window);
                insertWindow(window, name);
            }
        }
        jdialog.setSize(window.getWidth(), window.getHeight());
        jdialog.setLocation(window.getXpos(), window.getYpos());
    }

    public void updateWindow(JDialog jdialog, String name) {
        Window window = null;
        for (int i = 0; i < windowList.size(); i++) {
            if (windowList.get(i).getName().equals(name)) {
                window = windowList.get(i);
                break;
            }
        }

        Dimension dimension = jdialog.getSize();
        Point point = jdialog.getLocation();

        window.setHeight((int) dimension.getHeight());
        window.setWidth((int) dimension.getWidth());
        window.setXpos((int) point.getX());
        window.setYpos((int) point.getY());
    }

    public void saveIniFile() throws TransformerConfigurationException, TransformerException, FileNotFoundException {
        while (doc.getDocumentElement().hasChildNodes()) {
            doc.getDocumentElement().removeChild(doc.getDocumentElement().getFirstChild());
        }
        for (Window window : windowList) {
            insertWindow(window, window.getName());
        }

        if (fileExists) {
            //if file exists --> clear file
            PrintWriter writer = new PrintWriter(xmlFilePath.toFile());
            writer.print("");
            writer.close();
        }

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        Result result = new StreamResult(xmlFilePath.toFile());
        transformer.transform(new DOMSource(doc), result);
    }

    //helper methods 
    public List<Window> getWindowList() {
        List<Window> windows = new ArrayList<>();
        NodeList nl = doc.getDocumentElement().getElementsByTagName("window");
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                windows.add(convertNodeToWindow((Element) node, ((Element) node).getAttribute("name")));
            }
        }
        return windows;
    }

    public Window convertNodeToWindow(Element element, String name) {
        int width = Integer.parseInt(element.getElementsByTagName("width").item(0).getTextContent());
        int height = Integer.parseInt(element.getElementsByTagName("height").item(0).getTextContent());
        int xpos = Integer.parseInt(element.getElementsByTagName("xpos").item(0).getTextContent());
        int ypos = Integer.parseInt(element.getElementsByTagName("ypos").item(0).getTextContent());
        return new Window(name, xpos, ypos, width, height);
    }

    public void insertWindow(Window window, String name) {
        Element windowElement = doc.createElement("window");
        doc.getDocumentElement().appendChild(windowElement);
        windowElement.setAttribute("name", name);
        insertChild(windowElement, "xpos", window.getXpos() + "");
        insertChild(windowElement, "ypos", window.getYpos() + "");
        insertChild(windowElement, "width", window.getWidth() + "");
        insertChild(windowElement, "height", window.getHeight() + "");
    }

    public void insertChild(Element parent, String tagname, String textContent) {
        Element element = doc.createElement(tagname);
        element.setTextContent(textContent);
        parent.appendChild(element);
    }

}
