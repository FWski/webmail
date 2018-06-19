package com.fred.webmail.entites;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;


public class FenetrePrincipale extends JFrame implements ActionListener, MouseListener, CaretListener, WindowListener  {
	
	private JMenuItem mnuMessageNouveau = new JMenuItem("Nouveau", 'N');
	private JMenuItem mnuMessageOuvrir = new JMenuItem("Ouvrir ...", 'O');
	private JMenuItem mnuMessageEnregistrer = new JMenuItem("Enregistrer sous ...", 'E');
	private JMenuItem mnuMessageEnvoyer = new JMenuItem("Envoyer", 'E');
	private JMenuItem mnuMessageQuitter = new JMenuItem("Quitter", 'Q');
	private JMenuItem mnuOptionsParametres = new JMenuItem("Paramètres ...", 'P');
	private JMenuItem mnuAideApropos = new JMenuItem("A propos ...", 'A');
	
	private JButton cmdToolNouveau = new JButton (new ImageIcon("new.gif"));
	private JButton cmdToolOuvrir = new JButton (new ImageIcon("open.gif"));
	private JButton cmdToolEnregistrer = new JButton (new ImageIcon("enregistrer.gif"));
	private JButton cmdToolEnvoyer = new JButton (new ImageIcon("send.gif"));
	
	private JTextField champsText = new JTextField();
	private JComboBox comboAdresses = new JComboBox();
	private JTextArea zoneText = new JTextArea();
	
	private JLabel statusBar = new JLabel(" ");
	
	private Fichier fichier;
	

	
///////////////////////////////////////////////////////////////////////////////////////	CONSTRUCTEUR
	
	public FenetrePrincipale(Fichier ctrl) {
		
		this.setSize(1000, 700);
		this.setTitle("WebMail");
		this.setLocationRelativeTo(null);
		
		fichier = ctrl;
		initMenu();
		initControles();
		ajouterDonnees();
	} 
	
//////////////////////////////////////////////////////////////////////////////////////// METHODES INITMENU, INITCONTROLES
	private void initMenu() {
		
		JMenuBar mbPrincipale = new JMenuBar();
		this.setJMenuBar(mbPrincipale);

		JMenu mnuMessage = new JMenu ("Message");
		JMenu mnuAide = new JMenu ("?");
		JMenu mnuOptions = new JMenu("Options");
		
	// Menu Message
		mnuMessageNouveau.setIcon(new ImageIcon("new.gif"));
		mnuMessageOuvrir.setIcon(new ImageIcon("open.gif"));
		mnuMessageEnvoyer.setIcon(new ImageIcon("send.gif"));
				
		mnuMessage.setMnemonic('M');
		mnuMessage.add(mnuMessageNouveau);
		mnuMessage.add(mnuMessageOuvrir);
		mnuMessage.add(mnuMessageEnregistrer);
		mnuMessage.addSeparator();
		mnuMessage.add(mnuMessageEnvoyer);
		mnuMessage.addSeparator();
		mnuMessage.add(mnuMessageQuitter);

	// Menu Option
		mnuOptions.setMnemonic('O');
		mnuOptions.add(mnuOptionsParametres);
				
	// Menu Aide
		mnuAide.setMnemonic('?');
		mnuAide.add(mnuAideApropos);
		
	// Sous menus
		mnuMessageEnvoyer.setAccelerator(KeyStroke.getKeyStroke("F2"));
		
		mnuMessageNouveau.addActionListener(this);
		mnuMessageOuvrir.addActionListener(this);
		mnuMessageEnregistrer.addActionListener(this);
		mnuMessageEnvoyer.addActionListener(this);
		mnuMessageQuitter.addActionListener(this);
	
		mnuAideApropos.addActionListener(this);
		
	// Ajouts à la MenuBar
		mbPrincipale.add(mnuMessage);
		mbPrincipale.add(mnuOptions);
		mbPrincipale.add(mnuAide);

	}

//------------------------------------------
	private void initControles() {
		
		JPanel zoneClient = (JPanel) this.getContentPane();
		JToolBar tbPrincipale = new JToolBar();
		JPanel panHautDroit = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,4));
		JScrollPane panText = new JScrollPane(zoneText);
		JPanel panBas = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
		
	// Caractéristiques des Boutons, comboBox, zones de texte
		cmdToolNouveau.setToolTipText("Nouveau");
		cmdToolOuvrir.setToolTipText("Ouvrir");
		cmdToolEnvoyer.setToolTipText("Envoyer");
		cmdToolEnvoyer.setEnabled(false);
		champsText.setToolTipText("Sujet du message");
		comboAdresses.setToolTipText("Destinataire");
		comboAdresses.setPreferredSize(new Dimension(300,20));
		
		champsText.setColumns(50);
		
		zoneText.setLineWrap(true);
		zoneText.setWrapStyleWord(true);
		
		
	// Ajout des Listeners
		cmdToolOuvrir.addActionListener(this);
		cmdToolNouveau.addActionListener(this);
		cmdToolEnvoyer.addActionListener(this);
		
		zoneText.addCaretListener(this);
		champsText.addCaretListener(this);
		
		mnuMessageNouveau.addMouseListener(this);
		mnuMessageOuvrir.addMouseListener(this);
		mnuMessageEnregistrer.addMouseListener(this);
		mnuMessageEnvoyer.addMouseListener(this);
		mnuMessageQuitter.addMouseListener(this);
		
		this.addWindowListener(this);
		
		
		
	// PanHautDroit dans la toolbar	
				
		panHautDroit.add(new JLabel("Sujet"));
		panHautDroit.add(champsText);
		panHautDroit.add(new JLabel("Pour"));
		panHautDroit.add(comboAdresses);
		
	// Toolbar	
		tbPrincipale.setFloatable(false);
		
		tbPrincipale.add(cmdToolNouveau);
		tbPrincipale.add(cmdToolOuvrir);
		tbPrincipale.addSeparator();
		tbPrincipale.add(cmdToolEnvoyer);
		
		tbPrincipale.add(panHautDroit);
	
		
	// Panneau texte (scrollPane)
		panText.setBorder(BorderFactory.createLoweredBevelBorder());
		
	// Panneau Bas
		panBas.setBorder(BorderFactory.createLoweredBevelBorder());
		
		panBas.add(statusBar);
		
	// zoneClient	
		zoneClient.setBorder(BorderFactory.createEmptyBorder(1, 5, 5, 5));
		zoneClient.add(tbPrincipale, BorderLayout.NORTH);
		zoneClient.add(panText, BorderLayout.CENTER);
		zoneClient.add(panBas, BorderLayout.SOUTH);

	}
	
