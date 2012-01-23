package FicheP;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.extensions.abbot.*;
import abbot.finder.ComponentNotFoundException;
import abbot.finder.MultipleComponentsFoundException;
import abbot.tester.ComponentTester;
import abbot.tester.FrameTester;
import junit.extensions.abbot.ComponentTestFixture;

/**
 * Source code for Tutorial 1. Simple unit tests for example.ArrowButton. Also
 * demonstrates the use of ComponentTestFixture.
 */

public class IHMTest extends ComponentTestFixture {

	private ComponentTester testerFrame;
	private Ihm_gestion_graph ihm;

	@Before
	protected void setUp() {
		ihm = new Ihm_gestion_graph();
		testerFrame = FrameTester.getTester(Ihm_gestion_graph.class);
		ihm.setVisible(true);
	}

	@After
	protected void tearDown() {
		testerFrame.keyRelease(17);
	}

	@Test
	public void AjoutFiche() throws ComponentNotFoundException,
			MultipleComponentsFoundException {
		// On ajoute la fiche <nom="Dupond", prenom = "Michel", Adresse=(12,
		// "rue de la cote",
		// 44000, "France"), mail ="dupond@yahoo.fr",tel 1478964512>

		testerFrame.actionClick(ihm.getJtf_nom());
		testerFrame.keyString("Dupond");
		assertEquals("Valeur attendu : Dupont Valeur actuelle : "
				+ ihm.getJtf_nom().getText(), "Dupond", ihm.getJtf_nom()
				.getText());
		testerFrame.actionClick(ihm.getJtf_prenom());
		testerFrame.keyString("Michel");
		assertEquals("Valeur attendu : Michel Valeur actuelle : "
				+ ihm.getJtf_prenom().getText(), "Michel", ihm.getJtf_prenom()
				.getText());
		testerFrame.actionClick(ihm.getJtf_num());
		testerFrame.keyString("12");
		assertEquals("Valeur attendu : 12 Valeur actuelle : "
				+ ihm.getJtf_num().getText(), "12", ihm.getJtf_num().getText());
		testerFrame.actionClick(ihm.getJtf_rue());
		testerFrame.keyString("rue de la cote");
		assertEquals("Valeur attendu : rue de la cote Valeur actuelle : "
				+ ihm.getJtf_rue().getText(), "rue de la cote", ihm
				.getJtf_rue().getText());
		testerFrame.actionClick(ihm.getJtf_codep());
		testerFrame.keyString("44000");
		assertEquals("Valeur attendu : 44000 Valeur actuelle : "
				+ ihm.getJtf_codep().getText(), "44000", ihm.getJtf_codep()
				.getText());
		testerFrame.actionClick(ihm.getJtf_pays());
		testerFrame.keyString("France");
		assertEquals("Valeur attendu : France Valeur actuelle : "
				+ ihm.getJtf_pays().getText(), "France", ihm.getJtf_pays()
				.getText());
		testerFrame.actionClick(ihm.getJtf_mail());
		testerFrame.keyString("dupond@yahoo.fr");
		assertEquals("Valeur attendu : dupond@yahoo.fr Valeur actuelle : "
				+ ihm.getJtf_mail().getText(), "dupond@yahoo.fr", ihm
				.getJtf_mail().getText());
		testerFrame.actionClick(ihm.getJtf_tel());
		testerFrame.keyString("1478964512");
		assertEquals("Valeur attendu : 1478964512 Valeur actuelle : "
				+ ihm.getJtf_tel().getText(), "1478964512", ihm.getJtf_tel()
				.getText());
		testerFrame.actionClick(ihm.getBut_add());
		
		//On accède à la liste des fiches et on vérifie qu'elle a été ajouté
		Fiche f = ihm.getFiches().get(0);
		assertNotNull(f);
		assertEquals("Valeur attendu : Dupont Valeur actuelle : "
				+ f.getM_nom(), "Dupond", f.getM_nom());
		assertEquals("Valeur attendu : 1478964512 Valeur actuelle : "
				+ f.getM_tel(), 1478964512, f.getM_tel());

	}

