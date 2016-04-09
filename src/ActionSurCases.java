import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ActionSurCases implements MouseListener{

	private Case c;
	private Plateau plateau;
	
	public ActionSurCases(Case c, Plateau plateau) {
		super();
		this.c = c;
		this.plateau = plateau;
	}

	@Override

	public void mouseClicked(MouseEvent e) {		
   

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {	
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//Actions lorsqu'on relache la souris (� am�liorer)
		if (c.isSelectionnee()){
			plateau.unePartie(c);
		}

	}
		
}
