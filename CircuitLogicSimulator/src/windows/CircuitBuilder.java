package windows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import components.CircuitCanvas;
import components.CircuitComponent;
import components.ExitPoint;
import components.Wire;
import gateLogic.GeneratedLogic;
import gateLogic.BuiltInLogic.AndLogic;
import gateLogic.BuiltInLogic.NandLogic;
import gateLogic.BuiltInLogic.NorLogic;
import gateLogic.BuiltInLogic.NotLogic;
import gateLogic.BuiltInLogic.OrLogic;
import gateLogic.BuiltInLogic.SplitGateLogic;
import gateLogic.BuiltInLogic.XnorLogic;
import gateLogic.BuiltInLogic.XorLogic;
import windows.dialogs.CircuitCanvasMismatch;
import windows.dialogs.LoadBoard;
import windows.dialogs.SaveBoard;
import windows.dialogs.SaveBoardAsGate;

import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JLabel;
import javax.swing.JComboBox;

public class CircuitBuilder extends JFrame {
	
	public String version = "0.0.0.0000";
	public String versionName = " ";

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public CircuitCanvas circuitCanvas;
	JComboBox<String> cmbGeneratedGates;
	JLabel lblEntryCount;
	JLabel lblExitCount;
	
	public CircuitBuilder() {
		createSaveFolders();
		readVersion();
		setTitle("Circuit Builder (" + versionName + " - " + version + ")");
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(CircuitBuilder.class.getResource("/resources/images/Circuit.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		circuitCanvas = new CircuitCanvas();
		circuitCanvas.setBounds(40, 250, 1000, 400);
		contentPane.add(circuitCanvas);
		circuitCanvas.setOpaque(true);
		
		JButton btnAddEntry = new JButton("+");
		btnAddEntry.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				circuitCanvas.addEntryPoint();
				updateEntryCount();
			}
		});
		btnAddEntry.setBounds(25, 216, 41, 23);
		contentPane.add(btnAddEntry);
		
