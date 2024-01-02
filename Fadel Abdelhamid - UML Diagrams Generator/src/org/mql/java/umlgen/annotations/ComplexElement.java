package org.mql.java.umlgen.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents content that will be represented with an {@code XMLElement}
 * with complex content, the name of the tag will be in the attribute
 * {@code value}.
 * This annotation should be on a getter that returns a {@code List} of
 * {@code UMLModelEntity}, or on a type that implements {@code UMLModelEntity}.
 * The order of the elements in the list will be the order specified in the
 * attribute {@code order}.
 * Usually the content of the element will be wrapped in another element with
 * the name specified in the attribute {@code value}, if the {@code value} is
 * {@code -1} then there is no need to wrap it.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Inherited
public @interface ComplexElement {
	//-1 to indicate there is no need to wrap it
	String value() default "-1";
	int order() default 0;
}
