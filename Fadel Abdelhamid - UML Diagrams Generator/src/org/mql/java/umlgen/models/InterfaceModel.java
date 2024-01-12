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
public class InterfaceModel implements RelationEntity {
	
	//TODO interfaceModel not done
	ProjectContext projectContext;
	private String name;
	private Class<?> superclass;
	private List<MethodModel> methods;
	private List<FieldModel> fields;
	private List<RelationModel> relations;
	private Class<?> reflectClass;
	

	public InterfaceModel(ProjectContext projectContext, Class<?> interf) {
		this.reflectClass = interf;
		this.projectContext = projectContext;
		methods = new Vector<MethodModel>();
		fields = new Vector<FieldModel>();
		relations = new Vector<RelationModel>();
		this.name = interf.getName();
		Class<?>[] tmp = interf.getInterfaces();
		if(tmp.length > 0) {
			this.superclass = tmp[0];
		}else {
			this.superclass = null;
		}
		for (Field f : interf.getDeclaredFields()) {
			fields.add(new FieldModel(f));
		}
		for (Method m : interf.getDeclaredMethods()) {
			methods.add(new MethodModel(m));
		}
	}
	
	public void resolveRelations() {
		if(superclass != null) {
			RelationModel newRelation = new RelationModel(this, projectContext.getLoadedRelationEntity(superclass), 3);
			relations.add(newRelation);
			projectContext.addRelation(newRelation);
		}
	}

	@Override
	public XMLElement getElementModel(XMLElementGenerator generator) {
		return generator.generate(this);
	}
	
	@Override
	public Class<?> getReflectClass() {
		return reflectClass;
	}
	
	@Override
	@SimpleElement(value="name", order=1)
	public String getName() {
		return name;
	}
	
	@ComplexElement(value="fields", order=2)
	public List<FieldModel> getFields() {
		return fields;
	}
	
	@ComplexElement(value="methods", order=3)
	public List<MethodModel> getMethods() {
		return methods;
	}
	
	

}
