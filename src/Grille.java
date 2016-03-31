import java.awt.Graphics;
import javax.swing.JPanel;

public class Grille extends JPanel {
	
	public void paintComponent(Graphics g){
	   for (int i = 0 ; i<9 ; i++){
		   g.drawLine(i*this.getWidth()/8, 0, i*this.getWidth()/8, this.getHeight());
		   g.drawLine(0, i*this.getHeight()/8, this.getWidth(), i*this.getHeight()/8);
	   }
	} 
	
	private void ajouterCase(){
        Case case1 = new Case();
        add(case1);
    }
	
}
