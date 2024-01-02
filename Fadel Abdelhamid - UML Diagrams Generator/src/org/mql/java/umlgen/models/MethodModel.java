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
	private List<ModifierModel> modifiers;
	private String returnType;
	private String name;

	public MethodModel(Method method) {
		this.parameters = new Vector<ParameterModel>();
		this.modifiers = new Vector<ModifierModel>();		
		this.name = method.getName();
		this.returnType = method.getReturnType().getName();
		for (Parameter p : method.getParameters()) {
			parameters.add(new ParameterModel(p));
		}
		int m = method.getModifiers();
		modifiers.addAll(ModifierModel.getModifiers(m));
	}

	@Override
	public XMLElement getElementModel(XMLElementGenerator generator) {
		return generator.generate(this);
	}

	@ComplexElement(value="parametres", order=3)
	public List<ParameterModel> getParameters() {
		return parameters;
	}

	@ComplexElement(value="modifiers", order=4)
	public List<ModifierModel> getModifiers() {
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
	
}
