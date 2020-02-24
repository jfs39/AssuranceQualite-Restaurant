
import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import outilsjava.OutilsFichier;
import outilsjava.OutilsLecture;

public class Principal {

	private static ArrayList<String> clients = new ArrayList<String>();
	private static ArrayList<String> plats = new ArrayList<String>();
	private static ArrayList<String> commandes = new ArrayList<String>();
	private static ArrayList<Plat> tabPlats = new ArrayList<>();
	private static ArrayList<Commande> tabCommandes = new ArrayList<>();
	private static ArrayList<Facture> tabFactures = new ArrayList<>();

	private static ArrayList<String> erreurs = new ArrayList<String>();

	/*----------------------Méthodes Essentielles-----------------------*/

	public static void main(String[] args) {

		/*---------------------------- Partie 1 ------------------------------*/

//	lireEtAfficherCV();
//	---------------------------------------------------------- Partie 2 ----------------------------------------------------------------------

		System.out.println("Bienvenue chez Barette!\n");

		String nomFichier = OutilsFichier.lireNomFichier("Entrez le nom du fichier ainsi que l'extension : ");
		readFile("src/" + nomFichier);

		testerTab(clients);
		testerTab(plats);
		testerTab(commandes);

		creerFactures();

		testerTab(erreurs);
		
		
		for (int j = 0; j < tabFactures.size(); j++) {
			tabFactures.get(j).afficher();
		}
		
	}

	@SuppressWarnings("unused")
	private static void lireEtAfficherCV() {
//		Scanner in = new Scanner(System.in); 
//	
//	String prenom, nom, formation, attentes;
//	int experience;
//	String[] competences;
//	
//	System.out.print("\nVeuillez entrer votre prénom: ");
//	prenom = in.nextLine();
//	
//	System.out.print("\nVeuillez entrer votre nom: ");
//	nom = in.nextLine();
//	
//	System.out.print("\nVeuillez entrer votre formation: ");
//	formation = in.nextLine();
//	
//	experience = OutilsLecture.lireEntierValide("\nVeuillez entrer votre expérience en nombre d'années: ", 0, 100);
//	
//	System.out.print("\nVeuillez entrer vos compétences (séparées par des virgules): ");
//	String competenceCourante = in.nextLine();
//	
//	System.out.print("\nVeuillez entrer vos attentes face au cours 4B4: ");
//	attentes= in.nextLine();
//	
//	competences = competenceCourante.split(",");

//	/*-----------------------------Données en test----------------------------*/

		String[] competencesJF = new String[4];
		competencesJF[0] = "Java";
		competencesJF[1] = "HTML";
		competencesJF[2] = "CSS";
		competencesJF[3] = "C#";

		String[] competencesSteph = new String[4];
		competencesSteph[0] = "C#";
		competencesSteph[1] = "Javascript";
		competencesSteph[2] = "SQL";
		competencesSteph[3] = "JQuery";

		ObjetCV cvJF = new ObjetCV("Sergerie", "Jean-François", "420.AA", 2, competencesJF, "Aucune attente");
		ObjetCV cvSteph = new ObjetCV("Leduc", "Stéphanie", "420.AA", 1, competencesSteph, "Aucune attente");

		/*------------------------------------affichage------------------------------------*/

		affiche(cvJF);
		affiche(cvSteph);

	}

	public static void affiche(ObjetCV cvJF) {

		try {

			int longueur = cvJF.getCompetences().length;
			String competences = "";

			for (int i = 0; i < longueur - 1; i++) {
				competences += cvJF.getCompetences()[i] + ", ";
			}

			competences += cvJF.getCompetences()[longueur - 1];

			System.out.println("\nNom: " + cvJF.getNom() + "\n" + "Prenom: " + cvJF.getPrenom() + "\n" + "Formation: "
					+ cvJF.getFormation() + "\n" + "Année(s) d'expérience: " + cvJF.getExp() + "\n"
					+ "Liste de compétences: " + competences + "\n" + "Attentes face au cours 4B4: "
					+ cvJF.getAttentes());
		} catch (Exception e) {
			System.out.println("Erreur lors de la lecture des renseignements. Message: " + e.getMessage().toString());
		}

	}

