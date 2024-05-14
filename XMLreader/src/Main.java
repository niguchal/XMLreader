import org.w3c.dom.*;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("com.example.gladiatest.SignUp.xml"));

            // Анализ XML файла
            NodeList nodeList = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println("Node name: " + node.getNodeName());
                    NamedNodeMap attributes = node.getAttributes();
                    for (int j = 0; j < attributes.getLength(); j++) {
                        Node attribute = attributes.item(j);
                        System.out.println("Attribute: " + attribute.getNodeName() + " = " + attribute.getNodeValue());
                    }
                    System.out.println("Value: " + node.getTextContent());
                    System.out.println();
                }
            }

            // Редактирование XML файла
            Element newNode = document.createElement("newNode");
            newNode.setAttribute("name", "newValue");
            newNode.setTextContent("newTextNode");

            document.getDocumentElement().appendChild(newNode);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("edited_example.fxml"));

            transformer.transform(source, result);

            System.out.println("XML файл успешно отредактирован и сохранен как edited_example.xml");

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }
}