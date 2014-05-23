package net.mybecks.duffy.ui.settings;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

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
	private JButton btnFCNodeHome;
	private JButton btnSave;
	private JTextField tfPackages;
	private JLabel lblPackages;
	private JLabel lblNodeHome;
	private JTextField tfNodeHome;

	public SettingsBuilder() {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Preferences");
		frame.setPreferredSize(new Dimension(400, 300));
		frame.setLocationRelativeTo(null);
		
		lblPackages = new JLabel("Package.json:");
		tfPackages = new JTextField();
		lblNodeHome = new JLabel("Node.js Home");
		tfNodeHome = new JTextField();
		btnFCNodeHome = new JButton("...");
		btnFC = new JButton("...");
		btnSave = new JButton("Save");
	}

	public void createAndShowSettings() {
		
		
		if(Settings.getInstance().getPackageJsonPath() != ""){
			tfPackages.setText(Settings.getInstance().getPackageJsonPath());
		}
		tfPackages.setEditable(false);
		tfNodeHome.setEditable(false);
//		Map<String, String> var = System.getenv();//System.getenv("NODE_JS");
		
		btnFC.addActionListener(this);
		btnFCNodeHome.addActionListener(this);
		btnSave.addActionListener(this);
		
		
		
		
		
		GroupLayout layout = new GroupLayout(frame.getContentPane());
		frame.getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.LEADING)
								.addComponent(lblPackages)
								.addComponent(lblNodeHome))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.LEADING)
								.addComponent(tfPackages)
								.addComponent(tfNodeHome))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.LEADING)
								.addComponent(btnFC)
								.addComponent(btnFCNodeHome)
								.addComponent(btnSave)));

		layout.linkSize(SwingConstants.HORIZONTAL, btnFC, btnFCNodeHome, btnSave);

		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(lblPackages)
								.addComponent(tfPackages)
								.addComponent(btnFC))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(lblNodeHome)
								.addComponent(tfNodeHome)
								.addComponent(btnFCNodeHome))
				.addComponent(btnSave));

		// Display the window.
		frame.pack();
		LOG.info("Show Settings Dialog");
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JFileChooser fc;
		
		if (e.getSource() == btnFC) {
			fc = new JFileChooser();
			fc.setFileFilter(new CustomFileFilter());
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
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
		
		if (e.getSource() == btnFCNodeHome) {
			fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int retVal = fc.showOpenDialog(btnFCNodeHome);
			if (retVal == JFileChooser.APPROVE_OPTION) {
				
				LOG.info("node home loaded");
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
