
public class Commande {
	
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public String getNomPlat() {
		return nomPlat;
	}
	public void setNomPlat(String nomPlat) {
		this.nomPlat = nomPlat;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	
	
	public Commande(String nomClient, String nomPlat, int qte) {
		this.nomClient = nomClient;
		this.nomPlat = nomPlat;
		this.qte = qte;
	}

	public Commande() {
		this.nomClient = null;
		this.nomPlat = null;
		this.qte = 0;
	}
	
	
	private String nomClient;
	private String nomPlat;
	private int qte;
	
	
}
