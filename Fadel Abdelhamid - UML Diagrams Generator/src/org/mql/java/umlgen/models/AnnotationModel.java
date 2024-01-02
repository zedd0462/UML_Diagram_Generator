package org.mql.java.umlgen.models;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;

import org.mql.java.umlgen.annotations.ComplexElement;
import org.mql.java.umlgen.annotations.SimpleElement;
import org.mql.java.umlgen.xml.XMLElement;
import org.mql.java.umlgen.xml.XMLElementGenerator;

@ComplexElement(value="annotation")
public class AnnotationModel implements UMLModelEntity{
	
	//TODO finish this
	private String name;
	private List<MethodModel> methods;
	


	public AnnotationModel(Class<?> annotation) {
		this.name = annotation.getName();
		methods = new Vector<MethodModel>();
		for (Method m : annotation.getMethods()) {
			methods.add(new MethodModel(m));
		}
	}

	@Override
	public XMLElement getElementModel(XMLElementGenerator generator) {
		return generator.generate(this);
	}

	@SimpleElement(value="name", order=1)
	public String getName() {
		return name;
	}
	
	@ComplexElement(value="methods", order=2)
	public List<MethodModel> getMethods() {
		return methods;
	}
}
