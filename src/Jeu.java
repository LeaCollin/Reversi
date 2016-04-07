import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Jeu extends JFrame {
	
	
	
	//Pour creer une fenetre "externe" :
	public Jeu(){
	Fenetre plat = new Fenetre();
	plat.setVisible(true);
	}
	
	
	public static void main(String args[]){
		Jeu test = new Jeu();
		test.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
