package org.mql.java.umlgen.models;

import org.mql.java.umlgen.xml.XMLElement;
import org.mql.java.umlgen.xml.XMLElementGenerator;

public class ClassModel implements UMLModelEntity{
	
	//private AnnotationModel[] annotations;
	private ConstructorModel[] constructors;
	private FieldModel[] fields;
	private MethodModel[] model;
	private RelationModel[] relations;

	public ClassModel(ProjectModel project, Class<?> clazz) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public XMLElement getElementModel(XMLElementGenerator producer) {
		// TODO Auto-generated method stub
		return null;
	}

}
