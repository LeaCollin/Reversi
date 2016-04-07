//Classe ok ne plus toucher

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Case extends JPanel{

	private boolean selectionnee;
	private Couleur couleur;
	private Plateau plateau;
	private int i; // ligne
	private int j; //colonne

	public Case(Couleur couleur, int i, int j){
		this.i = i;
		this.j = j;
        setLayout(new GridLayout(1,0));
        this.couleur=couleur;
        //initCouleur();
    }

    public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public Couleur getCouleur() {
        return couleur;
    }

    public boolean isSelectionnee() {
        return selectionnee;
    }

    public void setSelectionnee(boolean selectionnee) {
    	this.selectionnee = selectionnee;
        if(selectionnee){
            setBackground(Color.BLUE);
            setForeground(Color.LIGHT_GRAY);
        }
        else {
            initCouleur();
        }
    }
    
    void initCouleur(){
    	setOpaque(true);
    	setBackground(Color.RED);
    	setForeground(Color.BLACK);
    	Border blackline = BorderFactory.createLineBorder(Color.black,1); 
    	setBorder(blackline);
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
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
