package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import model.Role;
import model.Utilisateur;
import outils.DateListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;

public class MainView extends JFrame {

	private JPanel contentPane;
	private JDateChooser dateChooser;
	
	private InternalTest it;
	
	private static Utilisateur util;
	private JMenu menuFichiers;
	private JMenu menuEdition;
	private JMenu menuVentes;
	private JMenu menuGestion;
	private JInternalFrame internalPanneauVentes;
	private JInternalFrame internaPanneauPersonnel;
	private JMenuBar menuBar;
	private JMenuItem itemVentesJours;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem_3;
	private JMenuItem mntmNewMenuItem_4;
	private JMenuItem MenuItemGestionVentes;
	private JMenuItem menuItemGestionStock;
	private JMenuItem mntmNewMenuItem_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView(util);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView(Utilisateur ut) {
		
		setPreferredSize(new Dimension(800, 600));
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuFichiers = new JMenu("Fichier");
		menuBar.add(menuFichiers);
		
		itemVentesJours = new JMenuItem("Sauvegarder les ventes du jour");
		menuFichiers.add(itemVentesJours);
		
		menuEdition = new JMenu("Edition");
		menuBar.add(menuEdition);
		
		mntmNewMenuItem = new JMenuItem("un menu");
		menuEdition.add(mntmNewMenuItem);
		
		mntmNewMenuItem_1 = new JMenuItem("un autre menu");
		menuEdition.add(mntmNewMenuItem_1);
		
		menuVentes = new JMenu(" Ventes");
		menuBar.add(menuVentes);
		
		
		internalPanneauVentes = new JInternalFrame("Panneau des Ventes");
		internalPanneauVentes.setSize(new Dimension(800, 600));
		internalPanneauVentes.setBounds(5, 11, 424, 125);
		internalPanneauVentes.addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				internalPanneauVentes.dispose();
			}
			@Override
			public void internalFrameIconified(InternalFrameEvent e) {
				
			}
		});
		
		//internalFrame.setIcon(true);
		internalPanneauVentes.setIconifiable(true);
		internalPanneauVentes.setResizable(true);
		internalPanneauVentes.setClosable(true);
		internalPanneauVentes.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		dateChooser = new JDateChooser();
		DateListener dl = new DateListener(dateChooser);
		System.out.println(dl.getJdc().getDate());
		
		int jour = dateChooser.getJCalendar().getDayChooser().getDay();
		int mois = dateChooser.getJCalendar().getMonthChooser().getMonth();
		int annee = dateChooser.getJCalendar().getYearChooser().getYear();
		
		//internalFrame.getContentPane().add(dateChooser);
		String date = dateChooser.getDateFormatString();
		System.out.println(date);
		
		
		
		mntmNewMenuItem_3 = new JMenuItem("Panneau des ventes");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalPanneauVentes.setVisible(true);
				it = new InternalTest();
				it.setVisible(true);
				
			}
		});
		menuVentes.add(mntmNewMenuItem_3);
		
		mntmNewMenuItem_4 = new JMenuItem("Consulter les historiques des ventes");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				it = new InternalTest();
				it.setVisible(true);
				contentPane.add(it);
				
			}
		});
		menuVentes.add(mntmNewMenuItem_4);
		
		menuGestion = new JMenu("Gestion");
		menuBar.add(menuGestion);
		
		MenuItemGestionVentes = new JMenuItem("Gestion des Ventes");
		menuGestion.add(MenuItemGestionVentes);
		
		menuItemGestionStock = new JMenuItem("Gestion du stock");
		menuGestion.add(menuItemGestionStock);
		
		mntmNewMenuItem_2 = new JMenuItem("Gestion du personnel");
		menuGestion.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(800, 600));
		contentPane.setSize(new Dimension(800, 600));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		internaPanneauPersonnel = new JInternalFrame("Gestion du Personnel");
		internaPanneauPersonnel.setClosable(true);
		internaPanneauPersonnel.setIconifiable(true);
		internaPanneauPersonnel.setBounds(5, 136, 424, 104);
		contentPane.setLayout(null);
		contentPane.add(internalPanneauVentes);
		internalPanneauVentes.getContentPane().setLayout(null);
		menuEdition.setVisible(false);
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 408, 95);
		internalPanneauVentes.getContentPane().add(desktopPane);
		contentPane.add(internaPanneauPersonnel);
		internaPanneauPersonnel.setVisible(true);
		setVisible(true); 
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	
	private void autorisation() {
		List<Role> roles = util.getListe_Roles();
		
		for(Role r : roles) {
			String role = r.getLibelle_role();
			switch (role) {
			
			case "ADMIN": 
				
				break;
				
			case "ACHETEUR":
				
				break;
				
			case "VENDEUR":
				
				break;
				
			case "GERANT":
				
				break;
				
				default:
		}
		
	}
		
	}
}
