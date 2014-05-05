package net.mybecks.duffy.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.mybecks.duffy.ui.settings.SettingsBuilder;
import net.mybecks.duffy.util.SettingsHandler;

public class MenuBuilder implements ActionListener {
	
	private static final Logger LOG = LoggerFactory.getLogger(MenuBuilder.class);	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenu menuRefresh;
	private JMenuItem miPreferences;
	private JMenuItem miRefresh;
	private JMenuItem miQuit;
	private JFrame frame;
	
	public MenuBuilder(JFrame frame) {
		this.frame = frame;
	}

	public JMenuBar getMenuBar() {
		// Create the menu bar.
		menuBar = new JMenuBar();

		// Build the first menu.
		menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
				"The only menu in this program that has menu items");
		menuBar.add(menu);

		// a group of JMenuItems
		miPreferences = new JMenuItem("Preferences", KeyEvent.VK_1);
		miPreferences.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
				ActionEvent.ALT_MASK));
		miPreferences.getAccessibleContext().setAccessibleDescription(
				"This doesn't really do anything");
		miPreferences.addActionListener(this);
		menu.add(miPreferences);
		
		miRefresh = new JMenuItem("Refresh", KeyEvent.VK_R);
		miRefresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				ActionEvent.ALT_MASK));
		miRefresh.addActionListener(this);
		menu.add(miRefresh);
		
		
		miQuit = new JMenuItem("Quit", KeyEvent.VK_Q);
		miQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				ActionEvent.ALT_MASK));
		miQuit.addActionListener(this);
		menu.add(miQuit);
		
		menuRefresh = new JMenu("Refresh");
		menuRefresh.setMnemonic(KeyEvent.VK_R);
		menuRefresh.getAccessibleContext().setAccessibleDescription(
				"The only menu in this program that has menu items");
//		menuRefresh.addMenuListener(new MenuListener() {
//			
//			@Override
//			public void menuSelected(MenuEvent e) {
//				JOptionPane.showMessageDialog(null,
//						"Refresh");
//			}
//			
//			@Override
//			public void menuDeselected(MenuEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void menuCanceled(MenuEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		});;
		menuBar.add(menuRefresh);
		
		
		return menuBar;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == miPreferences) {
			new SettingsBuilder().createAndShowSettings();
		}
		
		if (e.getSource() == miRefresh) {
			LOG.info("Refresh data");
			SettingsHandler.refreshData();
		}
		
		if (e.getSource() == miQuit) {
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}

	}

}
