package net.mybecks.duffy.ui.table;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;
	ImageIcon icon = null;    

    ImageRenderer(String iconName) {
        icon = new ImageIcon(getClass().getResource(iconName));
    }
}
