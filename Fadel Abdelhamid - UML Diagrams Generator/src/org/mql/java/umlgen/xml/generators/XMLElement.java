package org.mql.java.umlgen.xml.generators;

import java.util.List;
import java.util.Vector;

public class XMLElement {
	
	protected String elementName;
	protected List<XMLAttribute> attributes;
	protected List<XMLElement> children;
	
	protected XMLElement() {
		
	}

	public XMLElement(String elementName) {
		this.elementName = elementName;
		this.attributes = new Vector<XMLAttribute>();
		this.children = new Vector<XMLElement>();
	}
	
	public void addChildren(XMLElement element) {
		this.children.add(element);
	}
	
	public void addAttribute(XMLAttribute attribute) {
		this.attributes.add(attribute);
	}

	public String getElementName() {
		return elementName;
	}

	public List<XMLAttribute> getAttributes() {
		return attributes;
	}

	public List<XMLElement> getChildren() {
		return children;
	}
	
	public String getContent() {
		return "";
	}
	
	
	
	
	
	

}
