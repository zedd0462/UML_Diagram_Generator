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
	protected static final int VERTICAL_SPACER = 20;
	protected static final int TITLE_BOX_HEIGHT = 30;
	protected static final int X_MARGIN = 10;
	protected int width;
	protected int height;
	protected int estimatedfontHeight;
	protected int currentPrintingY;
	
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
		font = new Font("Times New Roman",Font.BOLD, 13);
		fontmetrics = new Canvas().getFontMetrics(font);
		estimatedfontHeight = (fontmetrics.getHeight() / 2) + 2;
		height = DEFAULT_HEIGHT;
		initName();
		int entityNameWidth = fontmetrics.stringWidth(entityName) + X_MARGIN * 2;
		width = entityNameWidth > DEFAULT_WIDTH ? entityNameWidth : DEFAULT_WIDTH;
		initStrings();
		initHeight();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		initGraphics(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);
		g2.setStroke(new BasicStroke(3));
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
			currentElementWidth = fontmetrics.stringWidth(fieldString) + X_MARGIN * 2;
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
			currentElementWidth = fontmetrics.stringWidth(methodString) + X_MARGIN * 2;
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
			currentElementWidth = fontmetrics.stringWidth(constructorString) + X_MARGIN * 2;
			if (currentElementWidth > width) {
				width = currentElementWidth;
			}
		}
	}
	
	protected void drawTitleBox(Graphics2D g) {
		int entityNameWidth =  fontmetrics.stringWidth(entityName);
		int entityNameHeight = estimatedfontHeight;
		g.drawLine(0, TITLE_BOX_HEIGHT, width, TITLE_BOX_HEIGHT);
		g.setStroke(new BasicStroke());
		g.drawString(entityName, (width / 2) - (entityNameWidth / 2), (TITLE_BOX_HEIGHT / 2) + (entityNameHeight/ 2));
		currentPrintingY = TITLE_BOX_HEIGHT + 10;
	}
	
	protected void drawConstructors(Graphics2D g) {
		currentPrintingY += VERTICAL_SPACER;
		g.setStroke(new BasicStroke(3));
		g.drawLine(0, currentPrintingY, width, currentPrintingY);
		for (String string : constructors) {
			currentPrintingY += VERTICAL_SPACER;
			g.drawString(string, X_MARGIN, currentPrintingY);
		}
	}
	
	protected void drawFields(Graphics2D g) {
		for (String string : fields) {
			currentPrintingY += VERTICAL_SPACER;
			g.drawString(string, X_MARGIN, currentPrintingY);
		}
	}
	
	protected void drawMethods(Graphics2D g) {
		currentPrintingY += VERTICAL_SPACER;
		g.setStroke(new BasicStroke(3));
		g.drawLine(0, currentPrintingY, width, currentPrintingY);
		for (String string : methods) {
			currentPrintingY += VERTICAL_SPACER;
			g.drawString(string, X_MARGIN, currentPrintingY);
		}
	}
	
	protected void initHeight() {
		int totalElementCount = constructors.size() + methods.size() + fields.size();
		int height = (totalElementCount * estimatedfontHeight) + ((totalElementCount) * VERTICAL_SPACER);
		this.height = height > DEFAULT_HEIGHT ? height : DEFAULT_HEIGHT;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

}
