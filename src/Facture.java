import outilsjava.OutilsAffichage;

public class Facture {

	private String nomClient;
	private String plat;
	private int qte;
	private double prixRepas;
	private double prixtotal;

	
	/* CONSTRUCTEURS */
	
	public Facture() {
	}
	
	public Facture(String nomClient) 
	{
		
		this.nomClient = nomClient;
		this.prixtotal = 0;
	}
	
	public Facture(String nomClient,String plat,int qte, double prix ) {
		
		this.nomClient = nomClient;
		this.plat = plat;
		this.qte = qte;
		this.prixRepas = prix;
		this.prixtotal = calculerPrixTotal(prix, qte);
		
	}
	
	/* 	M�THODES PROPRES � LA CLASSE */

	private double calculerPrixTotal(double prixRepas, int qte) {
		return prixRepas * qte;
		
	}
	
	public void ajouterRepas(int qte, double prix) {
		if (prix>0) {
			this.prixtotal += (qte * prix);
		}
		
	}
	
	@Override
    public String toString() { 
        return this.getNomClient(); 
    } 
	
	public void afficher() {
		System.out.println(this.nomClient +" "+ OutilsAffichage.formaterMonetaire(this.prixtotal, 2));
	}

	
	/* M�THODES GETTER AND SETTER */
	
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
}
