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

    private XML_Access() {
    }

    private XML_Access(Document doc) {
        this.doc = doc;
    }

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
                adrList.add(convertNodeToAddress((Element) node));
            }
        }
        return adrList;
    }

    public Address convertNodeToAddress(Element element) {
        String name = element.getElementsByTagName("name").item(0).getTextContent();
        String country = element.getElementsByTagName("country").item(0).getTextContent();
        String city = element.getElementsByTagName("city").item(0).getTextContent();
        String email = element.getElementsByTagName("email").item(0).getTextContent();
        String gender = element.getElementsByTagName("gender").item(0).getTextContent();
        String timezone = element.getElementsByTagName("timezone").item(0).getTextContent();
        return new Address(name, country, city, email, gender, timezone);
    }

    public void insertAddress(Address address) {
        Element addrElement = doc.createElement("address"); // create new Element
        doc.getDocumentElement().appendChild(addrElement);  // insert new Element into DOM-tree

        insertChild(addrElement, "name", address.getName());
        insertChild(addrElement, "country", address.getCountry());
        insertChild(addrElement, "city", address.getCity());
        insertChild(addrElement, "email", address.getEmail());
        insertChild(addrElement, "gender", address.getGender());
        insertChild(addrElement, "timezone", address.getTimezone());

    }

    public void insertChild(Element parent, String tagname, String textContent) {
        Element element = doc.createElement(tagname);
        element.setTextContent(textContent);
        parent.appendChild(element);
    }

    /**
     * removes all address-nodes from the DOM-doc with the specified country
     *
     * @param country
     */
    public void removeAddressesByCountry(String country) {
        NodeList nl = doc.getElementsByTagName("address");
        for (int i = 0; i < nl.getLength(); i++) {
            Element element = (Element) nl.item(i);
            if (element.getElementsByTagName("country").item(0).getTextContent().equalsIgnoreCase(country)) {
                doc.getDocumentElement().removeChild(element);
            }
        }
    }

    /**
     * adds to every address-node a unique ID as an attribute
     */
    public void addIdAsAttributeToAddress() {
        NodeList nl = doc.getElementsByTagName("address");
        for (int i = 0; i < nl.getLength(); i++) {
            Element element = (Element) nl.item(i);
            element.setAttribute("id", i + "");
        }
    }

    /**
     * creates a new DOM-document that contains only address-nodes of the
     * specified gender
     *
     * @param gender
     * @return
     */
    public Document createDOMByGender(char gender) {
        Document clone = (Document) doc.cloneNode(true);

        NodeList nl = clone.getElementsByTagName("address");
        for (int i = 0; i < nl.getLength(); i++) {
            Element element = (Element) nl.item(i);
            if (!(element.getElementsByTagName("gender").item(0).getTextContent().toUpperCase().startsWith(gender + "".toUpperCase()))) {
                clone.getDocumentElement().removeChild(element);
            }
        }
        return clone;
    }

    public static void main(String[] args) {
        XML_Access xmla = new XML_Access();
        Path xmlFilePath1 = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "res", "addresses.xml");
        Path xmlFilePath2 = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "res", "addresses_new1.xml");
        Path xmlFilePath3 = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "res", "addresses_new2.xml");
        Path xmlFilePath4 = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "res", "addresses_new3.xml");
        try {
            xmla.loadFromXmlFile(xmlFilePath1.toFile());
            // xmla.saveToXmlFile(xmlFilePath2.toFile());
            for (Address address : xmla.getAddressList()) {
                System.out.println(address);
            }
//            xmla.insertAddress(new Address("Joe Biden", "USA", "Washington", "president@gov.us", "Male", "na"));
//            xmla.saveToXmlFile(xmlFilePath2.toFile());
            xmla.removeAddressesByCountry("Brazil");
            xmla.saveToXmlFile(xmlFilePath2.toFile());
            xmla.addIdAsAttributeToAddress();
            xmla.saveToXmlFile(xmlFilePath3.toFile());
            XML_Access xmla2 = new XML_Access(
                    xmla.createDOMByGender('F')
            );
            xmla2.saveToXmlFile(xmlFilePath4.toFile());
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }
}
