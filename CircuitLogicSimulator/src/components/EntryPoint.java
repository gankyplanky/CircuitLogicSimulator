package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class EntryPoint extends JComponent implements CommonComponentTemplate, MouseListener{
	
	//Drawing parameters
	public int drawWidth = 25;
	public int drawHeight = 25;
	public int nodeOutOffsetX = drawWidth / 5;
	public int nodeOutOffsetY = drawHeight / 3;
	
	//Color for drawing
	public Color onColor  = Color.RED;
	public Color offColor = Color.BLACK;
	
	//Externals
	public ConnectionPoint nodeOut = new ConnectionPoint(this, false, 0);
	private JComponent parent;
	
	//Important checks
	public boolean isOn = true;
	
	//Unique ID
	public double uniquieID = 0;
	
	public EntryPoint(JComponent parent) {
		this.addMouseListener(this);
		nodeOut.setState(isOn);
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
		
		g2d.fillOval(0, 0, drawWidth, drawHeight);
		
		this.add(nodeOut);
		nodeOut.setBounds(new Rectangle(10, 10));
		nodeOut.setLocation(drawWidth - nodeOutOffsetX, nodeOutOffsetY);
	}
	
	public double calculateUniqueID() {
		double newID = this.hashCode();
		
		uniquieID = newID;
		return newID;
	}
	
	public void setWidthHeight(int width, int height) {
		drawWidth = width;
		drawHeight = height;
		nodeOutOffsetX = drawWidth / 5;
		nodeOutOffsetY = drawHeight / 3 - 2;
	}
	
	public int getConnPointX(int positionIndex, boolean isEntry) {		
		return this.getX() + (drawWidth - 2);
	}
	
	public int getConnPointY(int positionIndex, boolean isEntry) {		
		return this.getY() + (drawHeight / 2);
	}
	
	public void setState(boolean state) {
		isOn = state;
		nodeOut.setState(isOn);
	} 
	
	public JComponent getParent() {
		return parent;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == 1) {
			isOn = !isOn;
			nodeOut.setState(isOn);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Unused		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Unused		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Unused		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Unused		
	}
}
