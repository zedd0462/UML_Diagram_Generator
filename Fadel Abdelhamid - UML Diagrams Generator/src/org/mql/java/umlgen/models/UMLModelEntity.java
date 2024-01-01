package org.mql.java.umlgen.models;

import org.mql.java.umlgen.xml.XMLElement;
import org.mql.java.umlgen.xml.XMLElementGenerator;

/**
 * Represent an entity in an UML model.
 */
public interface UMLModelEntity {
	public XMLElement getElementModel(XMLElementGenerator generator);
}
