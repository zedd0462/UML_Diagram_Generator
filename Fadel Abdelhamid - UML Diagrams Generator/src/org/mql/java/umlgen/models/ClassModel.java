package org.mql.java.umlgen.models;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;

import org.mql.java.umlgen.annotations.ComplexElement;
import org.mql.java.umlgen.annotations.SimpleElement;
import org.mql.java.umlgen.xml.XMLElement;
import org.mql.java.umlgen.xml.XMLElementGenerator;

@ComplexElement(value="class")
public class ClassModel implements UMLModelEntity{
	
	
	//TODO: not complete
	private String name;
	private String superClass;
	private List<ModifierModel> modifiers;
	private List<ConstructorModel> constructors;
	private List<FieldModel> fields;
	private List<MethodModel> methods;
	private List<RelationModel> relations;

	//TODO: create relations
	public ClassModel(Class<?> clazz) {
		modifiers = new Vector<ModifierModel>();
		constructors = new Vector<ConstructorModel>();
		fields = new Vector<FieldModel>();
		methods = new Vector<MethodModel>();
		relations = new Vector<RelationModel>();
		
		this.name = clazz.getName();
		this.superClass = clazz.getSuperclass().getName();

		modifiers.addAll(ModifierModel.getModifiers(clazz.getModifiers()));
		for (Constructor<?> c : clazz.getConstructors()) {
			constructors.add(new ConstructorModel(c));
		}
		for (Field f : clazz.getDeclaredFields()) {
			fields.add(new FieldModel(f));
		}
		for (Method m : clazz.getDeclaredMethods()) {
			methods.add(new MethodModel(m));
		}
		
	}

	@Override
	public XMLElement getElementModel(XMLElementGenerator generator) {
		return generator.generate(this);
	}

	@SimpleElement(value="name", order=0)
	public String getName() {
		return name;
	}
	
	@SimpleElement(value="superclass", order=1)
	public String getSuperClass() {
		return superClass;
	}

	@ComplexElement(value="modifiers", order=2)
	public List<ModifierModel> getModifiers() {
		return modifiers;
	}
	
	@ComplexElement(value="constructors", order=3)
	public List<ConstructorModel> getConstructors() {
		return constructors;
	}

	@ComplexElement(value="fields", order=4)
	public List<FieldModel> getFields() {
		return fields;
	}

	@ComplexElement(value="methods", order=5)
	public List<MethodModel> getMethods() {
		return methods;
	}
	
	

}
