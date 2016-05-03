package Jeu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

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
		if (c.isSelectionnee()){
			plateau.TourJoueur(c);
		} else {
			JOptionPane jop = new JOptionPane();
			String erreur = "Erreur, cliquez sur une case rouge! ";
	        jop.showMessageDialog(null, erreur, "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Case c = plateau.initTourIA();

			Timer timer = new Timer();
	        timer.schedule (new TimerTask() {
	            public void run()
	            {
	                plateau.TourIA(c);
	                
	            }
	        }, 1000);     

	}
		
}
