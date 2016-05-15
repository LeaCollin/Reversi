package Jeu;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Fenetre extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 198007107186481305L;
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu Jeu = new JMenu("Jeu"), Apropos = new JMenu("A propos");
	
	private JMenuItem Recommencer = new JMenuItem("Recommencer");
	private JMenuItem Quitter = new JMenuItem("Quitter");
	private JMenuItem Regles = new JMenuItem("Règles");
	private JMenuItem point = new JMenuItem("?");

	public Fenetre(){
		this.setTitle("Reversi");
	    //Taille de la fenetre
		this.setSize(800, 800);
	    //Position de la fenetre lors de son ouverture
	    this.setLocationRelativeTo(null);
	    //empecher la redimension
	    setResizable(false);
	    Plateau plat = new Plateau(8);
	    add(plat);
	    menu();
	}
	
	 public void menu(){
		 
		//On initialise nos menus 
		//On a besoin d'un listener
		 Regles.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent arg0){
				JOptionPane jop= new JOptionPane();
				String regle ="Chacun à son tour, les joueurs vont poser un pion de leur couleur sur une case vide, adjacente a un pion adverse. \nChaque pion pose doit obligatoirement encadrer un ou plusieurs pions adverses avec un autre pion de sa couleur, deja place.\n\nIl retourne alors le ou les pions adverse(s) qu'il vient d'encadrer. Les pions ne sont ni retires du plateau, ni deplaces d'une case à l'autre.\n\nOn peut encadrer des pions adverses dans les huit directions et plusieurs pions peuvent etre encadres dans chaque direction.\n\nLe jeu s'arrête quand les deux joueurs ne peuvent plus poser de pion. On compte alors le nombre de pions. Le joueur ayant le plus grand nombre de pions de sa couleur sur le plateau a gagne."; 
				jop.showMessageDialog(null, regle, "Regles du Reversi", JOptionPane.INFORMATION_MESSAGE);

			 }
		 });
		//On a besoin d'un listener
		 Recommencer.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent arg0){
				 JOptionPane jop= new JOptionPane();
				 int option = jop.showConfirmDialog(null, 
						 "Voulez-vous recommencer une nouvelle partie ?", 
						 "Vous nous quittez... ?", 
						 JOptionPane.YES_NO_OPTION, 
						 JOptionPane.QUESTION_MESSAGE);

				 if(option == JOptionPane.OK_OPTION){
					 dispose();
					 new Fenetre();
					
				 }
			 }
		 });
		 this.Jeu.add(Regles); 
		 this.Jeu.add(Recommencer); 
		 this.Jeu.addSeparator();
		//Pour quitter l'application
		 Quitter.addActionListener(new ActionListener(){
		     public void actionPerformed(ActionEvent event){
		        System.exit(0);
		     }
		 });
		 this.Jeu.add(Quitter);
		
		//Menu a propos

		//Ajout de ce que doit faire le "?"
		 point.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent arg0) {
		        JOptionPane jop = new JOptionPane();
		        String apropos = "Ce jeu a ete cree par \n Melanie Petitcuenot et Lea Collin \nEleves de 3eme annee Informatique a Polytech Lyon";     
		        jop.showMessageDialog(null, apropos, "A propos", JOptionPane.INFORMATION_MESSAGE);        
		      }            
		    });
		 this.Apropos.add(point);
		 
		 //Ajout des menus:
		 menuBar.add(Jeu);
		 menuBar.add(Apropos);
		 
		 //Barre de menus
		 this.setJMenuBar(menuBar);
		 this.setVisible(true);
	 }
	 
	 public void paintComponent(Graphics g){
		    g.drawString("Tiens ! Le Site du Zero !", 10, 20);
		  }    
}