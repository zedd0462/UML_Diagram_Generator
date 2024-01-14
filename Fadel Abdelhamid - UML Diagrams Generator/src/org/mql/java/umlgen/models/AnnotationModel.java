package org.mql.java.umlgen.models;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;

import org.mql.java.umlgen.annotations.ComplexElement;
import org.mql.java.umlgen.annotations.SimpleElement;
import org.mql.java.umlgen.xml.XMLElement;
import org.mql.java.umlgen.xml.XMLElementGenerator;

@ComplexElement(value="annotation")
public class AnnotationModel implements RelationEntity{
	
	//TODO finish this
	private String name;
	private String simpleName;
	private List<MethodModel> methods;

	public AnnotationModel(Class<?> annotation) {
		this.name = annotation.getName();
		this.simpleName = annotation.getSimpleName();
		methods = new Vector<MethodModel>();
		for (Method m : annotation.getMethods()) {
			methods.add(new MethodModel(m));
		}
	}
	
	
	
	public AnnotationModel(String name, String simpleName, List<MethodModel> methods) {
		super();
		this.name = name;
		this.simpleName = simpleName;
		this.methods = methods;
	}
	
	public AnnotationModel(String name, String simpleName) {
		super();
		this.name = name;
		this.simpleName = simpleName;
		this.methods = new Vector<MethodModel>();
	}
	
	public void addMethod(MethodModel method){
		methods.add(method);
	}

	public void resolveRelations() {
		//TODO implement relations in annotations
		
	}

	@Override
	public XMLElement getElementModel(XMLElementGenerator generator) {
		return generator.generate(this);
	}
	
	@Override
	@SimpleElement(value="name", order=0)
	public String getName() {
		return name;
	}
	
	@SimpleElement(value="simple-name", order=1)
	public String getSimpleName() {
		return simpleName;
	}
	
	@ComplexElement(value="methods", order=2)
	public List<MethodModel> getMethods() {
		return methods;
	}
	
	
}
