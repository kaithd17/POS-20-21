/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domintro.xml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import beans.Address;
import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author kainz
 */
public class XML_Access {
    private Document doc;
    
    /**
     * Deserialization of xml-Document from file into Java-DOM-Object
     * 
     * @param file
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     */
    public void loadFromXmlFile(File file) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        doc = builder.parse(file);
    }
    
    public void saveToXmlFile(File file) throws TransformerConfigurationException, TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setAttribute("indent-number", 2);
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        Result result = new StreamResult(file);
        transformer.transform(new DOMSource(doc), result);
    }
    
    public List<Address> getAddressList() {
        List<Address> adrList = new ArrayList<>();
        NodeList nl = doc.getDocumentElement().getElementsByTagName("address");
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                adrList.add(convertNodeToAddress((Element)node));
            }
        }
        return adrList;
    }
    
    public Address  convertNodeToAddress(Element element) {
        String name = element.getElementsByTagName("name").item(0).getTextContent();
        String country = element.getElementsByTagName("country").item(0).getTextContent();
        String city = element.getElementsByTagName("city").item(0).getTextContent();
        String email = element.getElementsByTagName("email").item(0).getTextContent();
        String gender = element.getElementsByTagName("gender").item(0).getTextContent();
        String timezone = element.getElementsByTagName("timezone").item(0).getTextContent();
        return new Address(name, country, city, email, gender, timezone);
    }
    
    public static void main(String[] args) {
        XML_Access xmla = new XML_Access();
        Path xmlFilePath1 = Paths.get(System.getProperty("user.dir"),"src","main","java","res","addresses.xml");
        Path xmlFilePath2 = Paths.get(System.getProperty("user.dir"),"src","main","java","res","addresses_new.xml");
        try{
            xmla.loadFromXmlFile(xmlFilePath1.toFile());
           // xmla.saveToXmlFile(xmlFilePath2.toFile());
           for(Address address : xmla.getAddressList()){
               System.out.println(address);
           }
        }catch(Exception ex){
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }
}
