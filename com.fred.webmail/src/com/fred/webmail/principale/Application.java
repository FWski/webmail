package com.fred.webmail.principale;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.fred.webmail.entites.FenetrePrincipale;
import com.fred.webmail.entites.Fichier;



public class Application {

	public static void main(String[] args) {
		
	try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	} catch (InstantiationException e) {
	
		e.printStackTrace();
	} catch (IllegalAccessException e) {
	
		e.printStackTrace();
	} catch (UnsupportedLookAndFeelException e) {

		e.printStackTrace();
	}	
	
	Fichier ctrl = new Fichier();
	FenetrePrincipale frame = new FenetrePrincipale(ctrl);
	frame.setVisible(true);
	}

}
