package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class ConnectionPoint extends JComponent implements MouseListener, MouseMotionListener{
	
	//Drawing parameters
	public int width = 10, heigth = 10;
	public int parentPositionIndex = 0;
	public int wireConnPointX = 0;
	public int wireConnPointY = 0;
	
	//Colors for drawing
	public Color selectedColor = Color.getHSBColor((float)4, (float)0.83, (float)0.41);
	public Color onColor = Color.ORANGE;
	public Color offColor = Color.DARK_GRAY;
	
	//Externals
	public Wire connection = null;
	public CommonComponentTemplate parent = null;
	
	//Important checks
	public boolean role = false; // false is exit, true is entry
	public boolean selected = false;
	public boolean isOn = false; 
	
	public ConnectionPoint(CommonComponentTemplate parent, boolean role, int parentPositionIndex) {
		this.setPreferredSize(new Dimension(width, heigth));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.parent = parent;
		this.role = role;
		this.parentPositionIndex = parentPositionIndex;				
		wireConnPointX = parent.getConnPointX(parentPositionIndex, role);
		wireConnPointY = parent.getConnPointY(parentPositionIndex, role);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		if(selected)
			g2d.setColor(selectedColor);
		else if(this.isOn)
			g2d.setColor(onColor);
		else
			g2d.setColor(offColor);		
		g2d.fillRect(0, 0, width, heigth);
		wireConnPointX = parent.getConnPointX(parentPositionIndex, role);
		wireConnPointY = parent.getConnPointY(parentPositionIndex, role);
	}
	
	public void setState(boolean state) {
		isOn = state;
		
		if(connection != null) {
			if(!role) 
				connection.setState(state);
			else
				parent.setState(state);
		}
	}
	
	public void removeConnection() {
		CircuitCanvas panel = (CircuitCanvas)this.getParent().getParent();
		if(connection != null)
			panel.removeWire(connection);
		panel.wireDrawFlag = false;
		panel.curDrawnWire = null;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		CircuitCanvas panel = (CircuitCanvas)this.getParent().getParent();
		
		if(e.getButton() == 3) {
			removeConnection();			
		}
		
		//Draws new wire
		if(!panel.wireDrawFlag && !role && e.getButton() == 1 && connection == null) {
			connection = new Wire();
			connection.nodeIn = this;
			setState(isOn);
			selected = true;
			panel.curDrawnWire = connection;
			panel.wireDrawFlag = true;
			panel.drawingConnPoint = this;
			//System.out.print("boi");
		}
		//Connects selected wire
		else if(panel.wireDrawFlag && role && e.getButton() == 1 && connection == null){
			connection = panel.curDrawnWire;
			connection.nodeOut = this;
			setState(connection.isOn);
			
			panel.curDrawnWire.nodeIn.selected = false;
			
			panel.wireDrawFlag = false;
			panel.curDrawnWire = null;
			panel.drawingConnPoint = null;
			
			//System.out.print("boi2");
			panel.wires.add(connection);
		}
			
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
}
