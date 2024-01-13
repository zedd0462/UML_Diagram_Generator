package org.mql.java.umlgen.models;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

import org.mql.java.umlgen.annotations.ComplexElement;
import org.mql.java.umlgen.annotations.SimpleElement;
import org.mql.java.umlgen.xml.XMLElement;
import org.mql.java.umlgen.xml.XMLElementGenerator;

@ComplexElement(value="field")
public class FieldModel implements UMLModelEntity{
	
	private String name;
	private String returnType;
	private int modifiers;
	private Field reflectField;

	public FieldModel(Field field) {
		this.reflectField = field;
		this.name = field.getName();
		this.returnType = field.getType().getName();
		modifiers = field.getModifiers();
	}

	@Override
	public XMLElement getElementModel(XMLElementGenerator generator) {
		return generator.generate(this);
	}

	@SimpleElement(value="name", order=1)
	public String getName() {
		return name;
	}

	@SimpleElement(value="return", order=2)
	public String getReturnType() {
		return returnType;
	}
	
	@SimpleElement(value="modifiers", order=3)
	public int getModifiers() {
		return modifiers;
	}
	
	public Field getReflectField() {
		return reflectField;
	}

	
	
}
