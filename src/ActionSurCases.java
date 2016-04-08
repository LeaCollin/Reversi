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
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//Actions lorsqu'on relache la souris (� am�liorer)
		if (c.isSelectionnee()){
			if (plateau.isTourNoir()){
				plateau.ajouterPion(c, Couleur.Noir); 				//marche mais pb de r�actualiation de la fenetre, si on r�duit puis r�agrandit la fenetre, le pion apparait myst�re...
				plateau.actualiserPlateau();					//d�selection toutes les cases pour pouvoir passser au tour suivant
				plateau.jouer(plateau.isTourNoir());
			}
			else {
				plateau.ajouterPion(c, Couleur.Blanc); 				//marche mais pb de r�actualiation de la fenetre, si on r�duit puis r�agrandit la fenetre, le pion apparait myst�re...
				plateau.actualiserPlateau();					//d�selection toutes les cases pour pouvoir passser au tour suivant
				plateau.jouer(plateau.isTourNoir());
			}
					}
				

	}
	
	
	
	
}
