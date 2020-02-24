
public class Plat {

	private String nomPlat;
	private double prixPlat;
	
	/* CONSTRUCTEURS */
	public Plat() {
		
	}
	
	public Plat(String nomPlat, double prixPlat) {
		this.nomPlat = nomPlat;
		this.prixPlat = prixPlat;
	}
	
	/* METHODES GETTER AND SETTER */
	
	public String getNomPlat() {
		return nomPlat;
	}
	public void setNomPlat(String nomPlat) {
		this.nomPlat = nomPlat;
	}
	public double getPrixPlat() {
		return prixPlat;
	}
	public void setPrixPlat(double prixPlat) {
		this.prixPlat = prixPlat;
	}
	
}
