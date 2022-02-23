package outils;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class ConnectionDB {

	private static final String JDBC_DRIVER_MYSL = "com.mysql.jdbc.Driver";
	
	private static final String DB_URL = "jdbc:mysql://localhost/foodLand2";
	private static final String USER = "root";
	private static final String PASS = "";
	
	private Connection conn = null;
	
	public ConnectionDB() {
		
		try {
			Class.forName(JDBC_DRIVER_MYSL);
			System.out.println("Connection à la base..");
			conn =  DriverManager.getConnection(DB_URL, USER, PASS);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Problème de connection à la base..");
		}
	}
	
	public ConnectionDB(String dbUrl) {
		
		try {
			Class.forName(JDBC_DRIVER_MYSL);
			System.out.println("Connection à la base..");
			conn =  DriverManager.getConnection(dbUrl, USER, PASS);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Problème de connection à la base..");
		}
	}
	
	public Connection getConnectionDB() {
		return conn;
	}
}
