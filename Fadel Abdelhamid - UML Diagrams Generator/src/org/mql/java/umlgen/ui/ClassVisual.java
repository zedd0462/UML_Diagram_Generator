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

import org.mql.java.umlgen.models.ClassModel;
import org.mql.java.umlgen.models.ConstructorModel;
import org.mql.java.umlgen.models.FieldModel;
import org.mql.java.umlgen.models.MethodModel;
import static org.mql.java.umlgen.utils.StringUtils.*;

public class ClassVisual extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private ClassModel classModel;
	
	private List<String> fields;
	private List<String> methods;
	private List<String> constructors;
	
	private static final int DEFAULT_WIDTH = 100;
	private static final int DEFAULT_HEIGHT = 100;
	private static final int VERTICAL_SPACER = 20;
	private static final int TITLE_BOX_HEIGHT = 30;
	private static final int X_MARGIN = 10;
	private int width;
	private int height;
	private int estimatedfontHeight;
	
	private Font font;
	private FontMetrics fontmetrics;
	
	private String entityName;
	
	protected ClassVisual() {
		fields = new Vector<String>();
		methods = new Vector<String>();
		constructors = new Vector<String>();
		font = new Font("Times New Roman",Font.BOLD, 13);
		fontmetrics = new Canvas().getFontMetrics(font);
		estimatedfontHeight = (fontmetrics.getHeight() / 2) + 2;
		width = DEFAULT_WIDTH;
		height = DEFAULT_HEIGHT;
	}

	public ClassVisual(ClassModel classModel) {
		this();
		this.classModel = classModel;
		entityName = getClassShortName(classModel.getName());
		initStrings();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		initGraphics(g);
		Graphics2D g2 = (Graphics2D) g;
		
		
		int entityNameWidth =  fontmetrics.stringWidth(entityName);
		int entityNameHeight = estimatedfontHeight;//for some reason this returns almost double the height ??
		
		
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);
		g2.setStroke(new BasicStroke(3));
		g.drawRect(0, 0, width, height);
		g.drawLine(0, TITLE_BOX_HEIGHT, width, TITLE_BOX_HEIGHT);
		g2.setStroke(new BasicStroke());
		g.drawString(entityName, (width / 2) - (entityNameWidth / 2), (TITLE_BOX_HEIGHT / 2) + (entityNameHeight/ 2));
		
		int currentPrintingY = TITLE_BOX_HEIGHT + 10;
		for (String string : fields) {
			currentPrintingY += VERTICAL_SPACER;
			g.drawString(string, X_MARGIN, currentPrintingY);
		}
		
		currentPrintingY += VERTICAL_SPACER;
		g2.setStroke(new BasicStroke(3));
		g.drawRect(0, 0, width, height);
		g.drawLine(0, currentPrintingY, width, currentPrintingY);
		
		for (String string : constructors) {
			currentPrintingY += VERTICAL_SPACER;
			g.drawString(string, X_MARGIN, currentPrintingY);
		}
		
		currentPrintingY += VERTICAL_SPACER;
		g2.setStroke(new BasicStroke(3));
		g.drawRect(0, 0, width, height);
		g.drawLine(0, currentPrintingY, width, currentPrintingY);
		
		for (String string : methods) {
			currentPrintingY += VERTICAL_SPACER;
			g.drawString(string, X_MARGIN, currentPrintingY);
		}
	}
	
	private void initGraphics(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		//set the font
		g.setFont(font);
		//stop the font from looking pixelized
		Map<?, ?> desktopHints = (Map<?, ?>) Toolkit.getDefaultToolkit().getDesktopProperty("awt.font.desktophints");
		if (desktopHints != null) {
		    g2.setRenderingHints(desktopHints);
		}
	}
	
	private void initStrings() {
		int width = DEFAULT_WIDTH;
		int height = 0;
		int currentElementWidth = 0;
		List<FieldModel> fieldModels = classModel.getFields();
		for (FieldModel fieldModel : fieldModels) {
			String fieldString = fieldModel.toString();
			fields.add(fieldString);
			currentElementWidth = fontmetrics.stringWidth(fieldString) + X_MARGIN * 2;
			if (currentElementWidth > width) {
				width = currentElementWidth;
			}
		}
		List<MethodModel> methodModels = classModel.getMethods();
		for (MethodModel methodModel : methodModels) {
			String methodString = methodModel.toString();
			methods.add(methodString);
			currentElementWidth = fontmetrics.stringWidth(methodString) + X_MARGIN * 2;
			if (currentElementWidth > width) {
				width = currentElementWidth;
			}
			
		}
		List<ConstructorModel> constructorModels = classModel.getConstructors();
		for (ConstructorModel constructorModel : constructorModels) {
			String constructorString = constructorModel.toString();
			constructors.add(constructorString);
			currentElementWidth = fontmetrics.stringWidth(constructorString) + X_MARGIN;
			if (currentElementWidth > width) {
				width = currentElementWidth;
			}
		}
		
		int totalElementCount = constructorModels.size() + methodModels.size() + fieldModels.size();
		height = (totalElementCount * estimatedfontHeight) + ((totalElementCount) * VERTICAL_SPACER);
		this.height = height > DEFAULT_HEIGHT ? height : DEFAULT_HEIGHT;
		this.width = width;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

}
