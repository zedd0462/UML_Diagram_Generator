package org.mql.java.umlgen.models;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Vector;

import org.mql.java.umlgen.annotations.ComplexElement;
import org.mql.java.umlgen.annotations.SimpleElement;
import org.mql.java.umlgen.xml.XMLElement;
import org.mql.java.umlgen.xml.XMLElementGenerator;

@ComplexElement("constructor")
public class ConstructorModel implements UMLModelEntity{
	
	private List<ParameterModel> parameters;
	private int modifiers;
	
	public ConstructorModel(Constructor<?> constructor) {
		parameters = new Vector<ParameterModel>();
		modifiers = constructor.getModifiers();
		for (Parameter parameter : constructor.getParameters()) {
			parameters.add(new ParameterModel(parameter));
		}
	}

	@Override
	public XMLElement getElementModel(XMLElementGenerator generator) {
		return generator.generate(this);
	}

	@ComplexElement(value="parameters", order=1)
	public List<ParameterModel> getParameters() {
		return parameters;
	}

	@SimpleElement(value="modifiers", order=2)
	public int getModifiers() {
		return modifiers;
	}

	
}
