
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
		
		testerTab(erreurs);
		
		creerFactures();
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
			
			for (int i = 0; i < longueur-1; i++) {
				competences += cvJF.getCompetences()[i] + ", ";
			}
			
			competences += cvJF.getCompetences()[longueur-1];
			
			System.out.println("\nNom: " + cvJF.getNom() + "\n" + 
					"Prenom: " + cvJF.getPrenom() + "\n" +
					"Formation: " + cvJF.getFormation() + "\n" + 
					"Année(s) d'expérience: " + cvJF.getExp() + "\n" +
					"Liste de compétences: " + competences + "\n" +
					"Attentes face au cours 4B4: " + cvJF.getAttentes() );
		} catch (Exception e) {
			System.out.println("Erreur lors de la lecture des renseignements. Message: " + e.getMessage().toString());
		}
		
	}
	
	private static void creerFactures() {
		ArrayList<Plat> tabPlats = new ArrayList<Plat>();
		
		Facture[] facturesOuvertes = new Facture[commandes.size()];
		String[] commandeCourante;
		String platCourant, clientCourant, qteCourante;
		
		String[] repas;
		String nom;
		double prix;
		
		for (int i = 0; i < plats.size(); i++) {
			repas = plats.get(i).split(" ");
			nom = repas[0];
			prix = Double.parseDouble(repas[1]);
			Plat plat = new Plat(nom,prix);
			tabPlats.add(plat);
			
		}
		
		for (int i = 0; i < commandes.size(); i++) {
		
			commandeCourante = commandes.get(i).split(" ");

			
			clientCourant = commandeCourante[0];
			platCourant = commandeCourante[1];
			qteCourante = commandeCourante[2];
			
			Facture f = new Facture(clientCourant,platCourant,Integer.parseInt(qteCourante),tabPlats.get(i).getPrixPlat());//TODO pas sur
			facturesOuvertes[i] = f;
			
		}
		
		
		
	}

	private static Facture[] fusionnerFactures(Facture[] f) {
		Facture[] factures = new Facture[clients.size()];
	
		
		
		return factures;
		
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
				} else if(ligne.equals("Fin")){
					sectionCourante = "Fin";
				}
					
				
				switch (sectionCourante) {
				case "Clients":
					if(estConformeClient(ligne)) {
						clients.add(ligne);
					} else if (!ligne.equalsIgnoreCase(sectionCourante + " :")){
						ajouterLigneErreur("Il y a une erreur dans la section \"Clients\". Ligne: " + ligne);
					}
					
					break;
					
				case "Plats":
					if (estConformePlat(ligne) && !ligne.equalsIgnoreCase(sectionCourante + " :")) {
						plats.add(ligne);
					} else if (!ligne.equalsIgnoreCase(sectionCourante + " :")){
						ajouterLigneErreur("Il y a une erreur dans la section \"Plats\".");
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
		
		if (subdivision.length > 2) {
			conforme = false;
		} 		
		return conforme;
	}
	
	private static void ajouterLigneErreur(String message) {
		erreurs.add(message);
	}
	
	private static void testerTab(ArrayList<String> tab) {
		if (tab.size()!=0) {
			System.out.println(tab.toString());
		}
		
	}
	
}
