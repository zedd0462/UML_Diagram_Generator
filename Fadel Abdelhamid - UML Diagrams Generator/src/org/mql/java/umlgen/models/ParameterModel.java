package org.mql.java.umlgen.models;

import java.lang.reflect.Parameter;

import org.mql.java.umlgen.annotations.ComplexElement;
import org.mql.java.umlgen.annotations.SimpleElement;
import org.mql.java.umlgen.xml.XMLElement;
import org.mql.java.umlgen.xml.XMLElementGenerator;

@ComplexElement(value="parameter")
public class ParameterModel implements UMLModelEntity {
	
	private String type;
	private String name;
	private Parameter reflectParemeter;

	public ParameterModel(Parameter parameter) {
		reflectParemeter = parameter;
		this.type = parameter.getType().getName();
		this.name = parameter.getName();
	}

	@Override
	public XMLElement getElementModel(XMLElementGenerator generator) {
		return generator.generate(this);
	}
	
	@SimpleElement(value="type", order=2)
	public String getType() {
		return type;
	}

	
	@SimpleElement(value="name", order=1)
	public String getName() {
		return name;
	}
	
	public Parameter getReflectParemeter() {
		return reflectParemeter;
	}
	
	

}
