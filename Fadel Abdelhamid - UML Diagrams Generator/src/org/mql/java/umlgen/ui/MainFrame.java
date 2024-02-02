package org.mql.java.umlgen.ui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class MainFrame extends JFrame{
	
	private ImageIcon icon = new ImageIcon("resources/icons/‭icon.png");

	private static final long serialVersionUID = 1L;

	public MainFrame() {
		super("UML Diagram Generator - Home");
		setIconImage(icon.getImage());
		setContentPane(new HomePanel());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			System.err.println("Warning: Look and feel not applied.");
		}
		try {
			new MainFrame();
		} catch (Exception e) {
			e.printStackTrace();
			new ErrorDialog("Error: " + e.getMessage());
			System.exit(1);
		}
	}

}
