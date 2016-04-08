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
		//Actions lorsqu'on relache la souris (à améliorer)
		if (c.isSelectionnee()){
			if (plateau.isTourNoir()){
				plateau.ajouterPion(c, Couleur.Noir); 				//marche mais pb de réactualiation de la fenetre, si on réduit puis réagrandit la fenetre, le pion apparait mystère...
				plateau.actualiserPlateau();					//déselection toutes les cases pour pouvoir passser au tour suivant
				plateau.jouer(plateau.isTourNoir());
			}
			else {
				plateau.ajouterPion(c, Couleur.Blanc); 				//marche mais pb de réactualiation de la fenetre, si on réduit puis réagrandit la fenetre, le pion apparait mystère...
				plateau.actualiserPlateau();					//déselection toutes les cases pour pouvoir passser au tour suivant
				plateau.jouer(plateau.isTourNoir());
			}
					}
				

	}
	
	
	
	
}
