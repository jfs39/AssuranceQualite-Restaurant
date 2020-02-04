import java.util.Scanner; 

public class CV {

	/*-----------------------Cr�ation de l'objet Cv----------------------*/
	
	private String nom;
	private String prenom;
	private String formation;
	private int experience; 
	private String[] competences;
	private String attentes4B4;
	
	public CV(){
	
	}
	
	public CV(String pNom,String pPrenom,String pFormation,int pExperience, String[] pCompetences, String pAttentes4B4) {
		this.nom = pNom;
		this.prenom = pPrenom;
		this.formation = pFormation;
		this.experience = pExperience;
		this.competences = pCompetences;
		this.attentes4B4= pAttentes4B4;
	}
	
	/*--------------------- M�thodes Get ------------------------*/
	public String getNom(){
		return this.nom;
	}
	
	public String getPrenom() {
		return this.prenom;
	}
	
	public String getFormation() {
		return this.formation;
	}
	
	public int getExp() {
		return this.experience;
	}
	
	public String[] getCompetences() {
		return this.competences;
	}
	
	public String getAttentes() {
		return this.attentes4B4;
	}
	
	/*--------------------- M�thodes Set ------------------------*/
	
	public void setNom(String nom){
		this.nom = nom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public void setFormation(String formation) {
		this.formation = formation;
	}
	
	public void setExp(int exp) {
		this.experience = exp;
	}
	
	public void setCompetences(String[] competences) {
		this.competences = competences;
	}
	
	public void setAttentes(String attentes) {
		this.attentes4B4 = attentes;
	}
	
	/*----------------------M�thodes Essentielles-----------------------*/
	
	public static void main(String[] args) {
	
	Scanner in = new Scanner(System.in); 
	
	String prenom, nom, formation, attentes;
	int experience;
	String competences;
	
	

	System.out.println("Bienvenue chez Barette!");
	
	System.out.print("\nVeuillez entrer votre pr�nom: ");
	prenom = in.nextLine();
	
	
	System.out.print("\nVeuillez entrer votre nom: ");
	nom = in.nextLine();
	
	System.out.print("\nVeuillez entrer votre formation: ");
	formation = in.nextLine();
	
	System.out.print("\nVeuillez entrer votre exp�rience en nombre d'ann�es: ");
	String blabla = in.nextLine();
	
	try {
		experience = Integer.parseInt(blabla);
	} catch (Exception e) {
		experience = 0;
	}
	
	System.out.print("\nVeuillez entrer vos comp�tences (s�par�es par des virgules): ");
	competences = in.nextLine();
	
	System.out.print("\nVeuillez entrer vos attentes face au cours 4B4: ");
	attentes= in.nextLine();
	
	
	CV cv = new CV();
	
	
	affiche(cv);
	
	}
	
	public static void affiche(CV cv) {
		System.out.println("\nNom: " + cv.getNom() + "\n" + 
				"Prenom: " + cv.getPrenom() + "\n" +
				"Formation: " + cv.getFormation() + "\n" + 
				"Ann�e(s) d'exp�rience: " + cv.getExp() + "\n" +
				"Liste de comp�tences: " + cv.getCompetences() + "\n" +
				"Attentes face au cours 4B4: " + cv.getAttentes() );
		
	}
	
	public String afficherCompetences(String[]comp) {
		String tamere="";
		for (int i = 0; i < comp.length; i++) {
			tamere += comp[i] + ", ";
		}
		return tamere;
	}

}
