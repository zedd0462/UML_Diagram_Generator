package org.mql.java.umlgen.xml;

public class XMLAttribute {
	
	private String name;
	private String value;
	
	protected XMLAttribute() {
		
	}
	
	public XMLAttribute(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}

}
