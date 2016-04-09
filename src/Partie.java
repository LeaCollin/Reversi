
public class Partie {

	private Joueur j1;
	private Joueur j2;
	private Plateau p;
	private Boolean tourBlanc;
	
	public Partie(){
		this.j1=new Joueur(Couleur.Noir);
		this.j2=new Joueur(Couleur.Blanc);
		this.p=new Plateau(8);	
		this.tourBlanc=false;
	}
	
	public void calculerScore(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if(p.getPion(p.getCase(i, j)).getCouleur()==Couleur.Noir){
					j1.setScore(j1.getScore()+1);
				} else if(p.getPion(p.getCase(i, j)).getCouleur()==Couleur.Blanc){
					j2.setScore(j2.getScore()+1);
				}
			}
		}
		System.out.println("Joueur Blanc a: "+j2.getScore()+" points \n Joueur Noir a: "+j1.getScore()+" Points.");
	}
	

	public void jeu(){
			tourBlanc = !tourBlanc;
//			calculerScore();
			p.afficherLesPossibilites();

		
	}
	
	public Plateau getP() {
		return p;
	}
}
