package org.mql.java.umlgen.models;

import org.mql.java.umlgen.xml.generators.XMLElement;
import org.mql.java.umlgen.xml.generators.XMLElementGenerator;

/**
 * Represents an entity in an UML model.
 */
public interface Model {
	public XMLElement getElementModel(XMLElementGenerator generator);
}
