package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class ExitPoint extends JComponent implements CommonComponentTemplate {
	
	//Drawing parameters
	public int drawWidth = 25;
	public int drawHeight = 25;
	public int nodeOutOffsetX = 0;
	public int nodeOutOffsetY = drawHeight / 3;
	
	//Color for drawing
	public Color onColor  = Color.RED;
	public Color offColor = Color.BLACK;
	
	//Externals
	public ConnectionPoint nodeIn = new ConnectionPoint(this, true, 0);
	private JComponent parent;
	
	//Important checks
	public boolean isOn = false;
	
	//Unique ID
	public double uniquieID = 0;
	
	public ExitPoint(JComponent parent) {
		setState(nodeIn.isOn);
		this.parent = parent;
		uniquieID = calculateUniqueID();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		if(isOn)
			g2d.setColor(onColor);
		else
			g2d.setColor(offColor);
		
		g2d.fillOval(5, 0, drawWidth, drawHeight);
		
		this.add(nodeIn);
		nodeIn.setBounds(new Rectangle(10, 10));
		nodeIn.setLocation(nodeOutOffsetX, nodeOutOffsetY);
	}
	
	public double calculateUniqueID() {
		double newID = hashCode();
		
		uniquieID = newID;
		return newID;
	}
	
	public void setWidthHeight(int width, int height) {
		drawWidth = width;
		drawHeight = height;
		nodeOutOffsetY = drawHeight / 3 - 2;
	}
	
	public int getConnPointX(int positionIndex, boolean isEntry) {		
		return this.getX() + 2;
	}
	
	public int getConnPointY(int positionIndex, boolean isEntry) {		
		return this.getY() + (drawHeight / 2);
	}
	
	public void setState(boolean state) {
		isOn = state;
	} 
	
	public JComponent getParent() {
		return parent;
	}

}