import outilsjava.OutilsAffichage;

public class Facture {

	private String nomClient;
	private String plat;
	private int qte;
	private double prixRepas;
	private double prixtotal;

	public Facture() {
	}
	
	public Facture(String nomClient,String plat,int qte, double prix ) {
		
		this.nomClient = nomClient;
		this.plat = plat;
		this.qte = qte;
		this.prixRepas = prix;
		this.prixtotal = calculerPrixTotal(prix, qte);
		
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getPlat() {
		return plat;
	}

	public void setPlat(String plat) {
		this.plat = plat;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public double getPrixRepas() {
		return prixRepas;
	}

	public void setPrixRepas(double prixRepas) {
		this.prixRepas = prixRepas;
	}

	public double getPrixtotal() {
		return prixtotal;
	}

	public void setPrixtotal(double prixtotal) {
		this.prixtotal = prixtotal;
	}
	
	private double calculerPrixTotal(double prixRepas, int qte) {
		return prixRepas * qte;
		
	}
	
	public void afficher() {
		System.out.println(this.nomClient +" "+ OutilsAffichage.formaterMonetaire(this.prixtotal, 2));
	}
}
