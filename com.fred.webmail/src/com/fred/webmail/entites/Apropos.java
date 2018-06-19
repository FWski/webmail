package com.fred.webmail.entites;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Apropos extends JDialog implements ActionListener{

	private JLabel lblDescription = new JLabel();
	private JLabel lblCopyright = new JLabel ();
	private JLabel lblVersion = new JLabel () ;
	private JLabel lblIcone = new JLabel (new ImageIcon ());
	private JButton cmdOK = new JButton ("OK");
	
//////////////////////////////////////////////////////////////////////// CONSTRUCTEUR	
	
	public Apropos (JFrame parent) {
		super(parent, true);
		
		Point loc = parent.getLocation();
		this.setLocation((int) loc.getX()+50,(int)loc.getY()+100);
		this.setSize(400, 200);
		this.setResizable(false);
		this.setTitle("A propos de WebMail");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initControles();

	}
	
////////////////////////////////////////////////////////////////////////// METHODES	
	
	public void initControles() {
			
		JPanel zoneClientApropos = (JPanel) this.getContentPane();
		JPanel panDroite = new JPanel(new GridLayout(3,1,10,10));
		JPanel panHaut = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		JPanel panBas = new JPanel();
	
		cmdOK.addActionListener(this);
		
		panHaut.add (lblIcone);
		panHaut.add (panDroite);
	
		panDroite.add (lblDescription);
		panDroite.add (lblCopyright);
		panDroite.add (lblVersion);
	
	
		panBas.add(cmdOK);
		
		zoneClientApropos.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		zoneClientApropos.add (panHaut, BorderLayout.NORTH);
		zoneClientApropos.add (panBas, BorderLayout.SOUTH);
		
		}
		
/////////////////////////////////////////////////////////////////////////// SETTERS		
		
	public void setDescription (String texte)
	{
	lblDescription.setText(texte);
	this.pack();
	}
	public void setVersion (String texte)
	{
	lblVersion.setText(texte);
	this.pack();
	}
	public void setCopyright (String texte)
	{
	lblCopyright.setText(texte);
	this.pack();
	}
	public void setIcone (String url)
	{
	lblIcone.setIcon(new ImageIcon(url));
	this.pack();	
	}

///////////////////////////////////////////////////////////// ACTION LISTENER
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		if (source == cmdOK) this.dispose();
	} // eo acionPerformed
	
		
} //eo class
