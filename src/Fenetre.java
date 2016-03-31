import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Fenetre extends JFrame{
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu Jeu = new JMenu("Jeu");
	private JMenuItem Recommencer = new JMenuItem("Recommencer");
	private JMenuItem Enregistrer = new JMenuItem("Enregistrer");
	private JMenu Quitter = new JMenu("Quitter");

	public Fenetre(){
		this.setTitle("Ma premi�re fen�tre Java");
	    //Taille de la fenetre
		this.setSize(600, 600);
	    //Position de la fenetre lors de son ouverture
	    this.setLocation(300, 400);
	    plateau();
	    menu();
	}
	
	public void plateau(){
		
	    //Couleur fond
	    this.setBackground(Color.LIGHT_GRAY);
	    //On pr�vient notre JFrame que notre JPanel sera son content pane
	    this.setContentPane(new Grille());               
	    this.setVisible(true);
    }
	 public void menu(){
		//On initialise nos menus      
		 this.Jeu.add(Recommencer);    
		 this.Jeu.add(Enregistrer);
		 //Ajout des menus de gauche � droite
		 this.menuBar.add(Jeu);
		 this.menuBar.add(Quitter);
		 this.setJMenuBar(menuBar);
		 this.setVisible(true);
	 }
}
