package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import outils.ConnectionDB;

public class DaoColumns {

	
	private Connection c;
	private ConnectionDB conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DaoColumns() {
		conn = new ConnectionDB();
		this.c = conn.getConnectionDB();
	}

	// permet d'obtenir les noms des colonnes d'une table cible 
	public ArrayList<String> columnsTable(String nomBase, String nomTable){
		
		ArrayList<String> listCols = new ArrayList<>();
		try {
			stmt = c.createStatement();
			String q = "SHOW COLUMNS FROM "+ nomBase + "."+ nomTable + ";";
			
			rs = stmt.executeQuery(q);
			
			while(rs.next()) {
			String col = rs.getString("field");
			// restrictions
			if(!col.contains("MDP") || !col.contains("motdepasse")) {
			listCols.add(col);
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return listCols;
	}
	
}
