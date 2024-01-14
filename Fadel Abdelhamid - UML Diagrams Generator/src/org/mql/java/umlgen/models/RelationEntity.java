package org.mql.java.umlgen.models;

/**
 * Represents an {@code UMLModelEntity} that can be represented in a Relation.
 */
public interface RelationEntity extends UMLModelEntity{
	public String getName();
	//public Class<?> getReflectClass();
	public void resolveRelations();
}
