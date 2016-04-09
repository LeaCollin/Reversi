package Commun;

public class Commun {

	public static int NOMBRECOLONNES = 8;
	public static int NOMBRELIGNES = 8;
	
	public static enum Direction {
		CENTRE(0, 0),
		NORD(-1, 0), 
		SUD(1, 0), 
		EST(0, 1), 
		OUEST(0, -1), 
		NORDEST(-1, 1), 
		NORDOUEST(-1, -1), 
		SUDEST(1, 1), 
		SUDOUEST(1, -1);

		private int i;
		private int j;

		// Constructeur
		Direction(int pi, int pj) {
			this.i = pi;
			this.j = pj;
		}

		public String toString() {
			return i + ";" + j;
		}
		
		public int getI() {
			return this.i;
		}
		
		public int getJ() {
			return this.j;
		}
		
		public static Direction getDirection(int pi, int pj) {
			
			if(pi == -1) {
				if(pj == -1) return NORDOUEST;
				if(pj == 0)  return NORD;
				if(pj == 1)  return NORDEST;
			}
			else if(pi == 0) {
				if(pj == -1) return OUEST;
				if(pj == 0)  return CENTRE;
				if(pj == 1)  return EST;
			}
			else if(pi == 1) {
				if(pj == -1) return SUDOUEST;
				if(pj == 0)  return SUD;
				if(pj == 1)  return SUDEST;
			}
			
			return CENTRE;
		}
	}
}
