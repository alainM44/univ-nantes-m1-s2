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
import junit.extensions.abbot.ComponentTestFixture;

/**
 * Source code for Tutorial 1. Simple unit tests for example.ArrowButton. Also
 * demonstrates the use of ComponentTestFixture.
 */

public class helloTest extends ComponentTestFixture
{

	private ComponentTester tester;

	protected void setUp()
	{
		tester = FrameTester.getTester(hello.class);
	}

	private String gotClick;

	public void testClick() throws ComponentNotFoundException, MultipleComponentsFoundException
	{
		ActionListener al = new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				gotClick = ev.getActionCommand();
			}
		};

		Component nom = new JTextField();
		   nom = (JTextField) tester.getFinder().find(new ClassMatcher(JTextField.class));
		tester.actionClick(nom);

		hello left = new hello();
		// hello right = new hello(hello.RIGHT);
		// hello up = new hello(hello.UP);
		// hello down = new hello(hello.DOWN);

		// left.addActionListener(al);
		// right.addActionListener(al);
		// up.addActionListener(al);
		// down.addActionListener(al);

		JPanel pane = new JPanel();
		pane.add(left);

		// This method provided by ComponentTestFixture
		showFrame(pane);

		gotClick = null;
		tester.actionClick(pane);

		assertEquals("Action failed", "Ajout", gotClick);
		gotClick = null;
		// tester.actionClick(right);
		// assertEquals("Action failed", left.getBut().getText(), gotClick);
		// // gotClick = null;
		// tester.actionClick(up);
		// assertEquals("Action failed", hello.UP, gotClick);
		// gotClick = null;
		// tester.actionClick(down);
		// assertEquals("Action failed", hello.DOWN, gotClick);
	}

	private int count = 0;

	public void testRepeatedFire()
	{
		hello fen = new hello();
		ActionListener al = new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				++count;
			}
		};

		showFrame(fen);

		// Hold the button down for 5 seconds
		tester.mousePress(fen);
		tester.actionDelay(5000);
		tester.mouseRelease();
		assertTrue("Didn't get any repeated events", count > 1);
	}

	public helloTest(String name)
	{
		super(name);
	}

	public static void main(String[] args)
	{
		TestHelper.runTests(args, helloTest.class);
	}
}
