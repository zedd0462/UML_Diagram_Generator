package org.mql.java.umlgen.xml;

public class XMLAttribute {
	
	private String attributeName;
	private String attributeValue;
	
	protected XMLAttribute() {
		
	}
	
	public XMLAttribute(String attributeName, String attributeValue) {
		super();
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
	}
	
	public String getAttributeName() {
		return attributeName;
	}
	
	public String getAttributeValue() {
		return attributeValue;
	}

}
