package org.mql.java.umlgen.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.mql.java.umlgen.ui.ErrorDialog;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * Defines the generator that creates a DOM document from
 * {@code XMLElement}.
 */
public class DOMGenerator {
	Document document;
	

	public DOMGenerator() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.newDocument();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DOMGenerator(XMLElement rootElement, String title) {
		this();
		generateDoc(rootElement, title);
	}
	
	
	public void generateDoc(XMLElement rootElement, String title) {
		Element root = document.createElement(title);
        document.appendChild(root);
        generateElement(root, rootElement);
	}
	
	private void generateElement(Element parent, XMLElement xmlElement) {
        if (xmlElement instanceof XMLSimpleContent) {
            Text text = document.createTextNode(((XMLSimpleContent) xmlElement).getContent());
            parent.appendChild(text);
        } else {
            Element element = document.createElement(xmlElement.getElementName());
            parent.appendChild(element);
            for (XMLAttribute attribute : xmlElement.getAttributes()) {
                element.setAttribute(attribute.getName(), attribute.getValue());
            }
            for (XMLElement child : xmlElement.getChildren()) {
                generateElement(element, child);
            }
        }
    }
	
	/**
	 * Saves the generated dom document in the specified file.
	 * @param filepath Path for the saved file
	 */
	public void dump(String filepath) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(filepath));
            
            transformer.transform(source, result);
            
        } catch (Exception e) {
        	e.printStackTrace();
        	new ErrorDialog(e.toString());
        }
    }

}