/////////////////////////////////////////////////////////////////////////////////////	AUTRES METHODES
	
	private void ajouterDonnees() {				// méthode pour ajouter un vector dans la combobox
		
		Vector vec = new Vector();
		vec = Fichier.getLine();
		for(int i = 0; i < vec.size(); i++) {
			comboAdresses.addItem(vec.get(i));
		}
	}
//------------------------------------------
	private void mnuMessageNouveau_click() {
		
		String z = zoneText.getText();
		String s = champsText.getText();
		
		if (!z.isEmpty() || !s.isEmpty()) {
		
			int option = JOptionPane.showConfirmDialog(null, "Vous n'avez pas envoyé votre message actuel. Voulez-vous vraiment en créer un nouveau?", "Confirmation", JOptionPane.YES_NO_OPTION);
			
			if (option == JOptionPane.OK_OPTION) {
				zoneText.setText("");
				champsText.setText("");
			}
		}
	}
	
//------------------------------------------	
	private void mnuMessageOuvrir_click() {
		
		
		JFileChooser dlg = new JFileChooser("..\\com.fred.webmail\\msg");
		
		if (dlg.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){

		fichier.getContenu(zoneText, champsText, comboAdresses, dlg.getSelectedFile());
		String nomfichier = dlg.getSelectedFile().getName();
		statusBar.setText(nomfichier);
		}
	}
//-------------------------------------------	
	private void mnuMessageEnregistrer_click() {
		
		String destin = (String) comboAdresses.getSelectedItem();
		String expéd = "Fred";
		String suj = champsText.getText();
		String conte = zoneText.getText();
		
		Message newMessage = new Message(destin, expéd, suj, conte);
		
//		JFileChooser dlg = new JFileChooser("..\\nmProjet\\msg");
		JFileChooser dlg = new JFileChooser("..\\com.fred.webmail\\msg\\Brouillons");
		dlg.setSelectedFile(new File(suj));

		if (dlg.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
			
			File f1 = new File (dlg.getSelectedFile() + ".msg");
			
			if (f1.exists()) {
				
				int compteurE = 1;
				
				while (f1.exists()) {

				f1 = new File(dlg.getSelectedFile() + "(" + compteurE++ + ")" + ".msg");

				}

			} // eo if/else
			
			fichier.setContenuEnregistrer(newMessage, f1);	
		}
	}
