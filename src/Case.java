import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Case extends JPanel{

	private boolean selectionnee;
	
	public Case(){
		setLayout(new GridLayout(1,0));
	}
	
	public boolean isSelectionnee(){
		return selectionnee;
	}
	
	public void setSelectionnee(boolean selectionnee) {
        this.selectionnee = selectionnee;
        if(selectionnee){
        	//On change de couleur la case s�lectionn�e
            setBackground(Color.BLUE);
            setForeground(Color.LIGHT_GRAY);
        }
        
    }
}
