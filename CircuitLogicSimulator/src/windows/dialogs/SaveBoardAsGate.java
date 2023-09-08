package windows.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.CircuitCanvas;
import components.EntryPoint;
import windows.CircuitBuilder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class SaveBoardAsGate extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtInputName;
	private String filePath = "";

	public SaveBoardAsGate(CircuitBuilder parent) {
		setBounds(100, 100, 452, 147);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			txtInputName = new JTextField();
			txtInputName.setBounds(175, 10, 86, 20);
			contentPanel.add(txtInputName);
			txtInputName.setColumns(10);
		}
		
		JLabel lblName = new JLabel("Name the gate:");
		lblName.setBounds(84, 13, 81, 14);
		contentPanel.add(lblName);
		
		JLabel lblAlert = new JLabel("Already exists");
		lblAlert.setEnabled(false);
		lblAlert.setVisible(false);
		lblAlert.setBounds(266, 13, 122, 14);
		contentPanel.add(lblAlert);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						File newSave = new File(System.getProperty("user.home") + "\\Documents\\CLS\\savedGates\\" + txtInputName.getText() + ".txt");
						try {
							if(newSave.createNewFile()) {
								filePath = newSave.getPath();
								
								for(EntryPoint ep : parent.circuitCanvas.entryPoints)
									ep.setState(false);
								
								FileWriter saveWriter = new FileWriter(filePath, false);
								saveWriter.write(parent.version + "\n");
								saveWriter.close();
								
								generateSave(parent.circuitCanvas, 0);
								parent.updateSavedGates();
								parent.toFront();
								dispose();
							}
							else {
								txtInputName.setText("");
								lblAlert.setVisible(true);
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						parent.toFront();
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void generateSave(CircuitCanvas board, int position) {
		if(position == board.entryPoints.size()) {
			saveFile(board);
		}
		else {
			board.entryPoints.get(position).setState(false);
			generateSave(board, position + 1);
			board.entryPoints.get(position).setState(true);
			generateSave(board, position + 1);
		}	
	}
	
	private void saveFile(CircuitCanvas board) {
		String tempEntryState = "";
		String tempExitState = "";
		
		int entryLimit = board.entryPoints.size();
		int exitLimit = board.exitPoints.size();
		
		for(int i = 0; i < entryLimit || i < exitLimit; i++) {
			if(i < entryLimit)
				tempEntryState += board.entryPoints.get(i).isOn ? "1" : "0";
			if(i < exitLimit)
				tempExitState += board.exitPoints.get(i).isOn ? "1" : "0";
		}
		
		try {
			FileWriter saveWriter = new FileWriter(filePath, true);
			saveWriter.write(tempEntryState + "|" + tempExitState + "\n");
			saveWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
