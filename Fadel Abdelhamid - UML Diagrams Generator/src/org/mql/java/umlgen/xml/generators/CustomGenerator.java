package org.mql.java.umlgen.xml.generators;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.mql.java.umlgen.annotations.ComplexElement;
import org.mql.java.umlgen.annotations.SimpleElement;
import org.mql.java.umlgen.models.Model;

public class CustomGenerator implements XMLElementGenerator {

	public CustomGenerator() {
		
	}

	@Override
	public XMLElement generate(Model model) {
		try {
			String title = model.getClass().getDeclaredAnnotation(ComplexElement.class).value();
			XMLElement element = new XMLElement(title);
			Method[] methods = model.getClass().getDeclaredMethods();
			Arrays.sort(methods, Comparator.comparingInt(method -> {
                SimpleElement se = method.getDeclaredAnnotation(SimpleElement.class);
                ComplexElement ce = method.getDeclaredAnnotation(ComplexElement.class);
                if (se != null) {
                    return se.order();
                } else if (ce != null) {
                    return ce.order();
                }
                return 0; // Default order if no annotation is present
            }));
			for (Method method : methods) {
				SimpleElement se = method.getDeclaredAnnotation(SimpleElement.class);
				ComplexElement ce = method.getDeclaredAnnotation(ComplexElement.class);
				if(se != null) {
					XMLElement subElement = new XMLElement(se.value());
					String textContent = method.invoke(model, new Object[]{}).toString();
					XMLSimpleContent subSubElement = new XMLSimpleContent(textContent);
					subElement.addChildren(subSubElement);
					element.addChildren(subElement);
				}
				if(ce != null) {
					XMLElement subElement;
					if(ce.value().equals("-1")) {
						Model m = (Model) method.invoke(model, new Object[]{});
						subElement = m.getElementModel(this);
						element.addChildren(subElement);
					}else {
						subElement = new XMLElement(ce.value());
						@SuppressWarnings("unchecked")
						List<? extends Model> subSubElements = (List<? extends Model>) method.invoke(model, new Object[]{});
						for (Model m : subSubElements) {
							subElement.addChildren(m.getElementModel(this));
						}
						element.addChildren(subElement);
					}
				}
			}
			return element;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
