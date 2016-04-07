import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;

import javax.swing.JPanel;

public class Pion extends JPanel {
		
	private Couleur couleur;
	private Case emplacement;

	public Pion(Couleur couleur, Case emplacement) {
		this.couleur = couleur;
		this.emplacement = emplacement;
	}
	
	public Pion(Couleur couleur) {
        this.couleur = couleur;
        setOpaque(false);
        switch (couleur) {
        case Blanc :
            setForeground(Color.LIGHT_GRAY);
            setBackground(Color.WHITE);
            break;
        case Noir :
            setForeground(Color.BLUE);
            setBackground(Color.CYAN);
            break;
        }
    }
	
	@Override
    public void paintComponent(Graphics g){
        Paint paint;
        Graphics2D g2d;
        if (g instanceof Graphics2D) {
            g2d = (Graphics2D) g;
        }
        else {
            System.out.println("Error");
            return;
        }
        paint = new GradientPaint(0,0, getBackground(), getWidth(), getHeight(), getForeground());
        g2d.setPaint(paint);
        g.fillOval(5, 5, getWidth()-10, getHeight()-10);

    }



	public Case getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(Case emplacement) {
		this.emplacement = emplacement;
	}

	public Couleur getCouleur() {
		// TODO Auto-generated method stub
		return couleur;
	}
	
	
	
	
}