	@Test
	public void EgaliteFiche() {
		// On ajoute la fiche <nom="Dupond", prenom = "Michel" Adresse=(12,
		// "rue de la cote",
		// 44000, "France"), mail ="dupond@yahoo.fr",tel 1478964512>
		testerFrame.actionClick(ihm.getJtf_nom());
		testerFrame.keyString("Dupond");
		assertEquals("Valeur attendu : Dupont Valeur actuelle : "
				+ ihm.getJtf_nom().getText(), "Dupond", ihm.getJtf_nom()
				.getText());
		testerFrame.actionClick(ihm.getJtf_prenom());
		testerFrame.keyString("Michel");
		assertEquals("Valeur attendu : Michel Valeur actuelle : "
				+ ihm.getJtf_prenom().getText(), "Michel", ihm.getJtf_prenom()
				.getText());
		testerFrame.actionClick(ihm.getJtf_num());
		testerFrame.keyString("12");
		assertEquals("Valeur attendu : 12 Valeur actuelle : "
				+ ihm.getJtf_num().getText(), "12", ihm.getJtf_num().getText());
		testerFrame.actionClick(ihm.getJtf_rue());
		testerFrame.keyString("rue de la cote");
		assertEquals("Valeur attendu : rue de la cote Valeur actuelle : "
				+ ihm.getJtf_rue().getText(), "rue de la cote", ihm
				.getJtf_rue().getText());
		testerFrame.actionClick(ihm.getJtf_codep());
		testerFrame.keyString("44000");
		assertEquals("Valeur attendu : 44000 Valeur actuelle : "
				+ ihm.getJtf_codep().getText(), "44000", ihm.getJtf_codep()
				.getText());
		testerFrame.actionClick(ihm.getJtf_pays());
		testerFrame.keyString("France");
		assertEquals("Valeur attendu : France Valeur actuelle : "
				+ ihm.getJtf_pays().getText(), "France", ihm.getJtf_pays()
				.getText());
		testerFrame.actionClick(ihm.getJtf_mail());
		testerFrame.keyString("dupond@yahoo.fr");
		assertEquals("Valeur attendu : dupond@yahoo.fr Valeur actuelle : "
				+ ihm.getJtf_mail().getText(), "dupond@yahoo.fr", ihm
				.getJtf_mail().getText());
		testerFrame.actionClick(ihm.getJtf_tel());
		testerFrame.keyString("1478964512");
		assertEquals("Valeur attendu : 1478964512 Valeur actuelle : "
				+ ihm.getJtf_tel().getText(), "1478964512", ihm.getJtf_tel()
				.getText());
		testerFrame.actionClick(ihm.getBut_add());
		// On l'ajoute 2 fois
		testerFrame.actionClick(ihm.getBut_add());
		// Puis on les compare

		// On maintient Ctrl
		testerFrame.keyPress(17);

		// On clique sur les deux premières fiches créées
		testerFrame.actionClick(ihm.getTableau(), 0, 0);
		testerFrame.actionClick(ihm.getTableau(), 0, 20);

		// On relache Ctrl
		testerFrame.keyRelease(17);
		testerFrame.actionClick(ihm.getBut_comp());
		
		//On vérifie la cohérence du message de comparaison
		assertEquals("EGALES " + ihm.getLab().getText(), "EGALES", ihm.getLab()
				.getText());
	}

	@Test
	public void DifferenceFiche() {
		// On ajoute la fiche <nom="Dupond", prenom = "Michel", Adresse=(12,
		// "rue de la cote",
		// 44000, "France"), mail ="dupond@yahoo.fr",tel 1478964512>
		testerFrame.actionClick(ihm.getJtf_nom());
		testerFrame.keyString("Dupond");
		assertEquals("Valeur attendu : Dupont Valeur actuelle : "
				+ ihm.getJtf_nom().getText(), "Dupond", ihm.getJtf_nom()
				.getText());
		testerFrame.actionClick(ihm.getJtf_prenom());
		testerFrame.keyString("Michel");
		assertEquals("Valeur attendu : Michel Valeur actuelle : "
				+ ihm.getJtf_prenom().getText(), "Michel", ihm.getJtf_prenom()
				.getText());
		testerFrame.actionClick(ihm.getJtf_num());
		testerFrame.keyString("12");
		assertEquals("Valeur attendu : 12 Valeur actuelle : "
				+ ihm.getJtf_num().getText(), "12", ihm.getJtf_num().getText());
		testerFrame.actionClick(ihm.getJtf_rue());
		testerFrame.keyString("rue de la cote");
		assertEquals("Valeur attendu : rue de la cote Valeur actuelle : "
				+ ihm.getJtf_rue().getText(), "rue de la cote", ihm
				.getJtf_rue().getText());
		testerFrame.actionClick(ihm.getJtf_codep());
		testerFrame.keyString("44000");
		assertEquals("Valeur attendu : 44000 Valeur actuelle : "
				+ ihm.getJtf_codep().getText(), "44000", ihm.getJtf_codep()
				.getText());
		testerFrame.actionClick(ihm.getJtf_pays());
		testerFrame.keyString("France");
		assertEquals("Valeur attendu : France Valeur actuelle : "
				+ ihm.getJtf_pays().getText(), "France", ihm.getJtf_pays()
				.getText());
		testerFrame.actionClick(ihm.getJtf_mail());
		testerFrame.keyString("dupond@yahoo.fr");
		assertEquals("Valeur attendu : dupond@yahoo.fr Valeur actuelle : "
				+ ihm.getJtf_mail().getText(), "dupond@yahoo.fr", ihm
				.getJtf_mail().getText());
		testerFrame.actionClick(ihm.getJtf_tel());
		testerFrame.keyString("1478964512");
		assertEquals("Valeur attendu : 1478964512 Valeur actuelle : "
				+ ihm.getJtf_tel().getText(), "1478964512", ihm.getJtf_tel()
				.getText());
		testerFrame.actionClick(ihm.getBut_add());

		// On change le nom
		testerFrame.actionClick(ihm.getJtf_nom());
		testerFrame.keyString("Regis");
		assertEquals("Valeur attendu : Regis Valeur actuelle : "
				+ ihm.getJtf_nom().getText(), "Regis", ihm.getJtf_nom()
				.getText());
		testerFrame.actionClick(ihm.getBut_add());
		// Puis on les compare

		// On maintient Ctrl
		testerFrame.keyPress(17);

		// On clique sur les deux premières fiches créées
		testerFrame.actionClick(ihm.getTableau(), 0, 0);
		testerFrame.actionClick(ihm.getTableau(), 0, 20);

		// On relache Ctrl
		testerFrame.keyRelease(17);
		testerFrame.actionClick(ihm.getBut_comp());
		//On vérifie la cohérence du message de comparaison
		assertEquals(" PAS EGALES " + ihm.getLab().getText(), " PAS EGALES",
				ihm.getLab().getText());
	}

