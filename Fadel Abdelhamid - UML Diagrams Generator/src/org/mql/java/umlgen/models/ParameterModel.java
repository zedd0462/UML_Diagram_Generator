package org.mql.java.umlgen.models;

import java.lang.reflect.Parameter;

import org.mql.java.umlgen.annotations.ComplexElement;
import org.mql.java.umlgen.annotations.SimpleElement;
import org.mql.java.umlgen.xml.XMLElement;
import org.mql.java.umlgen.xml.XMLElementGenerator;

@ComplexElement("parameter")
public class ParameterModel implements UMLModelEntity {
	
	private String type;
	private String shortType;
	private String name;

	public ParameterModel(Parameter parameter) {
		this.shortType = parameter.getType().getSimpleName();
		this.type = parameter.getType().getName();
		this.name = parameter.getName();
	}

	@Override
	public XMLElement getElementModel(XMLElementGenerator generator) {
		return generator.generate(this);
	}
	
	@SimpleElement("type")
	public String getType() {
		return type;
	}
	
	@SimpleElement("short-type")
	public String getShortType() {
		return shortType;
	}
	
	@SimpleElement("name")
	public String getName() {
		return name;
	}
	
	

}
