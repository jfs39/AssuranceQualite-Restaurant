
import java.io.*;
import outilsjava.OutilsFichier;
import outilsjava.OutilsLecture; 

public class Principal {
	
	private BufferedReader fichierCourant;
	
	public Principal(BufferedReader fic) {
		
		this.fichierCourant = fic;
	}
	
	/*----------------------M�thodes Essentielles-----------------------*/
	
	public static void main(String[] args) {

	/*---------------------------- Pour lire des informations ------------------------------*/
	
//	Scanner in = new Scanner(System.in); 
//	
//	String prenom, nom, formation, attentes;
//	int experience;
//	String[] competences;
//	
//	System.out.print("\nVeuillez entrer votre pr�nom: ");
//	prenom = in.nextLine();
//	
//	System.out.print("\nVeuillez entrer votre nom: ");
//	nom = in.nextLine();
//	
//	System.out.print("\nVeuillez entrer votre formation: ");
//	formation = in.nextLine();
//	
//	experience = OutilsLecture.lireEntierValide("\nVeuillez entrer votre exp�rience en nombre d'ann�es: ", 0, 100);
//	
//	System.out.print("\nVeuillez entrer vos comp�tences (s�par�es par des virgules): ");
//	String competenceCourante = in.nextLine();
//	
//	System.out.print("\nVeuillez entrer vos attentes face au cours 4B4: ");
//	attentes= in.nextLine();
//	
//	competences = competenceCourante.split(",");
	
//	/*-----------------------------Donn�es en test----------------------------*/
//	
//	String[] competencesJF = new String[4];
//	competencesJF[0] = "Java";
//	competencesJF[1] = "HTML";
//	competencesJF[2] = "CSS";
//	competencesJF[3] = "C#";
//	
//	String[] competencesSteph = new String[4];
//	competencesSteph[0] = "C#";
//	competencesSteph[1] = "Javascript";
//	competencesSteph[2] = "SQL";
//	competencesSteph[3] = "JQuery";
//	
//	ObjetCV cvJF = new ObjetCV("Sergerie", "Jean-Fran�ois", "420.AA", 2, competencesJF, "Aucune attente");
//	ObjetCV cvSteph = new ObjetCV("Leduc", "St�phanie", "420.AA", 1, competencesSteph, "Aucune attente");
//	
//	/*------------------------------------affichage------------------------------------*/
//	
//	affiche(cvJF);
//	affiche(cvSteph);
//	
//	}
//	
//	public static void affiche(ObjetCV cvJF) {
//		
//		
//		try {
//			
//			int longueur = cvJF.getCompetences().length;
//			String competences = "";
//			
//			for (int i = 0; i < longueur-1; i++) {
//				competences += cvJF.getCompetences()[i] + ", ";
//			}
//			
//			competences += cvJF.getCompetences()[longueur-1];
//			
//			System.out.println("\nNom: " + cvJF.getNom() + "\n" + 
//					"Prenom: " + cvJF.getPrenom() + "\n" +
//					"Formation: " + cvJF.getFormation() + "\n" + 
//					"Ann�e(s) d'exp�rience: " + cvJF.getExp() + "\n" +
//					"Liste de comp�tences: " + competences + "\n" +
//					"Attentes face au cours 4B4: " + cvJF.getAttentes() );
//		} catch (Exception e) {
//			System.out.println("Erreur lors de la lecture des renseignements. Message: " + e.getMessage().toString());
//		}
//		
//	---------------------------------------------------------- Partie 2 ----------------------------------------------------------------------

		System.out.println("Bienvenue chez Barette!\n");
		System.out.println("Veuillez vous assurer que le fichier texte est dans le bon r�pretoire.\n");
		
		String monFichier = OutilsFichier.lireNomFichier("Entrez le nom du fichier : ");
		
		BufferedWriter fic = OutilsFichier.ouvrirFicTexteEcriture(monFichier);
		//catch pas trop comment sa fonctionne
		
		
		
		
		

	
	
	}
	
}
