package org.mql.java.umlgen.ui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.mql.java.umlgen.models.ProjectModel;

public class ProjectPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	
	
	private ProjectModel project;
	
	private TitledBorder titledBorder;
	private LayoutManager layout;
	
	private JPanel labelContainer;
	private JPanel buttonContainer;
	
	private JLabel projectName;
	private JLabel projectPath;
	private JLabel totalEntities;
	private JLabel totalPackages;
	
	private JButton classDiagramButton;
	private JButton packageDiagramButton;
	private JButton saveButton;
	private ActionListener classDiagramButtonListener;
	private ActionListener packageDiagramButtonListener;
	private ActionListener saveButtonListener;
	
	public ProjectPanel() {
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
		classDiagramButton = new JButton("Class Diagram");
		packageDiagramButton = new JButton("Package Diagram");
		saveButton = new JButton("Save as XML");
		initListeners();
		classDiagramButton.addActionListener(classDiagramButtonListener);
		packageDiagramButton.addActionListener(packageDiagramButtonListener);
		saveButton.addActionListener(saveButtonListener);
		classDiagramButton.setEnabled(false);
		packageDiagramButton.setEnabled(false);
		saveButton.setEnabled(false);
		buttonContainer = new JPanel();
		buttonContainer.setLayout(new GridBagLayout());
		buttonContainer.add(classDiagramButton);
		buttonContainer.add(packageDiagramButton);
		buttonContainer.add(saveButton);
		setLayout(layout);
		setBorder(titledBorder);
		add(labelContainer);
		add(buttonContainer);
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
		enableButtons();
	}

	private void enableButtons() {
		classDiagramButton.setEnabled(true);
		packageDiagramButton.setEnabled(true);
		saveButton.addActionListener(new SaveListener(project));
		saveButton.setEnabled(true);
	}
	
	private void initListeners() {
		classDiagramButtonListener = e -> {
			if(project == null) {
				new InfoDialog("Please load a project first!");
				return;
			}
			new ClassDiagramFrame(project);
		};
		packageDiagramButtonListener = e -> {
			if(project == null) {
				new InfoDialog("Please load a project first!");
				return;
			}
			new InfoDialog("Not Implemented Yet !");
		};
	}
	
	
}