		JButton btnRemoveEntry = new JButton("-");
		btnRemoveEntry.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				circuitCanvas.removeEntryPoint(false);
				updateEntryCount();
			}
		});
		btnRemoveEntry.setBounds(66, 216, 41, 23);
		contentPane.add(btnRemoveEntry);
		
		JButton btnAddExit = new JButton("+");
		btnAddExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				circuitCanvas.addExitPoint();
				updateExitCount();
			}
		});
		btnAddExit.setBounds(973, 216, 41, 23);
		contentPane.add(btnAddExit);
		
		JButton btnRemoveExit = new JButton("-");
		btnRemoveExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				circuitCanvas.removeExitPoint(false);
				updateExitCount();
			}
		});
		btnRemoveExit.setBounds(1015, 216, 41, 23);
		contentPane.add(btnRemoveExit);
		
		JButton btnAddAND = new JButton("AND");
		btnAddAND.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				circuitCanvas.addCircuitComponent(new AndLogic());
			}
		});
		btnAddAND.setBounds(25, 27, 89, 23);
		contentPane.add(btnAddAND);
		
		JLabel lblBuiltIns = new JLabel("Select a gate to add");
		lblBuiltIns.setBounds(25, 11, 113, 14);
		contentPane.add(lblBuiltIns);
		
		JButton btnAddOR = new JButton("OR");
		btnAddOR.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				circuitCanvas.addCircuitComponent(new OrLogic());
			}
		});
		btnAddOR.setBounds(25, 52, 89, 23);
		contentPane.add(btnAddOR);
		
		JButton btnAddNOT = new JButton("NOT");
		btnAddNOT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				circuitCanvas.addCircuitComponent(new NotLogic());
			}
		});
		btnAddNOT.setBounds(25, 78, 89, 23);
		contentPane.add(btnAddNOT);
		
		JButton btnAddSPLIT = new JButton("SPLIT");
		btnAddSPLIT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				circuitCanvas.addCircuitComponent(new SplitGateLogic());
			}
		});
		btnAddSPLIT.setBounds(25, 105, 89, 23);
		contentPane.add(btnAddSPLIT);
		
		JButton btnAddNAND = new JButton("NAND");
		btnAddNAND.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				circuitCanvas.addCircuitComponent(new NandLogic());
			}
		});
		btnAddNAND.setBounds(115, 27, 89, 23);
		contentPane.add(btnAddNAND);
		
		JButton btnAddNOR = new JButton("NOR");
		btnAddNOR.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				circuitCanvas.addCircuitComponent(new NorLogic());
			}
		});
		btnAddNOR.setBounds(115, 52, 89, 23);
		contentPane.add(btnAddNOR);
		
		JButton btnAddXOR = new JButton("XOR");
		btnAddXOR.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				circuitCanvas.addCircuitComponent(new XorLogic());
			}
		});
		btnAddXOR.setBounds(115, 78, 89, 23);
		contentPane.add(btnAddXOR);
		
		JButton btnAddXNOR = new JButton("XNOR");
		btnAddXNOR.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				circuitCanvas.addCircuitComponent(new XnorLogic());
			}
		});
		btnAddXNOR.setBounds(115, 105, 89, 23);
		contentPane.add(btnAddXNOR);
		
		cmbGeneratedGates = new JComboBox<String>();
		cmbGeneratedGates.setBounds(214, 52, 107, 22);
		
		updateSavedGates();

		contentPane.add(cmbGeneratedGates);
		
		JButton btnAddGeneratedGate = new JButton("Add Custom");
		btnAddGeneratedGate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				circuitCanvas.addCircuitComponent(new GeneratedLogic((String)cmbGeneratedGates.getSelectedItem()));
			}
		});
		btnAddGeneratedGate.setBounds(214, 27, 107, 23);
		contentPane.add(btnAddGeneratedGate);
		
		JButton btnSaveBoard = new JButton("Save Board");
		btnSaveBoard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openSaveBoardWindow();
			}
		});
		btnSaveBoard.setBounds(908, 52, 148, 23);
		contentPane.add(btnSaveBoard);
		
		JButton btnLoadBoard = new JButton("Load Board");
		btnLoadBoard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openLoadBoardWindow();
			}
		});
		btnLoadBoard.setBounds(908, 78, 148, 23);
		contentPane.add(btnLoadBoard);
		
		JButton btnSaveGate = new JButton("Save Board as Gate");
		btnSaveGate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openSaveGateWindow();
			}
		});
		btnSaveGate.setBounds(908, 27, 148, 23);
		contentPane.add(btnSaveGate);
		
		lblEntryCount = new JLabel("2");
		lblEntryCount.setBounds(110, 220, 46, 14);
		contentPane.add(lblEntryCount);
		
		lblExitCount = new JLabel("1");
		lblExitCount.setBounds(920, 220, 46, 14);
		contentPane.add(lblExitCount);
	}
	
	public void saveBoard(String fileName) {
		try {
			FileOutputStream fos = new FileOutputStream(System.getProperty("user.home") + "\\Documents\\CLS\\SavedBoards\\" + fileName + ".ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(circuitCanvas);
			oos.close();
		}
		catch(IOException ex){ex.printStackTrace();}
	}
	
	public void loadBoard(String fileName) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(System.getProperty("user.home") + "\\Documents\\CLS\\SavedBoards\\" + fileName));
			
			CircuitCanvas loadedCanvas = (CircuitCanvas)ois.readObject();
			
			ois.close();							
			
			circuitCanvas.clearAllComponents();
			
			for(int i = 0; i < loadedCanvas.entryPoints.size(); i++) {
				circuitCanvas.addEntryPoint();
				circuitCanvas.entryPoints.get(i).uniquieID = loadedCanvas.entryPoints.get(i).calculateUniqueID();
			}
				
			
			for(int i = 0; i < loadedCanvas.exitPoints.size(); i++){
				circuitCanvas.addExitPoint();
				circuitCanvas.exitPoints.get(i).uniquieID = loadedCanvas.exitPoints.get(i).calculateUniqueID();
			}
			
			for(int i = 0; i < loadedCanvas.components.size(); i++) {
				circuitCanvas.addCircuitComponent(loadedCanvas.components.get(i).gateLogic);
				circuitCanvas.components.get(i).x = loadedCanvas.components.get(i).x;
				circuitCanvas.components.get(i).y = loadedCanvas.components.get(i).y;
				circuitCanvas.components.get(i).uniquieID = loadedCanvas.components.get(i).calculateUniqueID();
			}
				
			for(int i = 0; i < circuitCanvas.entryPoints.size(); i++) {
				double connectedID = loadedCanvas.entryPoints.get(i).nodeOut.connection.nodeOut.parent.calculateUniqueID();
				int nodePosIndex = loadedCanvas.entryPoints.get(i).nodeOut.connection.nodeOut.parentPositionIndex;
				circuitCanvas.entryPoints.get(i).nodeOut.connection = new Wire();
				circuitCanvas.entryPoints.get(i).nodeOut.connection.nodeIn = circuitCanvas.entryPoints.get(i).nodeOut;
				
				CircuitComponent tempComponent = getCompByUniqueID(connectedID);				
				if(tempComponent != null) {
						tempComponent.inNodes.get(nodePosIndex).connection = circuitCanvas.entryPoints.get(i).nodeOut.connection;
						circuitCanvas.entryPoints.get(i).nodeOut.connection.nodeOut = tempComponent.inNodes.get(nodePosIndex);
					}
				else {
					ExitPoint temExitPoint = getExitPointByUniqueID(connectedID);
					circuitCanvas.entryPoints.get(i).nodeOut.connection.nodeOut = temExitPoint.nodeIn;
					temExitPoint.nodeIn.connection = circuitCanvas.entryPoints.get(i).nodeOut.connection;
				}
				circuitCanvas.wires.add(circuitCanvas.entryPoints.get(i).nodeOut.connection);
			}
			
			for(int i = 0; i < circuitCanvas.components.size(); i++) {
				for(int j = 0; j < circuitCanvas.components.get(i).outNodes.size(); j++) {
					double connectedID = loadedCanvas.components.get(i).outNodes.get(j).connection.nodeOut.parent.calculateUniqueID();
					int nodePosIndex = loadedCanvas.components.get(i).outNodes.get(j).connection.nodeOut.parentPositionIndex;
					
					circuitCanvas.components.get(i).outNodes.get(j).connection = new Wire();
					circuitCanvas.components.get(i).outNodes.get(j).connection.nodeIn = circuitCanvas.components.get(i).outNodes.get(j);
					
					CircuitComponent tempComponent = getCompByUniqueID(connectedID);					
					if(tempComponent != null) {
						tempComponent.inNodes.get(nodePosIndex).connection = circuitCanvas.components.get(i).outNodes.get(j).connection;
						circuitCanvas.components.get(i).outNodes.get(j).connection.nodeOut = tempComponent.inNodes.get(nodePosIndex);
					}	
					else {
						ExitPoint temExitPoint = getExitPointByUniqueID(connectedID);
						circuitCanvas.components.get(i).outNodes.get(j).connection.nodeOut = temExitPoint.nodeIn;
						temExitPoint.nodeIn.connection = circuitCanvas.components.get(i).outNodes.get(j).connection;
					}
					circuitCanvas.wires.add(circuitCanvas.components.get(i).outNodes.get(j).connection);
				}
			}
			
			for(int i = 0; i < circuitCanvas.entryPoints.size(); i++)
				circuitCanvas.entryPoints.get(i).setState(true);
				
			circuitCanvas.wireDrawFlag = loadedCanvas.wireDrawFlag;
			circuitCanvas.curDrawnWire = loadedCanvas.curDrawnWire;
			
			circuitCanvas.removeAll();
			circuitCanvas.revalidate();
			circuitCanvas.repaint();		
		}
		catch(Exception ex){
			ex.printStackTrace();
			CircuitCanvasMismatch newWindow = new CircuitCanvasMismatch();
			newWindow.setVisible(true);
			newWindow.setLocationRelativeTo(null);
			}
	}
	
	public ExitPoint getExitPointByUniqueID(double ID){
		for(int i = 0; i < circuitCanvas.exitPoints.size(); i++)
			if(circuitCanvas.exitPoints.get(i).uniquieID == ID)
				return circuitCanvas.exitPoints.get(i);
		return null;
	}
	
	public CircuitComponent getCompByUniqueID(double ID){
		for(int i = 0; i < circuitCanvas.components.size(); i++)
			if(circuitCanvas.components.get(i).uniquieID == ID)
				return circuitCanvas.components.get(i);
		return null;
	}
	
	public void readVersion() {
		File verFile = new File(System.getProperty("user.home") + "\\Documents\\CLS\\Version.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(verFile));
			
			version = reader.readLine();
			versionName = reader.readLine();
			
			reader.close();
			
		} catch (IOException e) {
			createSaveFolders();
			System.out.print("No version file\n");
		}
	}
	
	public void updateEntryCount() {
		lblEntryCount.setText(Integer.toString(circuitCanvas.entryPoints.size()));
	}
	
	public void updateExitCount() {
		lblExitCount.setText(Integer.toString(circuitCanvas.exitPoints.size()));
	}
	
	public void updateSavedGates() {
		cmbGeneratedGates.removeAllItems();
		String[] Saves = new File(System.getProperty("user.home") + "\\Documents\\CLS\\savedGates").list();	
		if(Saves.length - 1 < 0 || Saves == null)
			cmbGeneratedGates.addItem("None");
		else {
			for(String save : Saves) {				
				if(save.split("\\.")[1].equals("txt")) {
					File verFile = new File(System.getProperty("user.home") + "\\Documents\\CLS\\savedGates\\" + save);
					try {
						BufferedReader reader = new BufferedReader(new FileReader(verFile));
						
						if(!reader.readLine().split("\\.")[1].equals(version.split("\\.")[1])) {
							reader.close();
							continue;
						}
						
						reader.close();
						
					} catch (IOException e) {
						createSaveFolders();
						System.out.print("No file found\n");
						cmbGeneratedGates.addItem("None");
						break;
					}
					
					cmbGeneratedGates.addItem(save.split("\\.")[0]);
				}
					
			}
			
			if(cmbGeneratedGates.getItemCount() == 0)
				cmbGeneratedGates.addItem("None");
		}
	}
	
	public void createSaveFolders() {
		File test = new File(System.getProperty("user.home") + "\\Documents\\CLS");
		test.mkdir();
		test = new File(System.getProperty("user.home") + "\\Documents\\CLS\\savedGates\\");
		test.mkdir();
		test = new File(System.getProperty("user.home") + "\\Documents\\CLS\\savedBoards\\");
		test.mkdir();
	}
	
	private void openSaveGateWindow() {
		SaveBoardAsGate newWindow = new SaveBoardAsGate(this);
		newWindow.setVisible(true);
		newWindow.setLocationRelativeTo(null);
	}
	
	private void openSaveBoardWindow() {
		SaveBoard newWindow = new SaveBoard(this);
		newWindow.setVisible(true);
		newWindow.setLocationRelativeTo(null);
	}
	
	private void openLoadBoardWindow() {
		LoadBoard newWindow = new LoadBoard(this);
		newWindow.setVisible(true);
		newWindow.setLocationRelativeTo(null);
	}
}
