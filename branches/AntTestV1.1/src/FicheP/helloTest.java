package FicheP;

import java.awt.Component;
import java.awt.event.*;

import javax.swing.JPanel;
import javax.swing.JTextField;

import junit.*;
import junit.extensions.abbot.*;
import abbot.finder.ComponentFinder;
import abbot.finder.ComponentNotFoundException;
import abbot.finder.MultipleComponentsFoundException;
import abbot.finder.matchers.ClassMatcher;
import abbot.tester.ComponentTester;
import abbot.tester.FrameTester;
import abbot.tester.JTextFieldTester;
import junit.extensions.abbot.ComponentTestFixture;

/**
 * Source code for Tutorial 1. Simple unit tests for example.ArrowButton. Also
 * demonstrates the use of ComponentTestFixture.
 */

public class helloTest extends ComponentTestFixture
{

	private ComponentTester testerFrame;
	private ComponentTester testerField;
	private Ihm_gestion_graph ihm;

	protected void setUp()
	{
		ihm = new Ihm_gestion_graph();
		testerFrame = FrameTester.getTester(Ihm_gestion_graph.class);
		ihm.setVisible(true);
	}

	private String gotClick;

	public void testAjoutFiche() throws ComponentNotFoundException,
			MultipleComponentsFoundException
	{
		// On clique sur chaque champs de texte puis on rentre la valeur
		testerFrame.actionClick(ihm.getJtf_nom());
		testerFrame.keyString("Dupond");
		assertEquals("Valeur attendu : Dupont Valeur actuelle : "
				+ ihm.getJtf_nom().getText(), "Dupond", ihm.getJtf_nom()
				.getText());
		testerFrame.actionClick(ihm.getJtf_num());
		testerFrame.keyString("12");
		assertEquals("Valeur attendu : 12 Valeur actuelle : "
				+ ihm.getJtf_num().getText(), "12", ihm.getJtf_num()
				.getText());
		Fiche f1 = new Fiche("Dupond", new Adresse(12, "rue de la cote", 44000,
				"France"), "dupond@yahoo.fr", 1478964512);
	}

	private int count = 0;

	public helloTest(String name)
	{
		super(name);
	}

	public static void main(String[] args)
	{
		TestHelper.runTests(args, helloTest.class);
	}
}
