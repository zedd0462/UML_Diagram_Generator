package org.mql.java.umlgen.models;

import org.mql.java.umlgen.annotations.ComplexElement;
import org.mql.java.umlgen.annotations.SimpleElement;
import org.mql.java.umlgen.xml.XMLElement;
import org.mql.java.umlgen.xml.XMLElementGenerator;


@ComplexElement(value="relation")
public class RelationModel implements UMLModelEntity {
	private String sourceClass;
	private String targetClass;
	private String relationType;

	public RelationModel(String sourceClass, String targetClass, String relationType) {
		this.sourceClass = sourceClass;
		this.targetClass = targetClass;
		this.relationType = relationType;
	}

	public RelationModel(ClassModel sourceClass, ClassModel targetClass, String relationType) {
		this.sourceClass = sourceClass.getName();
		this.targetClass = targetClass.getName();
		this.relationType = relationType;
	}

	public RelationModel(ClassModel sourceClass, ClassModel targetClass) {
		this.sourceClass = sourceClass.getName();
		this.targetClass = targetClass.getName();
		this.relationType = "Unspecified";
	}

	@Override
	public XMLElement getElementModel(XMLElementGenerator generator) {
		return generator.generate(this);
	}

	@SimpleElement(value="source", order=1)
	public String getSourceClass() {
		return sourceClass;
	}

	@SimpleElement(value="target", order=2)
	public String getTargetClass() {
		return targetClass;
	}
	
	@SimpleElement(value="type", order=3)
	public String getRelationType() {
		return relationType;
	}

	
	

}
