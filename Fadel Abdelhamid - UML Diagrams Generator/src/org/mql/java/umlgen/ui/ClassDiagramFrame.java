package org.mql.java.umlgen.ui;

import javax.swing.JFrame;

import org.mql.java.umlgen.models.ProjectModel;

public class ClassDiagramFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private ClassDiagram classdiagram;

	public ClassDiagramFrame(ProjectModel project) {
		super("UML Diagram Generator - ClassDiagram");
		classdiagram = new ClassDiagram(project, true);
		this.setContentPane(classdiagram.asScrollPane(700, 500));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

}
