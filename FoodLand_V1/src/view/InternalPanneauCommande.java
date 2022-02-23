package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Dimension;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class InternalPanneauCommande extends JInternalFrame {

	/**
	 * Create the frame.
	 */
	public InternalPanneauCommande() {
		
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				
			}
		});
		getContentPane().setSize(new Dimension(100, 300));
		getContentPane().setPreferredSize(new Dimension(100, 300));
		setTitle("InternalTest");
		setClosable(true);
		setIconifiable(true);
		setResizable(true);
		setBounds(100, 100, 450, 300);
		setVisible(true);
	}

}
