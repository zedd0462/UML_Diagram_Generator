package org.mql.java.umlgen.ui;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JPanel;

import org.mql.java.umlgen.models.ConstructorModel;
import org.mql.java.umlgen.models.FieldModel;
import org.mql.java.umlgen.models.MethodModel;

public abstract class EntityVisual extends JPanel{

	private static final long serialVersionUID = 1L;
	
	protected List<String> fields;
	protected List<String> methods;
	protected List<String> constructors;
	
	protected static final int DEFAULT_WIDTH = 100;
	protected static final int DEFAULT_HEIGHT = 150;
	protected static final int VERTICAL_SPACER = 15;
	protected static final int TITLE_BOX_HEIGHT = 30;
	protected static final int X_MARGIN = 10;
	protected static final int STROKE_SIZE = 3;
	protected static final int FONT_SIZE = 13;
	protected int width;
	protected int height;
	protected int estimatedfontHeight;
	protected int currentPrintingX;
	protected int currentPrintingY;
	protected int totalElementCount;
	
	protected int countRelationsNorth;
	protected int countRelationsEast;
	protected int countRelationsSouth;
	protected int countRelationsWest;
	
	
	protected Font font;
	protected FontMetrics fontmetrics;
	
	protected String entityName;
	
	protected EntityVisual() {
		
	}
	
	protected abstract void initStrings();
	protected abstract void initName();
	protected abstract boolean hasFields();
	protected abstract boolean hasConstructors();
	protected abstract boolean hasMethods();
	
	public void init() {
		fields = new Vector<String>();
		methods = new Vector<String>();
		constructors = new Vector<String>();
		totalElementCount = 0;
		font = new Font("Times New Roman",Font.BOLD, FONT_SIZE);
		fontmetrics = new Canvas().getFontMetrics(font);
		estimatedfontHeight = (fontmetrics.getHeight() / 2) + 2;
		height = DEFAULT_HEIGHT;
		initName();
		int entityNameWidth = fontmetrics.stringWidth(entityName) + X_MARGIN * 2;
		width = entityNameWidth > DEFAULT_WIDTH ? entityNameWidth : DEFAULT_WIDTH;
		initStrings();
		initHeight();
		initCounts();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		currentPrintingX = 0;
		currentPrintingY = 0;
		initGraphics(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);
		g2.setStroke(new BasicStroke(STROKE_SIZE));
		g.drawRect(0, 0, width, height);
		
		drawTitleBox(g2);
		if(hasFields()) drawFields(g2);
		if(hasConstructors()) drawConstructors(g2);
		if(hasMethods()) drawMethods(g2);
	}
	
	protected void initGraphics(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.setFont(font);
		Map<?, ?> desktopHints = (Map<?, ?>) Toolkit.getDefaultToolkit().getDesktopProperty("awt.font.desktophints");
		if (desktopHints != null) {
		    g2.setRenderingHints(desktopHints);
		}
	}
	
	protected void initFields(List<FieldModel> fieldModels) {
		int currentElementWidth = 0;
		for (FieldModel fieldModel : fieldModels) {
			String fieldString = fieldModel.toString();
			fields.add(fieldString);
			totalElementCount++;
			currentElementWidth = fontmetrics.stringWidth(fieldString) + (X_MARGIN * 2);
			if (currentElementWidth > width) {
				width = currentElementWidth;
			}
		}
	}
	
	protected void initMethods(List<MethodModel> methodModels) {
		int currentElementWidth = 0;
		for (MethodModel methodModel : methodModels) {
			String methodString = methodModel.toString();
			methods.add(methodString);
			totalElementCount++;
			currentElementWidth = fontmetrics.stringWidth(methodString) + (X_MARGIN * 2);
			if (currentElementWidth > width) {
				width = currentElementWidth;
			}
		}
	}
	
	protected void initConstructors(List<ConstructorModel> constructorModels) {
		int currentElementWidth = 0;
		for (ConstructorModel constructorModel : constructorModels) {
			String constructorString = constructorModel.toString();
			constructors.add(constructorString);
			totalElementCount++;
			currentElementWidth = fontmetrics.stringWidth(constructorString) + (X_MARGIN * 2);
			if (currentElementWidth > width) {
				width = currentElementWidth;
			}
		}
	}
	
	protected void drawTitleBox(Graphics2D g) {
		int entityNameWidth =  fontmetrics.stringWidth(entityName);
		int entityNameHeight = estimatedfontHeight;
		g.drawLine(0, TITLE_BOX_HEIGHT, width, TITLE_BOX_HEIGHT);
		g.setStroke(new BasicStroke(STROKE_SIZE));
		g.drawString(entityName, (width / 2) - (entityNameWidth / 2), (TITLE_BOX_HEIGHT / 2) + (entityNameHeight/ 2));
		currentPrintingY = TITLE_BOX_HEIGHT + VERTICAL_SPACER + STROKE_SIZE;
	}
	
	protected void drawConstructors(Graphics2D g) {
		drawSeparatingLine(g);
		for (String string : constructors) {
			g.drawString(string, X_MARGIN, currentPrintingY);
			currentPrintingY +=  VERTICAL_SPACER;
		}
	}
	
	protected void drawFields(Graphics2D g) {
		for (String string : fields) {
			g.drawString(string, X_MARGIN, currentPrintingY);
			currentPrintingY +=  VERTICAL_SPACER;
		}
	}
	
	protected void drawMethods(Graphics2D g) {
		drawSeparatingLine(g);
		for (String string : methods) {
			g.drawString(string, X_MARGIN, currentPrintingY);
			currentPrintingY +=  VERTICAL_SPACER;
		}
	}
	
	protected void drawSeparatingLine(Graphics2D g) {
		int toPrintY = currentPrintingY - (estimatedfontHeight / 2);
		g.setStroke(new BasicStroke(STROKE_SIZE));
		g.drawLine(0, toPrintY, width, toPrintY);
		currentPrintingY += VERTICAL_SPACER + STROKE_SIZE - (estimatedfontHeight / 2);
	}
	
	protected void initHeight() {
		int tmpHeight = TITLE_BOX_HEIGHT + (VERTICAL_SPACER * 3);
		tmpHeight += (totalElementCount * (VERTICAL_SPACER));
		this.height = tmpHeight > DEFAULT_HEIGHT ? tmpHeight : DEFAULT_HEIGHT;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
	
	public void initCounts() {
		countRelationsNorth = 0;
		countRelationsEast = 0;
		countRelationsSouth = 0;
		countRelationsWest = 0;
	}

	public int getNorth() {
		return countRelationsNorth;
	}

	public void setNorth(int countRelationsNorth) {
		this.countRelationsNorth = countRelationsNorth;
	}

	public int getEast() {
		return countRelationsEast;
	}

	public void setEast(int countRelationsEast) {
		this.countRelationsEast = countRelationsEast;
	}

	public int getSouth() {
		return countRelationsSouth;
	}

	public void setSouth(int countRelationsSouth) {
		this.countRelationsSouth = countRelationsSouth;
	}

	public int getWest() {
		return countRelationsWest;
	}

	public void setWest(int countRelationsWest) {
		this.countRelationsWest = countRelationsWest;
	}

	public void incNorth() {
		countRelationsNorth++;
	}

	public void incEast() {
		countRelationsEast++;
	}

	public void incSouth() {
		countRelationsSouth++;
	}

	public void incWest() {
		countRelationsWest++;
	}
	
	
	

}
