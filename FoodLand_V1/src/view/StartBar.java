package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.Color;

public class StartBar extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartBar frame = new StartBar();
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
	public StartBar() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 91);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 540, 90);
		contentPane.add(desktopPane);
		desktopPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnNewButton = new JButton("ADMIN");
		btnNewButton.setBackground(new Color(255, 99, 71));
		desktopPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("VENTE");
		btnNewButton_1.setBackground(new Color(143, 188, 143));
		desktopPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("ACHAT");
		btnNewButton_2.setBackground(new Color(95, 158, 160));
		desktopPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("PREPARATION");
		btnNewButton_3.setBackground(new Color(189, 183, 107));
		desktopPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("LIVRAISON");
		btnNewButton_4.setBackground(new Color(147, 112, 219));
		desktopPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Fermer");
		btnNewButton_5.setBackground(new Color(255, 0, 0));
		btnNewButton_5.setForeground(new Color(0, 0, 0));
		desktopPane.add(btnNewButton_5);
	}
}
