package net.mybecks.duffy.ui.table;

import javax.swing.table.AbstractTableModel;

import net.mybecks.duffy.pojo.Settings;

public class TableModel extends AbstractTableModel{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -5068690632902944617L;

	private String[] columnNames = { "npm Package", "Local Version", "Remote Version" };
	
	private Object[][] data = Settings.getInstance().getPackagesAsMultiDimArray();
	
	public int getRowCount() {
		return data.length;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public Object getValueAt(int row, int column) {
		return data[row][column];
	}

	// Used by the JTable object to set the column names
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	// Used by the JTable object to render different
	// functionality based on the data type
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	public void updateData(){
		this.data = Settings.getInstance().getPackagesAsMultiDimArray();
	}
}
