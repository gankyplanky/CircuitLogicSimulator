package windows.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import windows.CircuitBuilder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class LoadBoard extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> cmbInput;
	
	public LoadBoard(CircuitBuilder parent) {
		setTitle("Save Board");
		setBounds(100, 100, 305, 128);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name the Board");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(10, 14, 100, 14);
		contentPanel.add(lblNewLabel);
		
		cmbInput = new JComboBox<String>();
		
		String[] Saves = new File(System.getProperty("user.home") + "\\Documents\\CLS\\savedBoards").list();	
		if(Saves.length - 1 < 0 || Saves == null)
			cmbInput.addItem("None");
		else {
			for(String save : Saves) {									
					cmbInput.addItem(save);
				}
			}	
		
		cmbInput.setBounds(114, 10, 140, 22);
		contentPanel.add(cmbInput);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						parent.loadBoard(cmbInput.getSelectedItem().toString());
						dispose();
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
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}