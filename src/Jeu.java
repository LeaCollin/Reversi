import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Jeu {
		
	public static void main(String args[]){
		Fenetre fenetre = new Fenetre();
		fenetre.isVisible();
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
