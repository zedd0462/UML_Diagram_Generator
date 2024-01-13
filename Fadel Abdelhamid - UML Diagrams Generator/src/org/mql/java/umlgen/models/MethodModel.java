package org.mql.java.umlgen.models;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Vector;

import org.mql.java.umlgen.annotations.ComplexElement;
import org.mql.java.umlgen.annotations.SimpleElement;
import org.mql.java.umlgen.xml.XMLElement;
import org.mql.java.umlgen.xml.XMLElementGenerator;

@ComplexElement("method")
public class MethodModel implements UMLModelEntity{
	
	//private AnnotationModel[] annotations;
	private List<ParameterModel> parameters;
	private int modifiers;
	private String returnType;
	private String name;
	private Method reflectMethod;

	public MethodModel(Method method) {
		reflectMethod = method;
		parameters = new Vector<ParameterModel>();
		modifiers = method.getModifiers();		
		name = method.getName();
		this.returnType = method.getReturnType().getName();
		for (Parameter p : method.getParameters()) {
			parameters.add(new ParameterModel(p));
		}
		int m = method.getModifiers();
	}

	@Override
	public XMLElement getElementModel(XMLElementGenerator generator) {
		return generator.generate(this);
	}

	@ComplexElement(value="parametres", order=3)
	public List<ParameterModel> getParameters() {
		return parameters;
	}

	@SimpleElement(value="modifiers", order=4)
	public int getModifiers() {
		return modifiers;
	}
	
	@SimpleElement(value="name", order=1)
	public String getName() {
		return name;
	}
	
	@SimpleElement(value="return", order=2)
	public String getReturnType() {
		return returnType;
	}
	
	public Method getReflectMethod() {
		return reflectMethod;
	}
	
}
