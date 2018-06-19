package com.fred.webmail.entites;

public class Message {

	
	String destinataire = new String();
	String expéditeur = new String();
	String sujet = new String();
	String contenu = new String();
	


	public Message(String destinataire, String expéditeur, String sujet, String contenu) {
		super();
		this.destinataire = destinataire;
		this.expéditeur = expéditeur;
		this.sujet = sujet;
		this.contenu = contenu;
	}


	public String getDestinataire() {
		return destinataire;
	}

	public String getExpéditeur() {
		return expéditeur;
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
				return  destinataire + newLine +  expéditeur + newLine + sujet + newLine + contenu ; 
		
	}


}
