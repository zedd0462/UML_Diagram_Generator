package org.mql.java.umlgen;

import java.lang.reflect.Parameter;
import java.util.List;

import org.mql.java.umlgen.models.ParameterModel;
import org.mql.java.umlgen.xml.CustomGenerator;
import org.mql.java.umlgen.xml.XMLElement;

/**
 * Class to test examples and discovered techniques.
 */
public class Examples {

	public Examples() {
		exp02();
	}

	public void exp01() {
		try {
			ProjectExplorer explorer = new ProjectExplorer("C:/Users/Abdelhamid/git/UMLGen/Fadel Abdelhamid - UML Diagrams Generator/bin");
			List<Class<?>> classList = explorer.getLoadedClasses();
			System.out.println("--Classes----------------------");
			for (Class<?> clazz : classList) {
				System.out.println(clazz.getName());
			}
			List<Package> packageList = explorer.getLoadedPackages();
			System.out.println("--Packages---------------------");
			for (Package pkg : packageList) {
				System.out.println(pkg.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exp02() {
		class testClass {
			@SuppressWarnings("unused")//used with introspection
			public void myMethod(String param1, int param2, Integer param3) {
				return;
			}
		}
		Parameter[] params = testClass.class.getDeclaredMethods()[0].getParameters();
		for (Parameter parameter : params) {
			ParameterModel param = new ParameterModel(parameter);
			XMLElement element = param.getElementModel(new CustomGenerator());
			System.out.println("<" + element.getElementName() + ">");
			for (XMLElement subElement : element.getChildren()) {
				System.out.print("	"+"<" + subElement.getElementName() + ">");
				for (XMLElement subsubelement : subElement.getChildren()) {
					System.out.print(subsubelement.getContent());
				}
				System.out.println("</" + subElement.getElementName() + ">");
			}
			System.out.println("</" + element.getElementName() + ">");
		}
	}
	
	public static void main(String[] args) {
		new Examples();
	}

}
