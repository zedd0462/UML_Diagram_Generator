package org.mql.java.umlgen.models;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;

import org.mql.java.umlgen.annotations.ComplexElement;
import org.mql.java.umlgen.annotations.SimpleElement;
import org.mql.java.umlgen.xml.generators.XMLElement;
import org.mql.java.umlgen.xml.generators.XMLElementGenerator;

@ComplexElement(value="annotation")
public class AnnotationModel implements Entity{
	
	//TODO finish this
	ProjectContext projectContext;
	private String name;
	int modifiers;
	private List<MethodModel> methods;
	private List<RelationModel> relations;

	public AnnotationModel(ProjectContext projectContext, Class<?> annotation) {
		this.projectContext = projectContext;
		name = annotation.getName();
		modifiers = annotation.getModifiers();
		methods = new Vector<MethodModel>();
		for (Method m : annotation.getMethods()) {
			methods.add(new MethodModel(m));
		}
		relations = new Vector<RelationModel>();
	}
	
	
	
	public AnnotationModel(ProjectContext projectContext, String name, int modifiers, List<MethodModel> methods, List<RelationModel> relations) {
		super();
		this.projectContext = projectContext;
		this.name = name;
		this.modifiers = modifiers;
		this.methods = methods;
		this.relations = relations;

	}
	
	public AnnotationModel(ProjectContext projectContext, String name, int modifiers) {
		super();
		this.projectContext = projectContext;
		this.name = name;
		this.modifiers = modifiers;
		this.methods = new Vector<MethodModel>();
		this.relations = new Vector<RelationModel>();
	}
	
	public void addMethod(MethodModel method){
		methods.add(method);
	}
	
	public void addRelation(RelationModel relation) {
		relations.add(relation);
		projectContext.addRelation(relation);
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
	
	@ComplexElement(value="methods", order=1)
	public List<MethodModel> getMethods() {
		return methods;
	}
	
	@ComplexElement(value="relations", order=2)
	public List<RelationModel> getRelations() {
		return relations;
	}
	
	
}
