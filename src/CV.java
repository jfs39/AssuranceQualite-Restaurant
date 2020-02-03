import javax.swing.JOptionPane;

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
	JOptionPane.showMessageDialog(null , "Bienvenue chez Barette!");
	CV cv = new CV();
	cv.setPrenom(JOptionPane.showInputDialog("Veuillez entrer votre pr�nom : "));
	cv.setNom(JOptionPane.showInputDialog("Veuillez entrer votre nom : "));
	cv.setFormation(JOptionPane.showInputDialog("Veuillez entrer votre formation : "));
	
	
	affiche(cv);
	
	}
	
	public static void affiche(CV cv) {
		JOptionPane.showMessageDialog(null,"Nom: " + cv.getNom() + "\nPrenom: "+ cv.getPrenom() );
	}
	
	public String afficherCompetences(String[]comp) {
		String tamere="";
		for (int i = 0; i < comp.length; i++) {
			tamere += comp[i] + ", ";
		}
		return tamere;
	}

}
