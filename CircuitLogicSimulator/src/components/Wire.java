package components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.CubicCurve2D;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Wire extends JComponent {
	
	//Drawing parameters
	public int heigth = 3;
	
	//Colors for drawing
	public Color onColor = Color.ORANGE;
	public Color offColor = Color.DARK_GRAY;	
	
	//Current state of the wire
	public boolean isOn = false;
	
	//Connection nodes
	public ConnectionPoint nodeIn = null;
	public ConnectionPoint nodeOut = null;
	
	public Wire() {
		this.setOpaque(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(nodeIn == null || nodeOut == null)
			return;
		Graphics2D g2d = (Graphics2D) g;
		
		if(this.isOn)
			g2d.setColor(onColor);
		else
			g2d.setColor(offColor);
		g2d.setStroke(new BasicStroke(this.heigth));
		
		if(nodeIn.wireConnPointY > nodeOut.wireConnPointY) {
			CubicCurve2D curve2d = new CubicCurve2D.Double();
			curve2d.setCurve(nodeIn.wireConnPointX, nodeIn.wireConnPointY,
					nodeIn.wireConnPointX + 20, nodeIn.wireConnPointY + 40,
					nodeOut.wireConnPointX - 20, nodeOut.wireConnPointY - 40,
					nodeOut.wireConnPointX, nodeOut.wireConnPointY);
			g2d.draw(curve2d);
		}
		else {
			CubicCurve2D curve2d = new CubicCurve2D.Double();
			curve2d.setCurve(nodeIn.wireConnPointX, nodeIn.wireConnPointY,
					nodeIn.wireConnPointX - 20, nodeIn.wireConnPointY - 40,
					nodeOut.wireConnPointX + 20, nodeOut.wireConnPointY + 40,
					nodeOut.wireConnPointX, nodeOut.wireConnPointY);
			g2d.draw(curve2d);
		}
	}
	
	public void removeOperations() {
		setState(false);
		nodeIn.connection = null;
		nodeIn = null;
		if(nodeOut != null)
			nodeOut.connection = null;
		nodeOut = null;
		this.removeAll();
	}
	
	public void setState(boolean state) {
		isOn = state;
		if(nodeOut != null)
			nodeOut.setState(state);
	}
}
