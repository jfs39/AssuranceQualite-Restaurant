
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class PrincipalTest {
	
	/*Tests sous TDD / réusinage du code*/
	
	@Test
	public void testTipFonctionnel() {
		
	assertEquals(5.75,Principal.calculerTip(5.0, 0.15),1337);
		
	}
	
	@Test
	public void testCreationFacturesFonctinnelle() {
		
	assertEquals(true,Principal.creerFactures());
		
	}
	
	@Test
	public void testImprimerErreurs() {
		ArrayList<String> tabErreurs = new ArrayList<String>();
		tabErreurs.add("Test Erreur");
	assertEquals(true,Principal.imprimerErreurs(tabErreurs));
		
	}
	
	@Test
	public void testAfficherFacturesAvecTaxes() {
		ArrayList<Facture> tabDeFactures = new ArrayList<Facture>();
		tabDeFactures.add(new Facture("JF"));
		tabDeFactures.add(new Facture("Stouf"));
		tabDeFactures.add(new Facture("Joseph"));
	assertEquals(true,Principal.afficherFactureAvecTaxes(tabDeFactures));
		
	}
	
	@Test
	public void testCreerFichierFacture() throws FileNotFoundException, IOException {
		
	assertEquals(true,Principal.creationFichierFacture());
		
	}
	
	@Test
	public void testAffichageFacture() throws FileNotFoundException, IOException {
		
		FileOutputStream outputStream = new FileOutputStream(new File("commandeTest.txt"));
		ArrayList<Facture> tabDeFactures = new ArrayList<Facture>();
		tabDeFactures.add(new Facture("JeanFrancois","Repas_Poulet",2,30));
		tabDeFactures.add(new Facture("Stephanie","Repas_Poulet",1,50));
		tabDeFactures.add(new Facture("Joseph","Frites",4,10));
		
	assertEquals(true,Principal.afficherFactureDansFichier(outputStream, tabDeFactures));
		
	}
	
	
	@Test
	public void testFactureClientExiste() {
		
		ArrayList<Facture> tab = new ArrayList<Facture>();
		tab.add(new Facture("Danielle"));
		tab.add(new Facture("Bryan"));
		tab.add(new Facture("Dumbo"));
		
		assertEquals(true,Principal.factureClientExiste("Danielle",tab ));  //Si le client existe
		assertEquals(false,Principal.factureClientExiste("Celine",tab));  //Si le client n'existe pas
		
	}
	
	/***********Tests unitaires************/
	
	@Test
	public void testCalculTaxes() {
		
		assertEquals(17.88,Principal.calculerTaxes(15.55), 17.77);
		
	}
	
	@Test
	public void testPlatConforme() {
		
		assertEquals(true,Principal.estConformePlat("Repas_Poulet 1.75")); //Si le plat est valide
		assertEquals(false,Principal.estConformePlat("Repas    Oriinal     2")); // Si le plat n'est pas valide
	}
	
	@Test
	public void testClientConforme() {
		
		assertEquals(true,Principal.estConformeClient("Celine")); //Si le client est conforme au format
		assertEquals(false,Principal.estConformeClient("Jean Maurice Duvent"));  //Si le client n'est pas conforme au format
	}
	
	@Test
	public void testFichierCommandeConforme() {
		
		assertEquals(true,Principal.readFile("src/commande.txt")); //Si le la commande existe et qu'elle est valide 
		assertEquals(false,Principal.readFile("src/okBoomer.txt"));  //Si la commande n'existe pas ou qu'elle n'est pas valide
	}
	
	@Test
	public void testAjouterErreur() {
		assertEquals(true, Principal.ajouterLigneErreur("test"));
	}
	
	@Test
	public void testLireFichierCommande() {
		assertEquals(true, Principal.lireFichierCommande());
	}
	
	@Test
	public void testAfficherTitre() {
		assertEquals(true, Principal.afficherTitre());
	}

}
