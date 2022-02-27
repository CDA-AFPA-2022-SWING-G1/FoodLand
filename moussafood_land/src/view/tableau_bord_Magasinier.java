package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;


public class tableau_bord_Magasinier {
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tableau_bord_Magasinier window = new tableau_bord_Magasinier();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public tableau_bord_Magasinier() {
		System.out.println("Je suis dans la view : new tableau_bord_Magasinier();");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/*
		 * Commandes à préparer
		 */
		JButton Commandes_a_preparer = new JButton("Commandes \u00E0 Pr\u00E9parer");
		//Commandes_a_preparer.setEnabled(false);
		Commandes_a_preparer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Liste_des_commandes_a_preparerView();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Commandes_a_preparer.setBounds(0, 28, 284, 23);
		frame.getContentPane().add(Commandes_a_preparer);
		
		/*
		 * Commandes à délivrer
		 */
		JButton Commandes_a_Delivrer = new JButton("Commandes \u00E0 D\u00E9livrer");
		Commandes_a_Delivrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Liste_des_commandes_a_delivrerView();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Commandes_a_Delivrer.setBounds(0, 82, 284, 23);
		frame.getContentPane().add(Commandes_a_Delivrer);
		
		/*
		 * Commandes à receptionner
		 */
		JButton Commandes_a_receptionner = new JButton("Commandes \u00E0 r\u00E9ceptionner");
		Commandes_a_receptionner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new commande_en_preparation_View();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Commandes_a_receptionner.setBounds(0, 143, 284, 23);
		frame.getContentPane().add(Commandes_a_receptionner);
		
		
		frame.setVisible(true);
	}
}
