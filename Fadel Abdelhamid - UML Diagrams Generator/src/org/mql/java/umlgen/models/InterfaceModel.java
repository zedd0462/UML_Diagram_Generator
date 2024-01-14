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
	private ProjectContext projectContext;
	private String name;
	private Class<?> superclass;
	private List<MethodModel> methods;
	private List<FieldModel> fields;
	private List<RelationModel> relations;
	

	public InterfaceModel(ProjectContext projectContext, Class<?> interf) {
		this.projectContext = projectContext;
		initLists();
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
	
	
	
	public InterfaceModel(
			ProjectContext projectContext,
			String name,
			List<MethodModel> methods,
			List<FieldModel> fields,
			List<RelationModel> relations) {
		super();
		this.projectContext = projectContext;
		this.name = name;
		this.methods = methods;
		this.fields = fields;
		this.relations = relations;
	}

	

	public InterfaceModel(ProjectContext projectContext, String name) {
		super();
		this.projectContext = projectContext;
		this.name = name;
		initLists();
	}

	public void initLists() {
		methods = new Vector<MethodModel>();
		fields = new Vector<FieldModel>();
		relations = new Vector<RelationModel>();
	}
	
	public void addMethod(MethodModel method) {
		methods.add(method);
	}
	
	public void addField(FieldModel field) {
		fields.add(field);
	}
	
	public void addRelation(RelationModel relation) {
		relations.add(relation);
		projectContext.addRelation(relation);
	}

	public void resolveRelations() {
		if(superclass != null) {
			RelationModel newRelation = new RelationModel(this, projectContext.getLoadedRelationEntity(superclass.getName()), 3);
			relations.add(newRelation);
			projectContext.addRelation(newRelation);
		}
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
	
	@ComplexElement(value="fields", order=2)
	public List<FieldModel> getFields() {
		return fields;
	}
	
	@ComplexElement(value="methods", order=3)
	public List<MethodModel> getMethods() {
		return methods;
	}
	
	

}
