import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Fenetre extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 198007107186481305L;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu Jeu = new JMenu("Jeu");
	private JMenuItem Recommencer = new JMenuItem("Recommencer");
	private JMenuItem Quitter = new JMenuItem("Quitter");

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
		 
		 Recommencer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
			 
		 });
		//On initialise nos menus      
		 this.Jeu.add(Recommencer);   
		 //Ajout des menus de gauche � droite
		 this.menuBar.add(Jeu);
		 this.menuBar.add(Quitter);
		 this.setJMenuBar(menuBar);
		 this.setVisible(true);
	 }
	 
	 public void paintComponent(Graphics g){
		    g.drawString("Tiens ! Le Site du Z�ro !", 10, 20);
		  }    
	 
}