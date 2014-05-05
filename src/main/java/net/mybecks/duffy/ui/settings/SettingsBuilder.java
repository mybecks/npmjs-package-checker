package net.mybecks.duffy.ui.settings;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.mybecks.duffy.pojo.Settings;
import net.mybecks.duffy.util.FileHandler;
import net.mybecks.duffy.util.JsonParser;
import net.mybecks.duffy.util.SettingsHandler;

public class SettingsBuilder implements ActionListener {
	
	private static final Logger LOG = LoggerFactory.getLogger(SettingsBuilder.class);	
	private JFrame frame = new JFrame();
	private JButton btnFC;
	private JButton btnSave;
	private JTextField tfPackages;
	private JFileChooser fc;
	private JLabel lblPackages;

	public SettingsBuilder() {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Preferences");
		frame.setPreferredSize(new Dimension(400, 300));
		frame.setLocationRelativeTo(null);
		fc = new JFileChooser();
		lblPackages = new JLabel("Package.json:");
		tfPackages = new JTextField();
		btnFC = new JButton("...");
		btnSave = new JButton("Save");
	}

	public void createAndShowSettings() {
		fc.setFileFilter(new CustomFileFilter());
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		if(Settings.getInstance().getPackageJsonPath() != ""){
			tfPackages.setText(Settings.getInstance().getPackageJsonPath());
		}
		tfPackages.setEditable(false);
		
		
		
		btnFC.addActionListener(this);
		btnSave.addActionListener(this);
		
		
		
		
		
		GroupLayout layout = new GroupLayout(frame.getContentPane());
		frame.getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addComponent(lblPackages)
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.LEADING).addComponent(
								tfPackages))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.LEADING)
								.addComponent(btnFC).addComponent(btnSave)));

		layout.linkSize(SwingConstants.HORIZONTAL, btnFC, btnSave);

		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(lblPackages)
								.addComponent(tfPackages).addComponent(btnFC))
				.addComponent(btnSave));

		// Display the window.
		frame.pack();
		LOG.info("Show Settings Dialog");
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFC) {

			int retVal = fc.showOpenDialog(btnFC);
			if (retVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				tfPackages.setText(file.getAbsolutePath());
				LOG.info("package.json file loaded");
				// save to settings helper
			} else {
				// log.append("Open command cancelled by user." + newline);
			}

		}

		if (e.getSource() == btnSave) {
			LOG.info("Button 'Save' pressed");
			Settings.getInstance().setPackageJsonPath(tfPackages.getText());
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					SettingsHandler.refreshData();
				}
			}).start();			
						
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}

	}
}
