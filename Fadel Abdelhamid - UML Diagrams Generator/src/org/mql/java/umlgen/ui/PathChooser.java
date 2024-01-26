package org.mql.java.umlgen.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

public class PathChooser extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JTextField textField;
	private JButton browseButton;
	
	private JFileChooser chooser;

	public PathChooser() {
		setLayout(new FlowLayout());
		File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
		chooser = new JFileChooser(homeDirectory);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		textField = new JTextField();
		browseButton = new JButton("Browse");
		browseButton.addActionListener((e)->{
			int res = chooser.showOpenDialog(null);
			if (res == JFileChooser.APPROVE_OPTION) {
			      File file = chooser.getSelectedFile();
			      textField.setText(file.getAbsolutePath());
			}
		});
		textField.setPreferredSize(new Dimension(200, browseButton.getPreferredSize().height));
		textField.setBorder(new RoundedCornerBorder());
		textField.setBackground(getBackground());
		textField.setEnabled(false);
		add(textField);
		add(browseButton);
	}
	
	@Override
	public void addNotify() {
		setPreferredSize(new Dimension(getParent().getPreferredSize().width, (int) (browseButton.getPreferredSize().height * 1.70)));
		setMaximumSize(getPreferredSize());
		super.addNotify();
	}
	
	public String getInput() {
		return textField.getText();
	}
	

}
