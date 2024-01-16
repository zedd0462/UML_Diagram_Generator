package org.mql.java.umlgen.xml.generators;

import org.mql.java.umlgen.models.Model;

/**
 * An interface that provides generator of XMLElement from an UMLModelEntity.
 */
public interface XMLElementGenerator {
	/**
	 * Generates an  {@code XMLElement} from an {@code UMLModelEntity}
	 * @param model The UMLModelEntity to generate XML from.
	 * @return Returns the generated XMLElement element.
	 */
	public XMLElement generate(Model model);
}
