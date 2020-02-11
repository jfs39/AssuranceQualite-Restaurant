
public class ObjetCommande {
	
	private String nomCommandeur;
	private int qte;
	private double prix;
	
	/*--------Constructeurs de l'objet--------*/
	
	public ObjetCommande() {
		
	}
	
	public ObjetCommande(String pNomCommandeur, double pPrix ,int pQte) {
		this.nomCommandeur = pNomCommandeur;
		this.prix = pPrix;
	}
	
	/*--------------------Méthodes get/set---------------------*/
	
	public String getNomCommandeur() {
		return this.nomCommandeur;
	}
	
	public int getQte() {
		return this.qte;
	}
	
	public double getPrix() {
		return this.prix;
	}
	
	public void setNomCommandeur(String nomCommandeur) {
		this.nomCommandeur= nomCommandeur;
	}
	
	public void setQte(int qte) {		
		this.qte= qte;
	}
	
	public void setPrix(double prix) {
		this.prix= prix;
	}

}
