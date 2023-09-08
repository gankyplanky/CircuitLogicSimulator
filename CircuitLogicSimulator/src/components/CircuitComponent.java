package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

import gateLogic.LogicTemplate;

@SuppressWarnings("serial")
public class CircuitComponent extends JComponent implements CommonComponentTemplate, MouseListener, MouseMotionListener{

	//Node connections
	public List<ConnectionPoint> inNodes = new ArrayList<ConnectionPoint>();
	public List<ConnectionPoint> outNodes = new ArrayList<ConnectionPoint>();
	
	//Externals
	public JComponent parent;
	
	//Colors for drawing
	public Color bodyColor = Color.BLUE;
	public Color textColor = Color.white;
	
	//Drawing parameters
	public int drawWidth = 25;
	public int drawHeight = 25;
	public String displayName = "DEFAULT";
	public boolean hasMoved = false;
	public int x = 0;
	public int y = 0;
	
	//Logic object
	public LogicTemplate gateLogic = null;
	
	//Unique ID
	public double uniquieID = 0;
	
	public CircuitComponent(LogicTemplate gate, JComponent parent) {
		gateLogic = gate;
		this.parent = parent;
		displayName = gateLogic.name;
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setDoubleBuffered(true);
		
		for(int i = 0; i < gateLogic.inNodesCount; i++){
			inNodes.add(new ConnectionPoint(this, true, i));
		}
		
		for(int i = 0; i < gateLogic.outNodesCount; i++) {
			outNodes.add(new ConnectionPoint(this, false, i));
		}
		
		drawWidth = (displayName.length() * 9) + 20;
		
		if(inNodes.size() > outNodes.size()) {
			drawHeight = (inNodes.size() * 10) + (inNodes.size() * 5);
		}
		else {
			drawHeight = (outNodes.size() * 10) + (outNodes.size() * 5);
		}	
		
		uniquieID = calculateUniqueID();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(bodyColor);	
		g2d.fillRect(5, 0, drawWidth - 10, drawHeight);
		
		g2d.setColor(textColor);
		g2d.drawChars(displayName.toCharArray(), 0, displayName.length(), ((drawWidth / 4)), drawHeight / 2 + 4);
		
		int yPosInc = drawHeight / (inNodes.size() + 1);
		int yPos = yPosInc;
		
		for(ConnectionPoint c : inNodes) {
			this.add(c);
			c.setBounds(new Rectangle(8, 8));
			c.setLocation(0, yPos - 5);
			yPos += yPosInc;
		}
		
		yPosInc = drawHeight / (outNodes.size() + 1);
		yPos = yPosInc;
		
		for(ConnectionPoint c : outNodes) {
			this.add(c);
			c.setBounds(new Rectangle(8, 8));
			c.setLocation(drawWidth - 7, yPos - 5);
			yPos += yPosInc;
		}
	}
	
	public double calculateUniqueID() {
		double newID = this.hashCode();
				
		uniquieID = newID;
		return newID;
	}
	
	public void setState(boolean state){
		String currentState = "";
		for(ConnectionPoint c : inNodes)
			currentState += c.isOn ? "1" : "0";
		
		String output = gateLogic.truthTable.get(currentState);
		
		for(int i = 0; i < gateLogic.outNodesCount; i++)
			outNodes.get(i).setState(output.toCharArray()[i] == '1' ? true : false);			
	}
	
	public int getConnPointX(int positionIndex, boolean isEntry) {
		if(isEntry)
			return this.getX() + 3;		
		return this.getX() + drawWidth - 3;
	}
	
	public int getConnPointY(int positionIndex, boolean isEntry) {
		if(isEntry)
			return this.getY() + (drawHeight / (inNodes.size() + 1) * (positionIndex + 1));		
		return this.getY() +(drawHeight / (outNodes.size() + 1) * (positionIndex + 1));
	}
	
	public JComponent getParent() {
		return parent;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		CircuitCanvas panel = (CircuitCanvas)this.getParent();
		
		if(e.getButton() == 3) {
			
			for(ConnectionPoint c : inNodes) {
				if(c.connection != null)
					panel.removeWire(c.connection);
			}
			
			for(ConnectionPoint c : outNodes) {
				if(c.connection != null)
					panel.removeWire(c.connection);
			}
			
			panel.wireDrawFlag = false;
			panel.curDrawnWire = null;
			panel.removeCircuitComponent(this);
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// Unused
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// Unused
		
	}
}
