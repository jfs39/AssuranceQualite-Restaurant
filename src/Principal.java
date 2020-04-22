
import java.io.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import outilsjava.OutilsAffichage;
import outilsjava.OutilsFichier;


public class Principal {

	private static ArrayList<String> clients = new ArrayList<String>();
	private static ArrayList<String> plats = new ArrayList<String>();
	private static ArrayList<String> commandes = new ArrayList<String>();
	private static ArrayList<Plat> tabPlats = new ArrayList<>();
	private static ArrayList<Commande> tabCommandes = new ArrayList<>();
	private static ArrayList<Facture> tabFactures = new ArrayList<>();

	private static ArrayList<String> erreurs = new ArrayList<String>();
	
	private static boolean formatRespecte = true;

	/*----------------------Méthodes Essentielles-----------------------*/

	public static void main(String[] args) throws IOException {

		

		afficherTitre();

		lireFichierCommande();
		
		if (formatRespecte) {	
			creerFactures();			
		}

		imprimerErreurs(erreurs);
		afficherFactureAvecTaxes(tabFactures);
		
		creationFichierFacture();

	}

	public static boolean lireFichierCommande() {
		boolean fonctionne = true;
		try {
			String nomFichier = OutilsFichier.lireNomFichier("Entrez le nom du fichier ainsi que l'extension : ");
			readFile("src/" + nomFichier);
		} catch (Exception e) {
			fonctionne = false;
		}
		return fonctionne;
	}

	public static boolean afficherTitre() {
		boolean fonctionne = true;
		try {
			System.out.println("Bienvenue chez Barette!\n");
		} catch (Exception e) {
			fonctionne = false;
		}
		return fonctionne;
	}

	public static boolean creationFichierFacture() throws FileNotFoundException, IOException {
		boolean fonctionne = true;
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
			LocalDateTime now = LocalDateTime.now();
			String temps = dtf.format(now);
			String[] maintenant = temps.split(" ");
			
			FileOutputStream outputStream = new FileOutputStream("src/Facture-du-"+ maintenant[0] + "T" + maintenant[1] + ".txt");
			
			afficherDateEtHeureDansFichier(maintenant, outputStream);
			
			afficherFactureDansFichier(outputStream,tabFactures);
			
			String footerFacture = "\nMerci de votre visite!";
			outputStream.write(footerFacture.getBytes());
			
			outputStream.close();

			FileOutputStream outputStreamErreur = ajoutErreursDansFichier();
			
			outputStreamErreur.close();
		} catch (Exception e) {
			fonctionne = false;
		}
	
		return fonctionne;
	}

	public static void afficherDateEtHeureDansFichier(String[] maintenant, FileOutputStream outputStream)
			throws IOException {
		String titre = "\n--------Chez Barette--------\n\nFacture en date du " + maintenant[0];
		outputStream.write(titre.getBytes());
		String heure = "\n" + maintenant[1] + "\n\n";
		outputStream.write(heure.getBytes());
	}

	public static FileOutputStream ajoutErreursDansFichier() throws FileNotFoundException, IOException {
		FileOutputStream outputStreamErreur = new FileOutputStream("src/Erreurs.txt");
		for (int i = 0; i < erreurs.size(); i++) {
			
			String erreur = erreurs.get(i) + "\n";
			outputStreamErreur.write(erreur.getBytes());
			
		}
		return outputStreamErreur;
	}

	public static boolean afficherFactureDansFichier(FileOutputStream outputStream,ArrayList<Facture> tabDeFactures) throws IOException {
		boolean fonctionne = true;
		try {
			for (int i = 0; i < tabDeFactures.size(); i++) {

				String facture = "\n"+tabDeFactures.get(i).getNomClient() + " "
						+ OutilsAffichage.formaterMonetaire(tabDeFactures.get(i).getPrixtotal(), 2) + " ( Tip 15% = "
						+ OutilsAffichage.formaterMonetaire((tabDeFactures.get(i).getPrixtotal() * 0.15), 2) + " / 18% = "
						+ OutilsAffichage.formaterMonetaire((tabDeFactures.get(i).getPrixtotal() * 0.18), 2) + " / 20% = "
						+ OutilsAffichage.formaterMonetaire((tabDeFactures.get(i).getPrixtotal() * 0.2), 2) + " )\n";

				outputStream.write(facture.getBytes());
				
				String tips ="\n----------------------------\n"+"\nTotaux possibles avec tips(facultatif) pour : "+ tabDeFactures.get(i).getNomClient()+"\n"
				+ "\nTotal avec 15% de tip : "+OutilsAffichage.formaterMonetaire(calculerTip(tabDeFactures.get(i).getPrixtotal(), 1.15), 2)
				+ "\nTotal avec 20% de tip : "+OutilsAffichage.formaterMonetaire(calculerTip(tabDeFactures.get(i).getPrixtotal(), 1.20), 2)
				+ "\nTotal avec 25% de tip : "+OutilsAffichage.formaterMonetaire(calculerTip(tabDeFactures.get(i).getPrixtotal(), 1.25), 2) + "\n\n----------------------------\n";
				
				outputStream.write(tips.getBytes());
				
			}

		} catch (Exception e) {
			fonctionne = false;
		}
		return fonctionne;
	}

	public static boolean afficherFactureAvecTaxes(ArrayList<Facture> tabDeFactures) {
		boolean fonctionne = true;
		try {
			for (int j = 0; j < tabDeFactures.size(); j++) {
						
						tabDeFactures.get(j).setPrixtotal(calculerTaxes(tabDeFactures.get(j).getPrixtotal()));
						tabDeFactures.get(j).afficher();
						
					}
		} catch (Exception e) {
			fonctionne = false;
		}
		
		return fonctionne;
	}

	public static boolean imprimerErreurs(ArrayList<String> tabErreurs) {
		boolean fonctionne = true;
		try {
			for (int i = 0; i < tabErreurs.size(); i++) {
				System.out.println(tabErreurs.get(i));
			}
		} catch (Exception e) {
			fonctionne = false;
		}
		return fonctionne;
	}

