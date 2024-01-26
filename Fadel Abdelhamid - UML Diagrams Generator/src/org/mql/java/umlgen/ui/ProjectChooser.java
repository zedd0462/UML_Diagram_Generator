package org.mql.java.umlgen.ui;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ProjectChooser extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	
	private JButton loadButton;
	private LayoutManager layout;
	private PathChooser pathChooser;
	
	private JPanel container;

	public ProjectChooser(ActionListener listener) {
		loadButton = new JButton("Load");
		loadButton.addActionListener(listener);
		loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		pathChooser = new PathChooser();
		pathChooser.setAlignmentX(Component.CENTER_ALIGNMENT);
		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.add(pathChooser);
		container.add(loadButton);
		layout = new GridBagLayout();
		setLayout(layout);
		add(container);
	}
	
	public String getPath() {
		return pathChooser.getInput();
	}

}