	private static void creerFactures() {
		

		String[] commandeCourante;
		String[] repasCourant;
		String[] listeClientCommande = new String[clients.size()];
		
		int compteur = 0;

		for (int i = 0; i < plats.size(); i++) {

			repasCourant = plats.get(i).split(" ");
			Plat plat = new Plat(repasCourant[0], Double.parseDouble(repasCourant[1]));
			tabPlats.add(plat);
		}

		for (int i = 0; i < commandes.size(); i++) {

			commandeCourante = commandes.get(i).split(" ");
			Commande com = new Commande();
			try {
				com.setNomClient(commandeCourante[0]);
				com.setNomPlat(commandeCourante[1]);
				com.setQte(Integer.parseInt(commandeCourante[2]));

				tabCommandes.add(com);
			} catch (NumberFormatException e) {
				System.out.println("Problème lors de la lecture de la commande.");
				erreurs.add("Erreur dans la section Commandes à la ligne: " + commandeCourante[0] + " "
						+ commandeCourante[1] + " " + commandeCourante[2]);
			}

		}

		for (int i = 0; i < tabCommandes.size(); i++) {
			Commande commandeTraitee = tabCommandes.get(i);

			double prixRepas = 0;
			for (int j = 0; j < tabPlats.size(); j++) {
				if (tabPlats.get(j).getNomPlat().equalsIgnoreCase(commandeTraitee.getNomPlat())) {
					prixRepas = tabPlats.get(j).getPrixPlat();
					break;
				}
			}

			Facture facture;
			
			

			if (!factureClientExiste(commandeTraitee.getNomClient())) {
			
				facture = new Facture(commandeTraitee.getNomClient(), commandeTraitee.getNomPlat(),
						commandeTraitee.getQte(), prixRepas);
				tabFactures.add(facture);
				listeClientCommande[compteur] = commandeTraitee.getNomClient();
				compteur++;
				
			} else {
				
				for (int j = 0; j < tabFactures.size(); j++) {
					if (tabFactures.get(j).getNomClient().toString() == commandeTraitee.getNomClient()) {
						facture = tabFactures.get(j);
						facture.ajouterRepas(commandeTraitee.getQte(), prixRepas);
					}
				}
			

			}
		


		}
		
	

	}

	private static boolean factureClientExiste(String nomClient) {
		boolean existe = false;

		for (int i = 0; i < tabFactures.size(); i++) {
	
			if (tabFactures.get(i).toString() == nomClient) {
				existe = true;
				break;
			}
		}

		return existe;
	}

	private static void readFile(String nomFichier) {

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
						ajouterLigneErreur("Il y a une erreur dans la section \"Clients\" à la ligne: " + ligne);
					}

					break;

				case "Plats":
					if (estConformePlat(ligne) && !ligne.equalsIgnoreCase(sectionCourante + " :")) {
						plats.add(ligne);
					} else if (!ligne.equalsIgnoreCase(sectionCourante + " :")) {
						ajouterLigneErreur("Il y a une erreur dans la section \"Plats\" à la ligne: " + ligne);
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
			String erreur = "Le fichier ne respecte pas le format demandé!";
			System.out.println(erreur);
			erreurs.add(erreur);
			e.printStackTrace();
		}

	}

	private static boolean estConformeClient(String ligne) {
		return ligne.indexOf(" ") == -1;
	}

	private static boolean estConformePlat(String ligne) {
		boolean conforme = true;
		String[] subdivision = ligne.split(" ");

		if (subdivision.length > 2 || !Character.isDigit(ligne.charAt(ligne.length() - 1))) {
			conforme = false;
		}
		return conforme;
	}

	private static void ajouterLigneErreur(String message) {
		erreurs.add(message);
	}

	private static void testerTab(ArrayList<String> tab) {
		if (tab.size() != 0) {
			System.out.println(tab.toString());
		}

	}

}