//	private static void gererFactureVides() {
//
//		for (int i = 0; i < clients.size(); i++) {
//
//			if (!factureClientExiste(clients.get(i),tabFactures)) {
//
//				tabFactures.add(new Facture(clients.get(i)));
//
//			}
//		}
//
//	}

	public static boolean creerFactures() {
		boolean fonctionne = true;
		String[] commandeCourante;
		String[] repasCourant;
		String[] listeClientCommande = new String[clients.size()];
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
		LocalDateTime now = LocalDateTime.now();
		String temps = dtf.format(now);
		String[] maintenant = temps.split(" ");

		int compteur = 0;

		for (int i = 0; i < plats.size(); i++) {

			repasCourant = plats.get(i).split(" ");
			Plat plat = new Plat(repasCourant[0], Double.parseDouble(repasCourant[1]));
			tabPlats.add(plat);
			
		}

		for (int i = 0; i < commandes.size(); i++) {

			commandeCourante = commandes.get(i).split(" ");
			Commande commande = new Commande();
			
			try {
				commande.setNomClient(commandeCourante[0]);
				commande.setNomPlat(commandeCourante[1]);
				commande.setQte(Integer.parseInt(commandeCourante[2]));

				tabCommandes.add(commande);
				
			} catch (Exception e) {
				System.out.println("Problème lors de la lecture de la commande. Revoir votre commande, elle est peut-être invalide.");
				fonctionne = false;
				erreurs.add("Erreur dans la section Commandes à la ligne: " + e.getMessage());
				
			}

		}

		
		
		for (int i = 0; i < tabCommandes.size(); i++) {
			boolean ok = false;
			Commande commandeTraitee = tabCommandes.get(i);

			double prixRepas = 0;
			
			for (int j = 0; j < tabPlats.size(); j++) {
				
				if (tabPlats.get(j).getNomPlat().equalsIgnoreCase(commandeTraitee.getNomPlat())) {
					ok = true;
					prixRepas = tabPlats.get(j).getPrixPlat();
					break;
				} 
				
			}
			if(!ok) {
				erreurs.add("\nErreur, le plat "+ commandeTraitee.getNomPlat()+ " n'existe pas.\n");
			}
			Facture facture;

			if (!factureClientExiste(commandeTraitee.getNomClient(),tabFactures)) {

				facture = new Facture(commandeTraitee.getNomClient(), commandeTraitee.getNomPlat(),
						commandeTraitee.getQte(), prixRepas);
				
				tabFactures.add(facture);
				listeClientCommande[compteur] = commandeTraitee.getNomClient();
				compteur++;

			} else {

				for (int j = 0; j < tabFactures.size(); j++) {
					
					if (tabFactures.get(j).getNomClient().toString().equals(commandeTraitee.getNomClient())) {
						
						facture = tabFactures.get(j);
						facture.ajouterRepas(commandeTraitee.getQte(), prixRepas);

					}
				}

			}

		}
		return fonctionne;
	}

	public static boolean factureClientExiste(String nomClient, ArrayList<Facture> tabDeFactures) {
		boolean existe = false;

		for (int i = 0; i < tabDeFactures.size(); i++) {

			if (tabDeFactures.get(i).toString().equals(nomClient)) {
				
				existe = true;
				break;
				
			}
		}

		return existe;
	}

	public static boolean readFile(String nomFichier) {
		boolean valide = true;
		boolean erreurValide = true;
		String sectionCourante = "";
		String ligne = "";

		try {
			
			Scanner scan = new Scanner(new File(nomFichier));
			
			while (scan.hasNextLine()) {
				ligne = scan.nextLine();

				if (ligne.equals("Clients :")) {
					sectionCourante = "Clients";
				} else if (ligne.equals("Plats :")) {
					sectionCourante = "Plats";
				} else if (ligne.equals("Commandes :")) {
					sectionCourante = "Commandes";
				} else if (ligne.equals("Fin")) {
					sectionCourante = "Fin";
				}

				switch (sectionCourante) {
				case "Clients":
				
					if (estConformeClient(ligne)) {
						
						clients.add(ligne);
				
					} else if (!ligne.equalsIgnoreCase(sectionCourante + " :")) {
						
						ajouterLigneErreur("\nIl y a une erreur dans la section \"Clients\" à la ligne: " + ligne);
						System.out.println("\nLe Fichier ne respecte pas le format demandé");
						erreurValide=false;
						formatRespecte = false;
						valide = false;
					}

					break;

				case "Plats":
				
					if (estConformePlat(ligne) && !ligne.equalsIgnoreCase(sectionCourante + " :")) {
					
						plats.add(ligne);
				
					} else if (!ligne.equalsIgnoreCase(sectionCourante + " :") ) {
						if (erreurValide) {
							System.out.println("\nLe Fichier ne respecte pas le format demandé");
						}
						ajouterLigneErreur("\nIl y a une erreur dans la section \"Plats\" à la ligne: " + ligne);
						formatRespecte = false;
						valide = false;
						
					}

					break;

				case "Commandes":
			
					if (!ligne.equalsIgnoreCase(sectionCourante + " :")) {
						
						commandes.add(ligne);
					} 
					break;
				}
			}

			scan.close();
			
		} catch (Exception e) {
		
			String erreur = "\nErreur avec le fichier. Celui-ci ne respecte pas le format demandé ou est introuvable.";
			erreurs.add(erreur);
			valide = false;
			
		}
		return valide;
	}
	
	public static boolean estConformeClient(String ligne) {
	
		return ligne.indexOf(" ") == -1 ;
		
	}
