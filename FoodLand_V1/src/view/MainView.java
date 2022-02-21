package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.MenuBar;

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
import serviceMetiers.ControlUtilisateur;

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
	private JMenuItem itemSaveVentesJours;
	private JMenuItem unMenuItem;
	private JMenuItem unAutreMenu;
	private JMenuItem menuItemPanneauVentes;
	private JMenuItem menuItemHistoriqueVentes;
	private JMenuItem MenuItemGestionVentes;
	private JMenuItem menuItemGestionStock;
	private JMenuItem menuItemGestionDuPersonnel;

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
		
		itemSaveVentesJours = new JMenuItem("Sauvegarder les ventes du jour");
		menuFichiers.add(itemSaveVentesJours);
		
		menuEdition = new JMenu("Edition");
		menuBar.add(menuEdition);
		
		unMenuItem = new JMenuItem("un menu");
		menuEdition.add(unMenuItem);
		
		unAutreMenu = new JMenuItem("un autre menu");
		menuEdition.add(unAutreMenu);
		
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
		
		
		
		menuItemPanneauVentes = new JMenuItem("Panneau des ventes");
		menuItemPanneauVentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalPanneauVentes.setVisible(true);
				it = new InternalTest();
				it.setVisible(true);
				
			}
		});
		menuVentes.add(menuItemPanneauVentes);
		
		menuItemHistoriqueVentes = new JMenuItem("Consulter les historiques des ventes");
		menuItemHistoriqueVentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				it = new InternalTest();
				it.setVisible(true);
				contentPane.add(it);
				
			}
		});
		menuVentes.add(menuItemHistoriqueVentes);
		
		menuGestion = new JMenu("Gestion");
		menuBar.add(menuGestion);
		
		MenuItemGestionVentes = new JMenuItem("Gestion des Ventes");
		menuGestion.add(MenuItemGestionVentes);
		
		menuItemGestionStock = new JMenuItem("Gestion du stock");
		menuGestion.add(menuItemGestionStock);
		
		menuItemGestionDuPersonnel = new JMenuItem("Gestion du personnel");
		menuItemGestionDuPersonnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// model
				Utilisateur ut = new Utilisateur();
				ManageUtilisateurView muv = new ManageUtilisateurView();
				//ControlUtilisateur cut = new ControlUtilisateur(ut, muv);
				
			}
		});
		menuGestion.add(menuItemGestionDuPersonnel);
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
		menuEdition.setVisible(true);
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

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	public void setDateChooser(JDateChooser dateChooser) {
		this.dateChooser = dateChooser;
	}

	public InternalTest getIt() {
		return it;
	}

	public void setIt(InternalTest it) {
		this.it = it;
	}

	public static Utilisateur getUtil() {
		return util;
	}

	public static void setUtil(Utilisateur util) {
		MainView.util = util;
	}

	public JMenu getMenuFichiers() {
		return menuFichiers;
	}

	public void setMenuFichiers(JMenu menuFichiers) {
		this.menuFichiers = menuFichiers;
	}

	public JMenu getMenuEdition() {
		return menuEdition;
	}

	public void setMenuEdition(JMenu menuEdition) {
		this.menuEdition = menuEdition;
	}

	public JMenu getMenuVentes() {
		return menuVentes;
	}

	public void setMenuVentes(JMenu menuVentes) {
		this.menuVentes = menuVentes;
	}

	public JMenu getMenuGestion() {
		return menuGestion;
	}

	public void setMenuGestion(JMenu menuGestion) {
		this.menuGestion = menuGestion;
	}

	public JInternalFrame getInternalPanneauVentes() {
		return internalPanneauVentes;
	}

	public void setInternalPanneauVentes(JInternalFrame internalPanneauVentes) {
		this.internalPanneauVentes = internalPanneauVentes;
	}

	public JInternalFrame getInternaPanneauPersonnel() {
		return internaPanneauPersonnel;
	}

	public void setInternaPanneauPersonnel(JInternalFrame internaPanneauPersonnel) {
		this.internaPanneauPersonnel = internaPanneauPersonnel;
	}

	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public JMenuItem getItemSaveVentesJours() {
		return itemSaveVentesJours;
	}

	public void setItemSaveVentesJours(JMenuItem itemSaveVentesJours) {
		this.itemSaveVentesJours = itemSaveVentesJours;
	}

	public JMenuItem getUnMenuItem() {
		return unMenuItem;
	}

	public void setUnMenuItem(JMenuItem unMenuItem) {
		this.unMenuItem = unMenuItem;
	}

	public JMenuItem getUnAutreMenu() {
		return unAutreMenu;
	}

	public void setUnAutreMenu(JMenuItem unAutreMenu) {
		this.unAutreMenu = unAutreMenu;
	}

	public JMenuItem getMenuItemPanneauVentes() {
		return menuItemPanneauVentes;
	}

	public void setMenuItemPanneauVentes(JMenuItem menuItemPanneauVentes) {
		this.menuItemPanneauVentes = menuItemPanneauVentes;
	}

	public JMenuItem getMenuItemHistoriqueVentes() {
		return menuItemHistoriqueVentes;
	}

	public void setMenuItemHistoriqueVentes(JMenuItem menuItemHistoriqueVentes) {
		this.menuItemHistoriqueVentes = menuItemHistoriqueVentes;
	}

	public JMenuItem getMenuItemGestionVentes() {
		return MenuItemGestionVentes;
	}

	public void setMenuItemGestionVentes(JMenuItem menuItemGestionVentes) {
		MenuItemGestionVentes = menuItemGestionVentes;
	}

	public JMenuItem getMenuItemGestionStock() {
		return menuItemGestionStock;
	}

	public void setMenuItemGestionStock(JMenuItem menuItemGestionStock) {
		this.menuItemGestionStock = menuItemGestionStock;
	}

	public JMenuItem getMenuItemGestionDuPersonnel() {
		return menuItemGestionDuPersonnel;
	}

	public void setMenuItemGestionDuPersonnel(JMenuItem menuItemGestionDuPersonnel) {
		this.menuItemGestionDuPersonnel = menuItemGestionDuPersonnel;
	}
	
	
	
}
