package org.mql.java.umlgen.examples;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

import org.mql.java.umlgen.models.MethodModel;
import org.mql.java.umlgen.models.ParameterModel;
import org.mql.java.umlgen.models.ProjectModel;
import org.mql.java.umlgen.xml.CustomGenerator;
import org.mql.java.umlgen.xml.DOMGenerator;
import org.mql.java.umlgen.xml.XMLElement;

/**
 * Class to test examples and discovered techniques.
 */
public class Examples {

	public Examples() {
		exp05();
	}
	
	class testClass {
		public void myMethod(String param1, int param2, Integer param3) {
			return;
		}
	}
	
	class testClass2 {
		
		private int d;
		public String s = "s";
		
		public testClass2() {
			// do ntng
		}
		
		public testClass2(int t) {
			
		}
		
		public void myMethod(String param1, int param2, Integer param3) {
			return;
		}
		
		public int getD() {
			return d;
		}
		
	}
	
	public void exp02() {
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
	
	public void exp03(){
		Method m = testClass.class.getDeclaredMethods()[0];
		DOMGenerator domGen = new DOMGenerator();
		domGen.generateDoc(new MethodModel(m).getElementModel(new CustomGenerator()), "test");
		domGen.dump("resources/dump.xml");
		
	}
	
	/*
	 //OLD EXAMPLE, THINGS HAVE CHANGED 
	 public void exp04() {
		Class<?> clazz = testClass2.class;
		DOMGenerator domGen = new DOMGenerator();
		domGen.generateDoc(new ClassModel(clazz).getElementModel(new CustomGenerator()));
		domGen.dump("resources/dump.xml");
		System.out.println("dumped");
	}
	*/
	
	public void exp05() {
		try {
			ProjectModel project = new ProjectModel("C:/repos/Java_MQL/p05-MultiThreading/bin");
			DOMGenerator generator = new DOMGenerator(project.getElementModel(new CustomGenerator()), "uml-diagram");
			generator.dump("resources/dump.xml");
			System.out.println("dumped");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Examples();
	}

}
