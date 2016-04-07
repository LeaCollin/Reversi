

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouvementsPion implements MouseListener {
		
	private Plateau plateau;
	private Pion pion;
	
	public MouvementsPion(Plateau plateau, Pion pion) {
		this.plateau = plateau;
		this.pion = pion;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		// Lorsque l'on clique et relache un élément

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub	
		// Lorsque l'on rentre dans quelque chose
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub	
		// Lorsque l'on sort de quelque chose
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Lorsque l'on clique sur un élement

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		//Lorsque l'on relache un élément
	}
	
	
	
	
	
}
