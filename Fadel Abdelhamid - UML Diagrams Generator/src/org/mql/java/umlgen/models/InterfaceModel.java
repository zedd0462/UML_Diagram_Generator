package org.mql.java.umlgen.models;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;

import org.mql.java.umlgen.annotations.ComplexElement;
import org.mql.java.umlgen.annotations.SimpleElement;
import org.mql.java.umlgen.xml.generators.XMLElement;
import org.mql.java.umlgen.xml.generators.XMLElementGenerator;

@ComplexElement(value="interface")
public class InterfaceModel implements Entity {
	private ProjectContext projectContext;
	private String name;
	private int modifiers;
	private Class<?> superclass;
	private List<MethodModel> methods;
	private List<FieldModel> fields;
	private List<RelationModel> relations;
	

	public InterfaceModel(ProjectContext projectContext, Class<?> interf) {
		this.projectContext = projectContext;
		initLists();
		this.name = interf.getName();
		this.modifiers = interf.getModifiers();
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
			int modifiers,
			List<MethodModel> methods,
			List<FieldModel> fields,
			List<RelationModel> relations) {
		super();
		this.projectContext = projectContext;
		this.name = name;
		this.methods = methods;
		this.fields = fields;
		this.relations = relations;
		this.modifiers = modifiers;
	}

	

	public InterfaceModel(ProjectContext projectContext, String name, int modifiers) {
		super();
		this.projectContext = projectContext;
		this.name = name;
		this.modifiers = modifiers;
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
		if(superclass != null && projectContext.isLoaded(superclass.getName())) {
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
	
	@SimpleElement(value="modifiers", order=1)
	public int getModifiers() {
		return modifiers;
	}
	
	@ComplexElement(value="fields", order=2)
	public List<FieldModel> getFields() {
		return fields;
	}
	
	@ComplexElement(value="methods", order=3)
	public List<MethodModel> getMethods() {
		return methods;
	}
	
	@ComplexElement(value="relations", order=4)
	public List<RelationModel> getRelations() {
		return relations;
	}
	
	

}
