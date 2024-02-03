package org.mql.java.umlgen.examples;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.mql.java.umlgen.fakemodels.ExampleClassTest;
import org.mql.java.umlgen.fakemodels.ExampleInterfaceTest;
import org.mql.java.umlgen.models.ClassModel;
import org.mql.java.umlgen.models.InterfaceModel;
import org.mql.java.umlgen.models.MethodModel;
import org.mql.java.umlgen.models.ParameterModel;
import org.mql.java.umlgen.models.ProjectModel;
import org.mql.java.umlgen.ui.ClassDiagram;
import org.mql.java.umlgen.ui.ClassVisual;
import org.mql.java.umlgen.ui.InterfaceVisual;
import org.mql.java.umlgen.utils.UIUtils;
import org.mql.java.umlgen.xml.generators.CustomGenerator;
import org.mql.java.umlgen.xml.generators.DOMGenerator;
import org.mql.java.umlgen.xml.generators.XMLElement;
import org.mql.java.umlgen.xml.parsers.ModelsParser;

/**
 * Class to test examples and discovered techniques.
 */
public class Examples {

	public Examples() {
		exp11();
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
			ProjectModel project = new ProjectModel("C:/repos/Java_MQL/p02-Generics/bin");
			DOMGenerator generator = new DOMGenerator(project.getElementModel(new CustomGenerator()), "uml-diagram");
			generator.dump("resources/dump.xml");
			System.out.println("dumped");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exp06() {
		ClassModel classModel = new ClassModel(null, ExampleClassTest.class);
		ClassVisual classVisual = new ClassVisual(classModel);
		JFrame frame = new JFrame("Class Visualization");
		frame.setContentPane(classVisual);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 200);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void exp07() {
		InterfaceModel interf = new InterfaceModel(null, ExampleInterfaceTest.class);
		InterfaceVisual interfaceVisual = new InterfaceVisual(interf);
		JFrame frame = new JFrame("Class Visualization");
		frame.setContentPane(interfaceVisual);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 200);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void exp08() {
		System.out.println("exp05");
		exp05();
		System.out.println("exp07");
		ModelsParser parser = new ModelsParser();
		ProjectModel project = parser.parse("resources/dump.xml");
		System.out.println("parsed");
		DOMGenerator generator = new DOMGenerator(project.getElementModel(new CustomGenerator()), "uml-diagram");
		generator.dump("resources/redump.xml");
		System.out.println("dumped");
		
	}
	
	public void exp09() {
		JPanel panel = new JPanel(new GridLayout());
		JScrollPane scrollpane = new JScrollPane(panel);
		scrollpane.getVerticalScrollBar().setUnitIncrement(13);
		scrollpane.getHorizontalScrollBar().setUnitIncrement(13);
		scrollpane.setPreferredSize(new Dimension(800,500));
		JFrame frame = new JFrame("UITEST");
		panel.add(new ClassVisual(new ClassModel(null, String.class)));
		panel.add(new ClassVisual(new ClassModel(null, ArrayList.class)));
		panel.add(new InterfaceVisual(new InterfaceModel(null, List.class)));
		panel.repaint();
		frame.setContentPane(scrollpane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 200);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void exp10() {
		try {
			ProjectModel project = new ProjectModel("C:/repos/Java_MQL/p04-XML Parsers/bin");
			System.out.println(project.getProjectContext().getClassesInhertianceLevel());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void exp11() {
		try {
			ProjectModel project = new ProjectModel("C:/repos/java_problem_solving/p02-Generics/bin");
			ClassDiagram classDiag = new ClassDiagram(project);
			JScrollPane scrollpane = classDiag.asScrollPane(800, 600);
			JFrame frame = new JFrame("UITEST");
			frame.setContentPane(scrollpane);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(800, 600);
			frame.pack();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exp12() {
		JFrame frame = new JFrame("UITEST");
		class Arrow extends JPanel{
			private static final long serialVersionUID = 1L;
			public Arrow() {
				this.setPreferredSize(new Dimension(800, 600));
			}
			@Override
			protected void paintComponent(Graphics g) {
				UIUtils.drawArrow(g, 200, 200, 0, 0);
				UIUtils.drawArrow(g, 200, 200, 400, 400);
				UIUtils.drawArrow(g, 200, 200, 0, 400);
				UIUtils.drawArrow(g, 200, 200, 400, 0);
				UIUtils.drawArrow(g, 200, 200, 400/2, 400);
				UIUtils.drawArrow(g, 200, 200, 400, 400/2);
				UIUtils.drawArrow(g, 200, 200, 400/2, 0);
				UIUtils.drawArrow(g, 200, 200, 0, 400/2);
			}
		}
		Arrow panel = new Arrow();
		panel.setPreferredSize(new Dimension(800, 600));
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void exp13() {
		JFrame frame = new JFrame("UITEST");
		class Arrow extends JPanel{
			private static final long serialVersionUID = 1L;
			public Arrow() {
				this.setPreferredSize(new Dimension(800, 600));
			}
			@Override
			protected void paintComponent(Graphics g) {
				UIUtils.drawDashedArrow(g, 200, 200, 0, 0);
				UIUtils.drawDashedArrow(g, 200, 200, 400, 400);
				UIUtils.drawDashedArrow(g, 200, 200, 0, 400);
				UIUtils.drawDashedArrow(g, 200, 200, 400, 0);
				UIUtils.drawDashedArrow(g, 200, 200, 400/2, 400);
				UIUtils.drawDashedArrow(g, 200, 200, 400, 400/2);
				UIUtils.drawDashedArrow(g, 200, 200, 400/2, 0);
				UIUtils.drawDashedArrow(g, 200, 200, 0, 400/2);
			}
		}
		Arrow panel = new Arrow();
		panel.setPreferredSize(new Dimension(800, 600));
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Examples();
	}

}
