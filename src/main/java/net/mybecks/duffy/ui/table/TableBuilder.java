package net.mybecks.duffy.ui.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import net.mybecks.duffy.pojo.Settings;
import net.mybecks.duffy.util.SystemUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TableBuilder {
	private static final Logger LOG = LoggerFactory.getLogger(TableBuilder.class);	
	private JTable table;
	private static TableModel tableModel = new TableModel();
//	private static DefaultTableModel tableModel = new DefaultTableModel();
	
	public TableBuilder(){
		
	}
	
	public JTable getTable(){
		// Create the JTable using the TableModel
//		String[] columnNames = { "npm Package", "Local Version", "Remote Version" };
//		tableModel.setColumnIdentifiers(columnNames);
//		tableModel.setColumnCount(0);
		table = new JTable(tableModel){

			private static final long serialVersionUID = 1L;

			public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
			{
				Component c = super.prepareRenderer(renderer, row, column);

				//  Color row based on a cell value

				if (!isRowSelected(row))
				{
					c.setBackground(getBackground());
					int modelRow = convertRowIndexToModel(row);
					String local = (String)getModel().getValueAt(modelRow, 1);
					String remote = (String)getModel().getValueAt(modelRow, 2);
					
					if(!local.equals(remote)){
						c.setBackground(Color.RED);
					}
				}

				return c;
			}
		};
//		table.setAutoCreateRowSorter(true);	
		
		table.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
			     if (e.getClickCount() == 2) { // check if a double click
			    	 
			    	 JTable table =(JTable) e.getSource();
			         Point p = e.getPoint();
			         int row = table.rowAtPoint(p);
			         String name = (String) table.getModel().getValueAt(row, 0);
			    	 
			         String packageUrl = Settings.getInstance().getRepositoryUrl() + name;
			         
			         LOG.info("Open Browser with following Url: "+packageUrl); 
			         
			         try {
						SystemUtils.openUrlInDefaultBrowser(packageUrl);
					} catch (IOException | URISyntaxException e1) {
						e1.printStackTrace();
					}
			         
			         
//			    	 JOptionPane.showMessageDialog(null,
//								name);
			     }
			   }
			});
	
		
		table.setGridColor(Color.GRAY);
		
		return table; 
	}
	
	public static void refreshTableData(){
		
//		for(int i=0; i<Settings.getInstance().getPackages().size(); i++){
//			String packageName = Settings.getInstance().getPackages().get(i).getName();
//			String localVersion = Settings.getInstance().getPackages().get(i).getLocalVersion();
//			String remoteVersion = Settings.getInstance().getPackages().get(i).getRemoteVersion();
//			Object[] data = {packageName, localVersion, remoteVersion};
//			tableModel.addRow(data);
//		}
		tableModel.updateData();
		tableModel.fireTableDataChanged();
	}
	
	public static void clearTableData(){
//		tableModel.setRowCount(0);
		tableModel.fireTableDataChanged();
//		tableModel = new TableModel();
	}
}