package org.mql.java.umlgen.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import org.mql.java.umlgen.models.ProjectModel;
import org.mql.java.umlgen.xml.generators.CustomGenerator;
import org.mql.java.umlgen.xml.generators.DOMGenerator;

public class SaveListener implements ActionListener{
	
	private JFileChooser chooser;
	private String savePath = null;
	private ProjectModel project;

	public SaveListener(ProjectModel model) {
		this.project = model;
		File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
		chooser = new JFileChooser(homeDirectory);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			int res = chooser.showSaveDialog(null);
			if (res == JFileChooser.APPROVE_OPTION) {
				savePath = chooser.getSelectedFile().getAbsolutePath();
				DOMGenerator generator = new DOMGenerator(project.getElementModel(new CustomGenerator()), "uml-diagram");
				generator.dump(savePath);
				new InfoDialog("File saved successfully !");
			} else if (res == JFileChooser.CANCEL_OPTION) {
				new InfoDialog("Operation cancelled.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			new ErrorDialog(ex.getMessage());
		}
	}

}