	@Test
	public void SuppressionFiche() {
		// On ajoute la fiche <nom="Dupond", prenom = "Michel", Adresse=(12,
		// "rue de la cote",
		// 44000, "France"), mail ="dupond@yahoo.fr",tel 1478964512>
		testerFrame.actionClick(ihm.getJtf_nom());
		testerFrame.keyString("Dupond");
		assertEquals("Valeur attendu : Dupont Valeur actuelle : "
				+ ihm.getJtf_nom().getText(), "Dupond", ihm.getJtf_nom()
				.getText());
		testerFrame.actionClick(ihm.getJtf_prenom());
		testerFrame.keyString("Michel");
		assertEquals("Valeur attendu : Michel Valeur actuelle : "
				+ ihm.getJtf_prenom().getText(), "Michel", ihm.getJtf_prenom()
				.getText());
		testerFrame.actionClick(ihm.getJtf_num());
		testerFrame.keyString("12");
		assertEquals("Valeur attendu : 12 Valeur actuelle : "
				+ ihm.getJtf_num().getText(), "12", ihm.getJtf_num().getText());
		testerFrame.actionClick(ihm.getJtf_rue());
		testerFrame.keyString("rue de la cote");
		assertEquals("Valeur attendu : rue de la cote Valeur actuelle : "
				+ ihm.getJtf_rue().getText(), "rue de la cote", ihm
				.getJtf_rue().getText());
		testerFrame.actionClick(ihm.getJtf_codep());
		testerFrame.keyString("44000");
		assertEquals("Valeur attendu : 44000 Valeur actuelle : "
				+ ihm.getJtf_codep().getText(), "44000", ihm.getJtf_codep()
				.getText());
		testerFrame.actionClick(ihm.getJtf_pays());
		testerFrame.keyString("France");
		assertEquals("Valeur attendu : France Valeur actuelle : "
				+ ihm.getJtf_pays().getText(), "France", ihm.getJtf_pays()
				.getText());
		testerFrame.actionClick(ihm.getJtf_mail());
		testerFrame.keyString("dupond@yahoo.fr");
		assertEquals("Valeur attendu : dupond@yahoo.fr Valeur actuelle : "
				+ ihm.getJtf_mail().getText(), "dupond@yahoo.fr", ihm
				.getJtf_mail().getText());
		testerFrame.actionClick(ihm.getJtf_tel());
		testerFrame.keyString("1478964512");
		assertEquals("Valeur attendu : 1478964512 Valeur actuelle : "
				+ ihm.getJtf_tel().getText(), "1478964512", ihm.getJtf_tel()
				.getText());
		testerFrame.actionClick(ihm.getBut_add());

		// On change le nom
		testerFrame.actionClick(ihm.getJtf_nom());
		testerFrame.keyString("Regis");
		assertEquals("Valeur attendu : Regis Valeur actuelle : "
				+ ihm.getJtf_nom().getText(), "Regis", ihm.getJtf_nom()
				.getText());
		testerFrame.actionClick(ihm.getBut_add());

		// On va maintenant supprimer le premier et vérifier qu'il a bien été
		// supprimé dans le tableau des Ficches

		// On clique sur la première fiche créée
		testerFrame.actionClick(ihm.getTableau(), 0, 0);

		// Puis on clique sur le bouton "supprimer"
		testerFrame.actionClick(ihm.getBut_sup());

		// On recherche le tableau de fiche et on verifie qu'il n'y en a qu'une
		assertTrue("Il ne devrait y avoir qu'une seule Fiche or il y en a "
				+ ihm.getFiches().size(), ihm.getFiches().size() == 1);

		// On vérifie que la bonne Fiche a été supprimé
		// On vérifie qu'il s'agis de la fiche de Regis
		assertEquals("Le nom de la fiche devrait être Regis mais c'est "
				+ ihm.getFiches().get(0).getM_nom(), "Regis", ihm.getFiches()
				.get(0).getM_nom());

	}

	private int count = 0;

	public IHMTest(String name) {
		super(name);
	}

	public static void main(String[] args) {
		TestHelper.runTests(args, IHMTest.class);
	}
}
