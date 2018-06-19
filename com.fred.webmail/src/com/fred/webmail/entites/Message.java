package com.fred.webmail.entites;

public class Message {

	
	String destinataire = new String();
	String exp�diteur = new String();
	String sujet = new String();
	String contenu = new String();
	


	public Message(String destinataire, String exp�diteur, String sujet, String contenu) {
		super();
		this.destinataire = destinataire;
		this.exp�diteur = exp�diteur;
		this.sujet = sujet;
		this.contenu = contenu;
	}


	public String getDestinataire() {
		return destinataire;
	}

	public String getExp�diteur() {
		return exp�diteur;
	}

	public String getSujet() {
		return sujet;
	}

	public String getContenu() {
		return contenu;
	}


	@Override
	public String toString() {     
		String newLine = System.getProperty("line.separator"); 
				return  destinataire + newLine +  exp�diteur + newLine + sujet + newLine + contenu ; 
		
	}


}
