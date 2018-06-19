package com.fred.webmail.entites;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Fichier {
	
	
//	private static String nom = "C:\\Users\\28010-34-14\\workspace\\com.fred.webmail\\adressesMail.csv";
	private static String nom = "..\\com.fred.webmail\\adressesMail.csv";
	
///////////////////////////////////////////////////////////////////////////////////	
	
	public static Vector getLine() {
		
		Vector<String> f = new Vector();
		
		try {
			BufferedReader bf = new BufferedReader(new FileReader(nom));
			for (;;) {
				
				String nomVariableString = bf.readLine();
				if (nomVariableString == null) break;
				if (nomVariableString.length() > 0 ) f.add(nomVariableString);
			
				
			}
				
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
			
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	
		return f;
		
	}

///////////////////////////////////////////////////////////////////////////////////	
	
	public boolean setContenuEnvoyer (Message message, File f){

				
		try {
			
			PrintWriter WModele = new PrintWriter (new FileWriter (f));
			
			WModele.print(message);
			WModele.close();
				
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "sauvegarde impossible après envoi.", "Erreur", JOptionPane.YES_OPTION);
			return false;
		}
		
		return true;
		
	}
	
///////////////////////////////////////////////////////////////////////////////////	
	
	public void setContenuEnregistrer (Message message, File f) {

//		int place = message.getDestinataire().indexOf(';');
//		String nomDestinataire = message.getDestinataire().substring(0, place);
		
		try {
			
			PrintWriter WModele = new PrintWriter (new FileWriter (f));
			WModele.print(message);
			WModele.close();
			
					
		} catch (IOException e) { 
		
		}
	}
	
///////////////////////////////////////////////////////////////////////////////////	
	
	public void getContenu (JTextArea txt, JTextField suj, JComboBox combo, File f) {
		
		try {
		
			BufferedReader RModele = new BufferedReader (new FileReader (f));
			txt.setText("");							// Pour effacer la zone de texte avant d'ouvrir la nouvelle
			suj.setText("");							// Pour effacer le sujet avant d'ouvrir le nouveau
			
			
			combo.setSelectedItem(RModele.readLine()); 	// destinataire
			RModele.readLine();							// expéditeur, ne doit pas être affiché
			suj.setText(RModele.readLine());			// sujet
		
			
			while (RModele.ready()) { 					// tant qu'il y a des lignes à lire, ....
				
				
			txt.append(RModele.readLine() + "\n"); 	// je lis				
				
			}
			RModele.close();
		} catch (IOException e) {}
	}
	
	
	
} // eo class