//------------------------------------------	
	private void mnuMessageEnvoyer_click() {
		
		String destin = (String) comboAdresses.getSelectedItem();
		int placeDebut = destin.indexOf(' ');
		int placeFin = destin.indexOf(';');
		
		String nomDestin = destin.substring(placeDebut+1, placeFin);
		String expéd = "Fred";
		String suj = champsText.getText();
		String conte = zoneText.getText();
		
		Message newMessage = new Message(destin, expéd, suj, conte);
		
		File dlg = new File("..\\com.fred.webmail\\msg\\Messages envoyés\\M - " + suj + " - " + nomDestin + ".msg");
		
		int option = JOptionPane.showConfirmDialog(null, "Vous êtes sur le point d'envoyer ce message. Voulez-vous continuer?", "Confirmation d'envoi", JOptionPane.YES_NO_OPTION);
			
		if (option == JOptionPane.OK_OPTION) {
			
			if (dlg.exists()) {
				
				
				int compteur = 1;
				
				while (dlg.exists()) {
							
					dlg = new File("..\\com.fred.webmail\\msg\\Messages envoyés\\M - " + suj + " - " + nomDestin + "(" + compteur++ + ")" + ".msg");

					
				} // eo while
			} // eo if 2
				
			
			if (fichier.setContenuEnvoyer(newMessage, dlg) == true) {
				
				Progress_Bar test = new Progress_Bar();
				 
				test.chargement();
				
				zoneText.setText("");
				champsText.setText("");
				
				
			} // eo if 3	
		} // eo if 1
	} // eo envoyer_click
	
//------------------------------------------	
	private void mnuMessageQuitter_click() {
		
		String z = zoneText.getText();
		String s = champsText.getText();
		
		if (!z.isEmpty() || !s.isEmpty()) {
			
			int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?", "Confirmation", JOptionPane.YES_NO_OPTION);
			
			if (option == JOptionPane.OK_OPTION) {
				
				System.exit(0);
				
			} else {
				
				this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			}
						
		} else {
			
			System.exit(0);
		
		}
	}
//------------------------------------------	
	private void mnuAideApropos_click() {
		
		Apropos fenApropos = new Apropos(this);
		fenApropos.setDescription("Envoi de mails à un destinataire");
		fenApropos.setCopyright("Fred (c) 2018");
		fenApropos.setVersion("Version 1.0");
		fenApropos.setIcone("mail.gif");
		fenApropos.setVisible(true);
	
	
	}
//------------------------------------------	
	 public boolean isTextetSujetVide() {
		 
		 String z = zoneText.getText();
		 String s = champsText.getText();
		 
		 if (z.isEmpty() || s.isEmpty()) { 
			 
	        return true; 
	     }

	     return false;
	 }

/////////////////////////////////////////////////////////////////////////////////////////// LISTENERS	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		if (source == mnuMessageNouveau) mnuMessageNouveau_click();
		if (source == mnuMessageOuvrir ) mnuMessageOuvrir_click();
		if (source == mnuMessageEnregistrer) mnuMessageEnregistrer_click();
		if (source == mnuMessageEnvoyer) mnuMessageEnvoyer_click();
		if (source == mnuMessageQuitter) mnuMessageQuitter_click();
		if (source == mnuAideApropos) mnuAideApropos_click();
		
		if (source == cmdToolOuvrir) mnuMessageOuvrir_click();
		if (source == cmdToolNouveau) mnuMessageNouveau_click();
		if (source == cmdToolEnvoyer) mnuMessageEnvoyer_click();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
		Object source = e.getSource();
		if (source == mnuMessageNouveau) statusBar.setText("Nouveau fichier");
		if (source == mnuMessageOuvrir) statusBar.setText("Ouvrir un fichier");
		if (source == mnuMessageEnregistrer) statusBar.setText("Enregisrer un fichier");
		if (source == mnuMessageEnvoyer) statusBar.setText("Envoyer un message");
		if (source == mnuMessageQuitter) statusBar.setText("Quitter l'application");
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		
		statusBar.setText(" ");

	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	
	public void caretUpdate(CaretEvent e) {
		
		cmdToolEnvoyer.setEnabled(!isTextetSujetVide());
		mnuMessageEnvoyer.setEnabled(!isTextetSujetVide());
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
		mnuMessageQuitter_click();
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
			
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
				
	}

	@Override
	public void windowIconified(WindowEvent e) {
				
	}

	@Override
	public void windowOpened(WindowEvent e) {
				
	}
	
	
} //eo class
