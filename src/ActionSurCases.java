

import java.awt.Color;
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
		plateau.selectionnerCases(c.getI(), c.getJ());;	
		Pion pion = new Pion(Couleur.Blanc);
		if (c.getComponentCount()!=0){
			boolean b = c.getComponent(0).equals(pion);
	        System.out.println(b);
		}
        System.out.println(c.isSelectionnee());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(c.isSelectionnee()){
            plateau.afficherPion(c);
        }
		c.setSelectionnee(false);
	}
	
	
	
	
}
