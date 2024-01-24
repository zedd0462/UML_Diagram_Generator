package org.mql.java.umlgen.ui;

import static java.lang.Math.max;
import static org.mql.java.umlgen.utils.MathUtils.max;
import static org.mql.java.umlgen.utils.MathUtils.sum;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.mql.java.umlgen.models.ClassModel;
import org.mql.java.umlgen.models.ProjectContext;
import org.mql.java.umlgen.models.ProjectModel;
import org.mql.java.umlgen.utils.UIUtils;

public class ClassDiagram extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private static final int DEFAULT_HEIGHT = 600;
	private static final int DEFAULT_WIDTH = 800;
	private static final int MARGIN = 50;
	private static final int SPACER_X = 50;
	private static final int SPACER_Y = 200;
	private int extraSpacer;
	private int height;
	private int width;
	private int currentPrintingX;
	private int currentPrintingY;
	
	private int lines;
	private int currentLevel;
	
	private ProjectModel project;
	private ProjectContext projectContext;
	
	private Collection<ClassModel> projectClasses;
	private List<List<ClassVisual>> classes;
	private Map<Integer, Integer> linesHeight;
	private Map<String, int[]> entitiesCoordinates;

	public ClassDiagram(ProjectModel projectModel) {
		project = projectModel;
		projectContext = project.getProjectContext();
		currentLevel = 0;
		initLists();
		height = MARGIN * 2;
		width = MARGIN * 2;
		fillEntites();
		initHeight();
		if(width < DEFAULT_WIDTH) width = DEFAULT_WIDTH;
		if(height < DEFAULT_HEIGHT) height = DEFAULT_HEIGHT;
		currentPrintingX = MARGIN;
		currentPrintingY = MARGIN;
	}
	
	private void initLists() {
		classes = new Vector<List<ClassVisual>>();
		projectClasses = projectContext.getLoadedClasses().values();
		linesHeight = new Hashtable<Integer, Integer>();
		entitiesCoordinates = new Hashtable<String, int[]>();
		lines = max(projectContext.getClassesInhertianceLevel().values());
		for(int i = 0; i <= lines; i++) {
			classes.add(new Vector<ClassVisual>());
			linesHeight.put(i, 0);
		}
	}
	
	private void fillEntites() {
		for (ClassModel classModel : projectClasses) {
			ClassVisual v = new ClassVisual(classModel);
			int inhertianceLevel = projectContext.getClassInheritanceLevel(classModel);
			classes.get(inhertianceLevel).add(v);
			linesHeight.put(inhertianceLevel, max(v.getPreferredSize().height, linesHeight.get(inhertianceLevel)));
			width += v.getPreferredSize().width + SPACER_X;
		}
	}
	
	private void initHeight() {
		int linesCount = linesHeight.size();
		int verticalSpacesHeight = SPACER_Y * (linesCount - 1);
		int classesHeight = sum(linesHeight.values());
		this.height += verticalSpacesHeight + classesHeight;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		currentPrintingX = MARGIN;
		currentPrintingY = MARGIN;
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.BLACK);
		paintLines(g, classes);
	}
	
	private void paintLines(Graphics g, List<List<ClassVisual>> lists) {
		currentLevel = 0;
		for (List<ClassVisual> list : lists) {
			paintLine(g, list);
			currentPrintingY += SPACER_Y + linesHeight.get(currentLevel);
			currentPrintingX = MARGIN;
			currentLevel++;
		}
	}
	
	private void paintLine(Graphics g, List<ClassVisual> elements) {
		extraSpacer = 0;
		for (ClassVisual v : elements) {
			ClassModel clazz = v.getClassModel();
			Graphics translatedGraphics = g.create(currentPrintingX, currentPrintingY, width, height);
			int[] currentClassCoordiantes = new int[]{currentPrintingX, currentPrintingY, v.getPreferredSize().width, v.getPreferredSize().height};
			entitiesCoordinates.put(clazz.getName(), currentClassCoordiantes);
			v.paintComponent(translatedGraphics);
			if(currentLevel > 0) {
				String superclassName = clazz.getSuperClassName();
				int[] superClassCoordiantes = entitiesCoordinates.get(superclassName);
				int x1 = currentClassCoordiantes[0] + (currentClassCoordiantes[2] / 2);
				int y1 = currentClassCoordiantes[1];
				int x2 = superClassCoordiantes[0] + (superClassCoordiantes[2] / 2);
				int y2 = superClassCoordiantes[1] + superClassCoordiantes[3];
				UIUtils.drawHorizontallyBrokenArrow(g, x1, y1, x2, y2, ((SPACER_Y / 3) + extraSpacer));
				extraSpacer += 5;
			}
			currentPrintingX += v.getPreferredSize().width + SPACER_X;
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
	
	public JScrollPane asScrollPane(int width, int height) {
		JScrollPane scrollPane =  new JScrollPane(this);
		scrollPane.setPreferredSize(new Dimension(width, height));
		UIUtils.fixScrolling(scrollPane);
		return scrollPane;
	}
	
	
	

}
