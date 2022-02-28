package view;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JInternalFrame;
import java.awt.Dimension;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import dao.DaoColumns;
import dao.DaoCommande;
import model.BonCommande;
import outils.MyJTable;
import outils.MyJTable_EnTest;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class InternalPanneauCommande extends JInternalFrame {
	
	private JTable tableCommandes;

	private BonCommande commande;
	
	/**
	 * Create the frame.
	 */
	public InternalPanneauCommande() {
		setMaximizable(true);
		
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				
			}
		});
		getContentPane().setSize(new Dimension(100, 300));
		getContentPane().setPreferredSize(new Dimension(100, 300));
		getContentPane().setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 900, 800);
		getContentPane().add(desktopPane);
		
		tableCommandes = new JTable();
		tableCommandes.setBounds(0, 0, 874, 800);
		tableCommandes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		MyJTable_EnTest containTable = new MyJTable_EnTest<BonCommande>(tableCommandes, new DefaultTableModel(), false);
		
		ArrayList<String> cols = new ArrayList<>();
		DaoColumns daoColumns = new DaoColumns();
		cols = daoColumns.columnsTable("foodland", "commande");
		System.out.println("colonnes commande: " + cols);

		BonCommande c1 = new BonCommande();
		c1.setId_bonCommande(2);
		c1.setFk_id_entreprise(345);
		c1.setNom_acheteur_commande("toto");
		
		BonCommande c2 = new BonCommande();
		c2.setId_bonCommande(7);
		c2.setFk_id_entreprise(567);
		c2.setNom_acheteur_commande("titi");
		Vector<BonCommande> lines = new Vector<>();
		
		lines.add(c1);
		lines.add(c2);
		tableCommandes = containTable.generateTable(cols, lines);
		tableCommandes.setBounds(10, 48, 414, 211);
		//desktopPane.add(tableCommandes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 800, 800);
		scrollPane.setViewportView(tableCommandes);
		desktopPane.add(scrollPane);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(685, 11, 89, 23);
		getContentPane().add(btnNewButton);
		setTitle("Panneau des Commandes");
		setClosable(true);
		setIconifiable(true);
		setResizable(true);
		setBounds(100, 100, 450, 300);
		setSize(this.getMaximumSize());
		
		setVisible(true);
	}
}
