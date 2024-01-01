package org.mql.java.umlgen.xml;

import java.lang.reflect.Method;

import org.mql.java.umlgen.annotations.ComplexElement;
import org.mql.java.umlgen.annotations.SimpleElement;
import org.mql.java.umlgen.models.UMLModelEntity;

/**
 * A Producer class to produce XMLElement from an 
 * UMLModelEntity using custom XML syntax.
 */
public class CustomGenerator implements XMLElementGenerator {

	public CustomGenerator() {
		
	}

	@Override
	public XMLElement generate(UMLModelEntity model) {
		try {
			String title = model.getClass().getDeclaredAnnotation(ComplexElement.class).value();
			XMLElement element = new XMLElement(title);
			Method[] methods = model.getClass().getDeclaredMethods();
			for (Method method : methods) {
				SimpleElement se = method.getDeclaredAnnotation(SimpleElement.class);
				ComplexElement ce = method.getDeclaredAnnotation(ComplexElement.class);
				if(se != null) {
					XMLElement subElement = new XMLElement(se.value());
					XMLSimpleContent subSubElement = new XMLSimpleContent((String)method.invoke(model, new Object[]{}));
					subElement.addChildren(subSubElement);
					element.addChildren(subElement);
				}
				if(ce != null) {
					//TODO: stopped here
				}
			}
			return element;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
