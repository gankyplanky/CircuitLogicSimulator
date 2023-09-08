package components;

import javax.swing.JComponent;

public interface CommonComponentTemplate {
	
	public double calculateUniqueID();
	public int getConnPointX(int positionIndex, boolean isEntry);
	public int getConnPointY(int positionIndex, boolean isEntry);
	public void setState(boolean state);
	public JComponent getParent();
}
