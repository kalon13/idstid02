import java.awt.Component;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;



public class Table extends AbstractTableModel {

	private static String[] _columnNames;
	private static List<String> title;
	private static Object[][] _data;
	private JTable table;
	
	public Table() {
		
	}	
	public Table(String[] _columnNames, Object[][] _data) {
		this._columnNames = _columnNames;
		this._data = _data;
	}
	
	public Table(List<String> title, Object[][] _data) {
		this._data = _data;
		this.title = title;
		setTitles(title) ;
	}
	
	//setta i column name della table
	private void setTitles(List<String> title) {
		//String[] _columnNames
		 Iterator it=title.iterator();
		 int cntTit = title.size();
		 _columnNames = new String[cntTit];
		 int k=0;
		  while(it.hasNext())
	        {//[riga][colonna]
	          _columnNames[k] = (String) it.next();
	           k++;
	        }
	}
	public int getColumnCount() {
	        return _columnNames.length;
	}
	
	public String getColumnName(int col) {
	       return _columnNames[col];
	}
	
	public Object getValueAt(int row, int col) {
	        return _data[row][col];
	}
	
	public Class getColumnClass(int c) {
	        return getValueAt(0, c).getClass();
	}
	    
	public void setValueAt(Object value, int row, int col) {
	    _data[row][col] = value;
	    fireTableCellUpdated(row, col);
	 }
	 //Create Table
	public Component createTable(){
		JTable table = new JTable(_data, _columnNames);
		table.getTableHeader().setVisible(true);
		return table;
	 }
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}
}
