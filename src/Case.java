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

	public Case(Couleur couleur){
        setLayout(new GridLayout(1,0));
        this.couleur=couleur;
        initCouleur();
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public boolean isSelectionnee() {
        return selectionnee;
    }

    public void setSelectionnee(boolean selectionnee) {
        this.selectionnee = selectionnee;
    }
    
    void initCouleur(){
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
