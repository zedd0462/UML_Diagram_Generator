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
	private ProjectContext projectContext;
	private List<ModifierModel> modifiers;
	private List<ConstructorModel> constructors;
	private List<FieldModel> fields;
	private List<MethodModel> methods;
	private List<RelationModel> relations;
	private Class<?> reflectClass;
	
	/*
	 * To figure out relations we need all class models to
	 * be already loaded, so we will delay the treatement.
	 * Maybe its not the best way to this.
	 */
	private List<Field> pendingFields;

	//TODO: finish relations implementation
	public ClassModel(ProjectContext context,Class<?> clazz) {
		this.projectContext = context;
		this.reflectClass = clazz;
		modifiers = new Vector<ModifierModel>();
		constructors = new Vector<ConstructorModel>();
		fields = new Vector<FieldModel>();
		methods = new Vector<MethodModel>();
		relations = new Vector<RelationModel>();
		pendingFields = new Vector<Field>();
		
		
		
		this.name = clazz.getName();
		this.superClass = clazz.getSuperclass().getName();

		modifiers.addAll(ModifierModel.getModifiers(clazz.getModifiers()));
		for (Constructor<?> c : clazz.getConstructors()) {
			constructors.add(new ConstructorModel(c));
		}
		for (Field f : clazz.getDeclaredFields()) {
			Class<?> fieldType = f.getType();
			if(fieldType.isPrimitive()) {
				fields.add(new FieldModel(f));
			} else {
				pendingFields.add(f);
			}
		}
		for (Method m : clazz.getDeclaredMethods()) {
			methods.add(new MethodModel(m));
		}
		
	}

	public void resolveRelations() {
		for (Field f : pendingFields) {
			Class<?> fieldType = f.getType();
			//TODO: add support for collections.
			if(projectContext.isLoaded(fieldType)) {
				RelationModel newRelation = new RelationModel(this, projectContext.getLoadedClassModel(fieldType));
				relations.add(newRelation);
				projectContext.addRelation(newRelation);
			} else {
				fields.add(new FieldModel(f));
			}
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
	
	@ComplexElement(value="relations", order=6)
	public List<RelationModel> getRelations() {
		return relations;
	}
	
	public Class<?> getReflectClass() {
		return reflectClass;
	}
	
	

}
