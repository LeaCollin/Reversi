import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;

import javax.swing.JPanel;

public class Pion extends JPanel {
		
	private Couleur couleur;
	private Case emplacement;
	private boolean monte;

	public Pion(Couleur couleur, Case emplacement, boolean monte) {
		this.couleur = couleur;
		this.emplacement = emplacement;
		this.monte = monte;
	}
	
	public Pion(Couleur couleur, boolean monte) {
        this.monte=monte;
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

	public Pion(Couleur couleur) {
		this.couleur = couleur;
	}
	
	public boolean isMonte() {
        return monte;
    }

    public void setMonte(boolean monte) {
        this.monte = monte;
    }

	public Case getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(Case emplacement) {
		this.emplacement = emplacement;
	}
	
	
	
	
}
