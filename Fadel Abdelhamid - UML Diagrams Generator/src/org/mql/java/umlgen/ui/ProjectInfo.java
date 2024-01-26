package org.mql.java.umlgen.ui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.mql.java.umlgen.models.ProjectModel;

public class ProjectInfo extends JPanel{

	private static final long serialVersionUID = 1L;
	
	
	
	private ProjectModel project;
	
	private TitledBorder titledBorder;
	private LayoutManager layout;
	
	private JPanel labelContainer;
	
	private JLabel projectName;
	private JLabel projectPath;
	private JLabel totalEntities;
	private JLabel totalPackages;
	
	public ProjectInfo() {
		
		titledBorder = new TitledBorder(new EtchedBorder(), "Loaded Project:");
		titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.ITALIC + Font.BOLD));
		layout = new FlowLayout(FlowLayout.LEFT);
		projectName = new JLabel("Name : ");
		projectPath = new JLabel("Path : ");
		totalEntities = new JLabel("Number of entities : ");
		totalPackages = new JLabel("Number of packages : ");
		labelContainer = new JPanel();
		labelContainer.setLayout(new BoxLayout(labelContainer, BoxLayout.PAGE_AXIS));
		labelContainer.add(projectName);
		labelContainer.add(projectPath);
		labelContainer.add(totalEntities);
		labelContainer.add(totalPackages);
		setLayout(layout);
		setBorder(titledBorder);
		add(labelContainer);
	}
	
	public void updateProject(ProjectModel projectModel) {
		this.project = projectModel;
		String name = project.getName();
		String path = project.getPath();
		String entites = String.valueOf(project.getProjectContext().getEntitesCount());
		String packages = String.valueOf(project.getProjectContext().getPackagesCount());
		projectName.setText("Name : " + name);
		projectPath.setText("Path : " + path);
		totalEntities.setText("Number of entities : " + entites);
		totalPackages.setText("Number of packages : " + packages);
	}
	
	
}
