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
	private JMenuItem point = new JMenuItem("?");

	public Fenetre(){
		this.setTitle("Reversi");
	    //Taille de la fenetre
		this.setSize(800, 800);
	    //Position de la fenetre lors de son ouverture
	    this.setLocationRelativeTo(null);
	    //empecher la redimension
	    setResizable(false);
	    add(new Plateau(8));
	    menu();
	}
	
	 public void menu(){
		 
		//On initialise nos menus 
		//Attention on a besoin d'un listener
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
					 //Essayer d'effacer la console??
					 new Fenetre();
					
				 }
			 }
		 });
		 this.Jeu.add(Recommencer); 
		 this.Jeu.addSeparator();
		//Pour quitter l'application
		 Quitter.addActionListener(new ActionListener(){
		     public void actionPerformed(ActionEvent event){
		        System.exit(0);
		     }
		 });
		 this.Jeu.add(Quitter);
		 
		//Menu À propos

		//Ajout de ce que doit faire le "?"
		 point.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent arg0) {
		        JOptionPane jop = new JOptionPane();
		        String apropos = "Ce jeu a ete cree par \n Melanie Petitcuenot (la plus belle) et Lea Collin \nEleves de 3eme annee Informatique a Polytech Lyon";     
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