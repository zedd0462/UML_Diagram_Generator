package org.mql.java.umlgen.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class UIUtils {
	
	public static final int DEFAULT_DASH_LENGTH = 20;
	
	private UIUtils() {	}
	
	public static void fixScrolling(JScrollPane scrollpane) {
	    JLabel systemLabel = new JLabel();
	    FontMetrics metrics = systemLabel.getFontMetrics(systemLabel.getFont());
	    int lineHeight = metrics.getHeight();
	    int charWidth = metrics.getMaxAdvance();
	            
	    JScrollBar systemVBar = new JScrollBar(JScrollBar.VERTICAL);
	    JScrollBar systemHBar = new JScrollBar(JScrollBar.HORIZONTAL);
	    int verticalIncrement = systemVBar.getUnitIncrement();
	    int horizontalIncrement = systemHBar.getUnitIncrement();
	            
	    scrollpane.getVerticalScrollBar().setUnitIncrement(lineHeight * verticalIncrement);
	    scrollpane.getHorizontalScrollBar().setUnitIncrement(charWidth * horizontalIncrement);
	}
	
	public static void drawArrow(Graphics gr, int x1, int y1, int x2, int y2) {
		
		Graphics2D g = (Graphics2D) gr;
		g.setStroke(new BasicStroke(2));
		g.setColor(Color.BLACK);
		g.drawLine(x1, y1, x2, y2);
	
        double angle = Math.atan2(y2 - y1, x2 - x1);

        int arrowSize = 10;

        int x3 = x2 - (int) (arrowSize * Math.cos(angle - Math.PI / 6));
        int y3 = y2 - (int) (arrowSize * Math.sin(angle - Math.PI / 6));
        g.drawLine(x2, y2, x3, y3);

        int x4 = x2 - (int) (arrowSize * Math.cos(angle + Math.PI / 6));
        int y4 = y2 - (int) (arrowSize * Math.sin(angle + Math.PI / 6));
        g.drawLine(x2, y2, x4, y4);
        
        g.drawLine(x3, y3, x4, y4);
        
	}
	
	public static void drawHorizontallyBrokenArrow(Graphics gr, int x1, int y1, int x2, int y2, int breakingDistance) {
	    Graphics2D g = (Graphics2D) gr;
	    g.setStroke(new BasicStroke(2));
	    g.setColor(Color.BLACK);

	    int breakY1 = y1 + (y2 > y1 ? breakingDistance : -breakingDistance);
	    g.drawLine(x1, y1, x1, breakY1);

	    int breakX = x2;
	    g.drawLine(x1, breakY1, breakX, breakY1);
	    
	    int breakY2 = y2 + (y2 > y1 ? -breakingDistance : breakingDistance);
	    g.drawLine(breakX, breakY1, breakX, breakY2);

	    g.drawLine(breakX, breakY2, x2, y2);

	    double angle = Math.atan2(y2 - breakY2, x2 - breakX);
	    int arrowSize = 10;

	    int arrowX1 = x2 - (int) (arrowSize * Math.cos(angle - Math.PI / 6));
	    int arrowY1 = y2 - (int) (arrowSize * Math.sin(angle - Math.PI / 6));
	    g.drawLine(x2, y2, arrowX1, arrowY1);

	    int arrowX2 = x2 - (int) (arrowSize * Math.cos(angle + Math.PI / 6));
	    int arrowY2 = y2 - (int) (arrowSize * Math.sin(angle + Math.PI / 6));
	    g.drawLine(x2, y2, arrowX2, arrowY2);

	    g.drawLine(arrowX1, arrowY1, arrowX2, arrowY2);
	}
	
	public static void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2, int dashLength) {

        double lineLength = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        int numDashes = (int) (lineLength / dashLength);

        double xInc = (x2 - x1) / numDashes;
        double yInc = (y2 - y1) / numDashes;

        for (int i = 0; i < numDashes; i++) {
            int xStart = (int) (x1 + i * xInc);
            int yStart = (int) (y1 + i * yInc);
            int xEnd = (int) (x1 + (i + 0.5) * xInc);
            int yEnd = (int) (y1 + (i + 0.5) * yInc);
            g.drawLine(xStart, yStart, xEnd, yEnd);
        }
    }
	
	public static void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2) {
		int dashLength = DEFAULT_DASH_LENGTH;
        drawDashedLine(g, x1, y1, x2, y2, dashLength);
    }
	
	public static void drawDashedArrow(Graphics gr, int x1, int y1, int x2, int y2) {
        Graphics2D g = (Graphics2D) gr;

        drawDashedLine(g, x1, y1, x2, y2);

        double angle = Math.atan2(y2 - y1, x2 - x1);
        int arrowSize = 10;

        int x3 = x2 - (int) (arrowSize * Math.cos(angle - Math.PI / 6));
        int y3 = y2 - (int) (arrowSize * Math.sin(angle - Math.PI / 6));

        int x4 = x2 - (int) (arrowSize * Math.cos(angle + Math.PI / 6));
        int y4 = y2 - (int) (arrowSize * Math.sin(angle + Math.PI / 6));

        g.drawLine(x3, y3, x2, y2);
        g.drawLine(x4, y4, x2, y2);
        
        g.drawLine(x3, y3, x4, y4);
    }
	
	public static void drawHorizontallyDashedBrokenArrow(Graphics gr, int x1, int y1, int x2, int y2, int breakingDistance) {
	    Graphics2D g = (Graphics2D) gr;
	    g.setStroke(new BasicStroke(2));
	    g.setColor(Color.BLACK);

	    int breakY1 = y1 + (y2 > y1 ? breakingDistance : -breakingDistance);
	    drawDashedLine(g, x1, y1, x1, breakY1);

	    int breakX = x2;
	    drawDashedLine(g, x1, breakY1, breakX, breakY1);
	    
	    int breakY2 = y2 + (y2 > y1 ? -breakingDistance : breakingDistance);
	    drawDashedLine(g, breakX, breakY1, breakX, breakY2);

	    drawDashedLine(g, breakX, breakY2, x2, y2);

	    double angle = Math.atan2(y2 - breakY2, x2 - breakX);
	    int arrowSize = 10;

	    int arrowX1 = x2 - (int) (arrowSize * Math.cos(angle - Math.PI / 6));
	    int arrowY1 = y2 - (int) (arrowSize * Math.sin(angle - Math.PI / 6));
	    g.drawLine(x2, y2, arrowX1, arrowY1);

	    int arrowX2 = x2 - (int) (arrowSize * Math.cos(angle + Math.PI / 6));
	    int arrowY2 = y2 - (int) (arrowSize * Math.sin(angle + Math.PI / 6));
	    g.drawLine(x2, y2, arrowX2, arrowY2);

	    g.drawLine(arrowX1, arrowY1, arrowX2, arrowY2);
	}

}
