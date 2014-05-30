package net.mybecks.duffy.ui;

import java.awt.Dimension;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.mybecks.duffy.ui.menu.MenuBuilder;
import net.mybecks.duffy.ui.table.TableBuilder;

public class UiBuilder {
	private static JFrame frame = new JFrame();
	private static JLabel lblLastUpdatedText;
//	public static BusyIndicator layerUI;

	public UiBuilder() {

	}

	public static void createAndShowUI() {
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("npm.js Package Update");
		// frame.setIconImage(new
		// ImageIcon(frame.getClass().getResource("/resource/icon.png")).getImage());
		frame.setPreferredSize(new Dimension(600, 500));
		frame.setLocationRelativeTo(null);
		frame.setJMenuBar(new MenuBuilder(frame).getMenuBar());
		
//		layerUI = new BusyIndicator();
//		JPanel panel = new JPanel() {
//
//            @Override
//            public Dimension getPreferredSize() {
//                return new Dimension(400, 300);
//            }
//        };
//        JLayer<JPanel> jlayer = new JLayer<JPanel>(panel, layerUI);
//		frame.add(jlayer);
		
		JTable table = new TableBuilder().getTable();
		JScrollPane scrollPane = new JScrollPane(table);
		lblLastUpdatedText = new JLabel("Last updated on: ");
//		lblLastUpdatedValue = new JLabel("time");

		GroupLayout layout = new GroupLayout(frame.getContentPane());
		frame.getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createSequentialGroup().addGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(scrollPane)
						.addGroup(
								layout.createSequentialGroup().addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addComponent(
														lblLastUpdatedText)))));

		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addComponent(scrollPane)
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.LEADING).addGroup(
								layout.createSequentialGroup().addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addComponent(
														lblLastUpdatedText)))));

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void updateLastUpdatedTime(){
		String lastUpdated = lblLastUpdatedText.getText().substring(0, 17);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		lastUpdated+=dateFormat.format(date);
		lblLastUpdatedText.setText(lastUpdated);
	}
}
