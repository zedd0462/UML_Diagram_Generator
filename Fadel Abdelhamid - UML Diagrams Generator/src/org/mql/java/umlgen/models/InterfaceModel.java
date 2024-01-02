package org.mql.java.umlgen.models;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;

import org.mql.java.umlgen.annotations.ComplexElement;
import org.mql.java.umlgen.annotations.SimpleElement;
import org.mql.java.umlgen.xml.XMLElement;
import org.mql.java.umlgen.xml.XMLElementGenerator;

@ComplexElement(value="interface")
public class InterfaceModel implements UMLModelEntity{
	
	//TODO interfaceModel not done
	private String name;
	private String superclass;
	private List<MethodModel> methods;
	private List<FieldModel> fields;
	

	public InterfaceModel(Class<?> interf) {
		methods = new Vector<MethodModel>();
		fields = new Vector<FieldModel>();
		this.name = interf.getName();
		Class<?>[] tmp = interf.getInterfaces();
		if(tmp.length > 0) {
			this.superclass = tmp[0].getName();
		}else {
			this.superclass = "";
		}
		for (Field f : interf.getDeclaredFields()) {
			fields.add(new FieldModel(f));
		}
		for (Method m : interf.getDeclaredMethods()) {
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
	
	@SimpleElement(value="superclass", order=2)
	public String getSuperclass() {
		return superclass;
	}
	
	@ComplexElement(value="fields", order=3)
	public List<FieldModel> getFields() {
		return fields;
	}
	
	@ComplexElement(value="methods", order=4)
	public List<MethodModel> getMethods() {
		return methods;
	}

}
