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
import org.mql.java.umlgen.models.InterfaceModel;
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
	private static final int EXTRA_WIDTH = 50;
	private int extraSpacer;
	private int height;
	private int width;
	private int currentPrintingX;
	private int currentPrintingY;
	
	private int classRows;
	private int currentClassLevel;
	
	private ProjectModel project;
	private ProjectContext projectContext;
	
	private int classSectionStartingX;
	private int classSectionStartingY;
	
	private int interfaceSectionStartingX;
	private int interfaceSectionStartingY;
	private int interfaceSectionWidth;
	private int interfaceSectionHeight;
	
	private int relationBreakPointX, relationBreakPointY;
	
	private Collection<ClassModel> projectClasses;
	private Collection<InterfaceModel> projectInterfaces;
	private List<List<ClassVisual>> classes;
	private List<InterfaceVisual> interfaces;
	private Map<Integer, Integer> classRowsHeight;
	private Map<Integer, Integer> classRowsWidth;
	private Map<String, int[]> entitiesCoordinates;

	public ClassDiagram(ProjectModel projectModel) {
		project = projectModel;
		projectContext = project.getProjectContext();
		interfaceSectionWidth = 0;
		interfaceSectionHeight = 0;
		interfaceSectionStartingX = MARGIN;
		interfaceSectionStartingY = MARGIN;
		currentClassLevel = 0;
		height = MARGIN * 2;
		width = MARGIN * 2;
		initLists();
		fillEntites();
		initHeight();
		initWidth();
		relationBreakPointX = width - SPACER_X - EXTRA_WIDTH;
		relationBreakPointY = interfaceSectionStartingY + interfaceSectionHeight + (SPACER_Y / 2);
		if(width < DEFAULT_WIDTH) width = DEFAULT_WIDTH;
		if(height < DEFAULT_HEIGHT) height = DEFAULT_HEIGHT;
		
		classSectionStartingX = MARGIN;
		classSectionStartingY = interfaceSectionStartingY + interfaceSectionHeight();
	}
	
	private void initLists() {
		classes = new Vector<List<ClassVisual>>();
		interfaces = new Vector<InterfaceVisual>();
		projectClasses = projectContext.getLoadedClasses().values();
		projectInterfaces = projectContext.getLoadedInterfaces().values();
		classRowsHeight = new Hashtable<Integer, Integer>();
		classRowsWidth = new Hashtable<Integer, Integer>();
		entitiesCoordinates = new Hashtable<String, int[]>();
		classRows = max(projectContext.getClassesInhertianceLevel().values());
		for(int i = 0; i <= classRows; i++) {
			classes.add(new Vector<ClassVisual>());
			classRowsHeight.put(i, 0);
			classRowsWidth.put(i, 0);
		}
	}
	
	private void fillEntites() {
		for (ClassModel classModel : projectClasses) {
			ClassVisual v = new ClassVisual(classModel);
			int inheritanceLevel = projectContext.getClassInheritanceLevel(classModel);
			int addedWidth = v.getPreferredSize().width + SPACER_X;
			classes.get(inheritanceLevel).add(v);
			classRowsHeight.put(inheritanceLevel, max(v.getPreferredSize().height, classRowsHeight.get(inheritanceLevel)));
			classRowsWidth.put(inheritanceLevel, classRowsWidth.get(inheritanceLevel) + addedWidth);
		}
		for (InterfaceModel interfaceModel : projectInterfaces) {
			InterfaceVisual v = new InterfaceVisual(interfaceModel);
			int entityHeight = v.getPreferredSize().height;
			int addedWidth = v.getPreferredSize().width + SPACER_X;
			interfaces.add(v);
			interfaceSectionHeight = max(interfaceSectionHeight, entityHeight);
			interfaceSectionWidth += addedWidth;
		}
	}
	
	private void initHeight() {
		this.height += interfaceSectionHeight() + SPACER_Y + classSectionHeight();
	}
	
	private void initWidth() {
		this.width += max(classSectionWidth(), interfaceSectionWidth());
		this.width += EXTRA_WIDTH;
	}
	
	private int classSectionWidth() {
		return max(classRowsWidth.values());
	}
	
	private int classSectionHeight() {
		int classRowsCount = classRowsHeight.size();
		int verticalSpacesHeight = SPACER_Y * (classRowsCount - 1);
		int classesHeight = sum(classRowsHeight.values());
		return verticalSpacesHeight + classesHeight;
	}
	
	private int interfaceSectionWidth() {
		return interfaceSectionWidth;
	}
	
	private int interfaceSectionHeight() {
		return interfaceSectionHeight > 0 ? interfaceSectionHeight + SPACER_Y : 0 ;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		currentPrintingX = MARGIN;
		currentPrintingY = MARGIN;
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.BLACK);
		paintInterfaces(g, interfaces);
		paintClassRows(g, classes);
	}
	
	private void paintClassRows(Graphics g, List<List<ClassVisual>> lists) {
		currentClassLevel = 0;
		currentPrintingX = classSectionStartingX;
		currentPrintingY = classSectionStartingY;
		for (List<ClassVisual> list : lists) {
			paintClassRow(g, list);
			currentPrintingY += SPACER_Y + classRowsHeight.get(currentClassLevel);
			currentPrintingX = classSectionStartingX;
			currentClassLevel++;
		}
	}
	
	//TODO refactor this (its getting too long)
	private void paintClassRow(Graphics g, List<ClassVisual> elements) {
		extraSpacer = 0;
		int breakX = relationBreakPointX;
		int breakY = relationBreakPointY;
		int interfaceRelationSpacer = 5;
		for (ClassVisual v : elements) {
			ClassModel clazz = v.getClassModel();
			List<InterfaceModel> implementedInterfaces = projectContext.getImplementedInterfaces(clazz);
			Graphics translatedGraphics = g.create(currentPrintingX, currentPrintingY, width, height);
			int[] currentClassCoordiantes = new int[]{currentPrintingX, currentPrintingY, v.getPreferredSize().width, v.getPreferredSize().height};
			entitiesCoordinates.put(clazz.getName(), currentClassCoordiantes);
			v.paintComponent(translatedGraphics);
			if(currentClassLevel > 0) {
				String superclassName = clazz.getSuperClassName();
				v.incNorth();
				int[] superClassCoordiantes = entitiesCoordinates.get(superclassName);
				int x1 = currentClassCoordiantes[0] + (currentClassCoordiantes[2] / 2);
				int y1 = currentClassCoordiantes[1];
				int x2 = superClassCoordiantes[0] + (superClassCoordiantes[2] / 2);
				int y2 = superClassCoordiantes[1] + superClassCoordiantes[3];
				UIUtils.drawHorizontallyBrokenArrow(g, x1, y1, x2, y2, ((SPACER_Y / 3) + extraSpacer));
				extraSpacer += 5;
			}
			if(implementedInterfaces.size() > 0) {
				for (InterfaceModel interfaceModel : implementedInterfaces) {
					String interfaceName = interfaceModel.getName();
					int[] interfaceCoordiantes = entitiesCoordinates.get(interfaceName);
					int x1 = currentClassCoordiantes[0] + (currentClassCoordiantes[2] / 2) + interfaceRelationSpacer;
					int y1 = currentClassCoordiantes[1];
					int x2 = interfaceCoordiantes[0] + (interfaceCoordiantes[2] / 2);
					int y2 = interfaceCoordiantes[1] + interfaceCoordiantes[3];
					if(currentClassLevel == 0) {
						UIUtils.drawHorizontallyDashedBrokenArrow(
									g, x1, y1, x2, y2, ((SPACER_Y / 3) + extraSpacer)
								);
					}else {
						UIUtils.drawHorizontallyDashedBrokenLine(
									g, x1, y1, breakX, breakY, ((SPACER_Y / 3) + extraSpacer)
								);
						UIUtils.drawHorizontallyDashedBrokenArrow(
									g, breakX, breakY, x2, y2, (10 + extraSpacer)
								);
						breakX += 5;
						//breakY += 5;
					}
					interfaceRelationSpacer += 3;
					extraSpacer += 5;
				}
			}
			currentPrintingX += v.getPreferredSize().width + SPACER_X;
		}
	}
	
	private void paintInterfaces(Graphics g, List<InterfaceVisual> elements) {
		extraSpacer = 0;
		currentPrintingX = interfaceSectionStartingX;
		currentPrintingY = interfaceSectionStartingY;
		for (InterfaceVisual v : elements) {
			InterfaceModel interf = v.getInterfaceModel();
			Graphics translatedGraphics = g.create(currentPrintingX, currentPrintingY, width, height);
			int[] currentClassCoordiantes = new int[]{currentPrintingX, currentPrintingY, v.getPreferredSize().width, v.getPreferredSize().height};
			entitiesCoordinates.put(interf.getName(), currentClassCoordiantes);
			v.paintComponent(translatedGraphics);
			//TODO: draw inhertiance
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
