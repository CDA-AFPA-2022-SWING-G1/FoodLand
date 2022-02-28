package outils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class MyJTable_EnTest<T> {
	
	private JTable table;
	private DefaultTableModel dtm;
	private boolean isEditable;
	
	public MyJTable_EnTest(JTable table, DefaultTableModel dtm, boolean isEditable) {
		this.table = table;
		this.dtm = dtm;
		this.isEditable = isEditable;
	}

	public JTable generateTable(ArrayList<String> cols, Vector<T> lines) {
		
		for(int i = 0; i <  cols.size(); i++) {
			String col = (String) cols.get(i);
			dtm.addColumn(col);
		}
		
		for(int i = 0; i < lines.size(); i++) {
			Vector row =  new Vector<>();
			T obj = lines.get(i);
			System.out.println("contenu de T: "+ obj.toString());
			Class<?> c = obj.getClass();
			
			try {
				Method[] mts = c.getMethods();
				Method[] mtsGet;
				for(Method m : mts) {
					if(m.getName().startsWith("get")) {
					 System.out.println(m.getName());
					 
					try {
						System.out.println(cols);
						Object o = new Object();
						o = m.invoke(obj, null);
						row.add(o);
						System.out.println(row);
						
						
						System.out.println(o);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					}
				}
				
				//System.out.println(Arrays.toString(mts));
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("classe de T :"+ c);
			

		dtm.addRow(row);
		}
		table.setModel(dtm);
		dtm.addTableModelListener(table);
		return table;
	}
	
	
}
