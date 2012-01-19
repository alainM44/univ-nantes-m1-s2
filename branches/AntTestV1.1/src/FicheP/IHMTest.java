package FicheP;

import java.awt.Component;

import javax.swing.CellRendererPane;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ViewportLayout;

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

	protected void setUp() {
		ihm = new Ihm_gestion_graph();
		testerFrame = FrameTester.getTester(Ihm_gestion_graph.class);
		ihm.setVisible(true);
	}

	protected void tearDown() {
		testerFrame.keyRelease(17);
	}

	private String gotClick;

	public void testAjoutFiche() throws ComponentNotFoundException,
			MultipleComponentsFoundException {
		// On ajoute la fiche <nom="Dupond", Adresse=(12, "rue de la cote",
		// 44000, "France"), mail ="dupond@yahoo.fr",tel 1478964512>

		testerFrame.actionClick(ihm.getJtf_nom());
		testerFrame.keyString("Dupond");
		assertEquals("Valeur attendu : Dupont Valeur actuelle : "
				+ ihm.getJtf_nom().getText(), "Dupond", ihm.getJtf_nom()
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
		Fiche f = ihm.getFiches().get(0);
		assertNotNull(f);
		assertEquals("Valeur attendu : Dupont Valeur actuelle : "
				+ f.getM_nom(), "Dupond", f.getM_nom());
		assertEquals("Valeur attendu : 1478964512 Valeur actuelle : "
				+ f.getM_tel(), 1478964512, f.getM_tel());

	}

	public void testEgaliteFiche() {
		// On ajoute la fiche <nom="Dupond", Adresse=(12, "rue de la cote",
		// 44000, "France"), mail ="dupond@yahoo.fr",tel 1478964512>
		testerFrame.actionClick(ihm.getJtf_nom());
		testerFrame.keyString("Dupond");
		assertEquals("Valeur attendu : Dupont Valeur actuelle : "
				+ ihm.getJtf_nom().getText(), "Dupond", ihm.getJtf_nom()
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
		
		//On maintient Ctrl
		testerFrame.keyPress(17);

		//On clique sur les deux premières fiches créées
		testerFrame.actionClick(ihm.getTableau(), 0, 0);
		testerFrame.actionClick(ihm.getTableau(), 0, 20);

		//On relache Ctrl
		testerFrame.keyRelease(17);
		testerFrame.actionClick(ihm.getBut_comp());
		assertEquals("EGALES " + ihm.getLab().getText(),"EGALES", ihm.getLab().getText());
	}
	
	public void testDifferenceFiche() {
		// On ajoute la fiche <nom="Dupond", Adresse=(12, "rue de la cote",
		// 44000, "France"), mail ="dupond@yahoo.fr",tel 1478964512>
		testerFrame.actionClick(ihm.getJtf_nom());
		testerFrame.keyString("Dupond");
		assertEquals("Valeur attendu : Dupont Valeur actuelle : "
				+ ihm.getJtf_nom().getText(), "Dupond", ihm.getJtf_nom()
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
		
		//On change le nom
		testerFrame.actionClick(ihm.getJtf_nom());
		testerFrame.keyString("Regis");
		assertEquals("Valeur attendu : Regis Valeur actuelle : "
				+ ihm.getJtf_nom().getText(), "Regis", ihm.getJtf_nom()
				.getText());
		testerFrame.actionClick(ihm.getBut_add());
		// Puis on les compare
		
		//On maintient Ctrl
		testerFrame.keyPress(17);

		//On clique sur les deux premières fiches créées
		testerFrame.actionClick(ihm.getTableau(), 0, 0);
		testerFrame.actionClick(ihm.getTableau(), 0, 20);

		//On relache Ctrl
		testerFrame.keyRelease(17);
		testerFrame.actionClick(ihm.getBut_comp());
		assertEquals(" PAS EGALES " + ihm.getLab().getText()," PAS EGALES", ihm.getLab().getText());
	}

	private int count = 0;

	public IHMTest(String name) {
		super(name);
	}

	public static void main(String[] args) {
		TestHelper.runTests(args, IHMTest.class);
	}
}
