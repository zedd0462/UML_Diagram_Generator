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
	
	private UIUtils() {	}
	
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

	    // Draw vertically to the breaking point
	    int breakY1 = y1 + (y2 > y1 ? breakingDistance : -breakingDistance);
	    g.drawLine(x1, y1, x1, breakY1);

	    // Take a detour horizontally to reach the perpendicular point
	    int breakX = x2;
	    g.drawLine(x1, breakY1, breakX, breakY1);

	    // Take another detour vertically to reach the final point
	    int breakY2 = y2 + (y2 > y1 ? -breakingDistance : breakingDistance);
	    g.drawLine(breakX, breakY1, breakX, breakY2);

	    // Draw the final vertical segment
	    g.drawLine(breakX, breakY2, x2, y2);

	    // Draw arrowhead
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

}