//	@SuppressWarnings("unused") //Cette méthode était utilisée dans la partie 2 mais est maintenant obsolète pour que la partie 3 fonctionne.
//	private static boolean estConformeCommande(String ligne) {
//		boolean conforme = false;
//		String[] subdivision = ligne.split(" ");
//		
//		
//		for (int i = 0; i < tabPlats.size(); i++) {
//			System.out.println(subdivision[i]);
//			if(tabPlats.get(i).getNomPlat().equals(subdivision[2])) {
//				conforme = true;
//				break;
//			}
//		}
//		return conforme;
//	}

	public static boolean estConformePlat(String ligne) {
	
		boolean conforme = true;
		String[] subdivision = ligne.split(" ");
		if (subdivision.length > 2 || !Character.isDigit(ligne.charAt(ligne.length() - 1))) {
		
			conforme = false;
			
		}
		
		return conforme;
	}

	public static boolean ajouterLigneErreur(String message) {
		boolean messageAjoute = true;
		
		try {
			erreurs.add(message);
		} catch (Exception e) {
			messageAjoute = false;
		}
		return messageAjoute;
	}

	//Méthode pour tester les tableaux

//	private static void testerTab(ArrayList<String> tab) {
//	
//		if (tab.size() != 0) {
//			
//			System.out.println(tab.toString());
//			
//		}
//
//	}
	
	public static double calculerTaxes(double sousTotal) {
		sousTotal *= 1.14975;
		return sousTotal;
	}
	
	public static double calculerTip(double sousTotal, double tipChoisi) {
		return sousTotal *= tipChoisi;
	}
}
