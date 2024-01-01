package org.mql.java.umlgen.models;

import org.mql.java.umlgen.annotations.ComplexElement;
import org.mql.java.umlgen.annotations.SimpleElement;
import org.mql.java.umlgen.xml.XMLElement;
import org.mql.java.umlgen.xml.XMLElementGenerator;

@ComplexElement("modifier")
public class ModiferModel implements UMLModelEntity {
	
	private String name;
	private String value;
	
	public ModiferModel(String name, String value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public XMLElement getElementModel(XMLElementGenerator generator) {
		return generator.generate(this);
	}
	
	@SimpleElement("name")
	public String getName() {
		return name;
	}
	
	@SimpleElement("value")
	public String getValue() {
		return value;
	}

}
