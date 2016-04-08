//Classe ok ne plus toucher

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;

import javax.swing.JPanel;

public class Case extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3347630978748093310L;
	private boolean selectionnee;
	private int i;							// ligne
	private int j; 							// colonne
	private boolean etat; 					// permet de savoir si la case contient un pion ou non => true : il y a un pion dans la case

	public Case(boolean etat, int i, int j){
		this.etat = etat;
		this.i = i;
		this.j = j;
        setLayout(new GridLayout(1,0));
    }

    public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
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

    public boolean isSelectionnee() {
        return selectionnee;
    }

    public void setSelectionnee(boolean selectionnee) {
    	this.selectionnee = selectionnee;
        if(selectionnee){
            setBackground(Color.RED);
        }
        else {
            initCouleur();
        }
    }
    
    public void initCouleur(){
    	setBackground(Color.WHITE);
    }
    
    @Override
    public void paintComponent(Graphics g){
    	//Initialise les couleurs du tableau initial
        Paint paint;
        Graphics2D g2d;
        if (g instanceof Graphics2D) {
            g2d = (Graphics2D) g;
        }
        else {
            System.out.println("Error");
            return;
        }
        paint = new GradientPaint(0,0,getBackground(), getWidth(), getHeight(), Color.BLACK);
        g2d.setPaint(paint);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
