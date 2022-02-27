package view;

import java.awt.event.MouseAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.events.MouseEvent;

import controller.Liste_des_commandes_a_delivrerControl;

public class Liste_des_commandes_a_delivrerView {
	
	Liste_des_commandes_a_delivrerView() throws SQLException
	{
		/*JFrame frame;*/
		JTable table = new JTable();
		Vector<String> rowHeader = new Vector<String> ();      
		  rowHeader.add ("id_commande");  
		  rowHeader.add ("date_creation_commande");  
		  rowHeader.add ("id_entreprise");    
		  
		  DefaultTableModel model = new DefaultTableModel(rowHeader,0);     
		  table.setModel(model); 
		  
		  table.addMouseListener(new MouseAdapter()  {
		    	 
	          public void mouseClicked(MouseEvent me) {
	        	  
	        	  System.out.println("test");
	             
	             }
	          
	       });
	  
		  Liste_des_commandes_a_delivrerControl commandes = new Liste_des_commandes_a_delivrerControl();  
	   ResultSet res= commandes.selectCommandes();
	   
	   Vector<String> rowData;      
	   if (res != null) while (res.next()){  
	    rowData = new Vector<String>() ;  
	    rowData.add (res.getString("id_commande"));  
	    rowData.add (res.getString("date_creation_commande")); 
	    rowData.add (res.getString("fk_id_entreprise"));  
	    
	     
	    model.addRow(rowData);  
	  
	   }  
	   
	      //table.setNextFocusableComponent(table);;
	      table.setShowGrid(true);
	      table.setShowVerticalLines(true);
	      
	      
	      
	      JScrollPane pane = new JScrollPane(table);
	      JFrame f = new JFrame("Populate JTable from Database");
	      JPanel panel = new JPanel();
	     // panel.add(table);
	      panel.add(pane);
	      f.add(panel);
	      f.setSize(500, 400);
	      f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      f.setVisible(true);
	   
	      res.close();
// rendre la tablecliquable
	      
	      	   
	}
	/*

	 public static void main(String[] args) throws SQLException
	    {
	       
		
		 new Liste_des_commandes_a_delivrerView();
	    }
     */

}


