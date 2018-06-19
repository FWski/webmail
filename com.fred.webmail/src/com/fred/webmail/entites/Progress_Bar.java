package com.fred.webmail.entites;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class Progress_Bar {

	
	public JFrame fenetre;
	public JProgressBar probar;
	
	
	public Progress_Bar() {
		
		fenetre = new JFrame();

		fenetre.setSize(200, 150);
		fenetre.setLocationRelativeTo(null);
		probar = new JProgressBar(0 , 100);
		probar.setStringPainted(true);
		fenetre.setLayout(new FlowLayout());
		chargement();
		
		
	}
	
	public void chargement() {
		
		fenetre.add(probar);
		fenetre.pack();
		fenetre.setVisible(true);;
	
		new Thread(new Update()).start();
	}
	
	public class Update implements Runnable {

		@Override
		public void run() {
			
			for (int i = 0; i <= 100 ; i++) {
				
				probar.setValue(i);
				probar.repaint();
				

				try {
					
					Thread.sleep(8);
				}catch(Exception e) {
					
					e.printStackTrace();
					
					
				}
			if (i == 100) {
				fenetre.dispose();
				JOptionPane.showConfirmDialog(null, "Message envoyé avec succès.", "Message envoyé", JOptionPane.CLOSED_OPTION);
			}
				
		}	
			
		}
		
		
	}
	
	
}
