package test;
import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

import acquisition.Acquisition;

public class TestAcquisition
{

	Acquisition a;
	@Before
	public void setUp() throws Exception
	{
		a = new Acquisition();

		a.setFile("/comptes/E074862X/workspace/Acquisition/darkcity.mov");
	}
	
	@Test
	public void simpleNext()
	{
		a.start();
		BufferedImage b = a.next();
		assertNotNull("L'image obtenu est null", b);
		b = a.next();
		assertNotNull("L'image obtenu est null", b);
	}
	
	@Test
	public void frequencySettedNext()
	{
		a.setFrequence(0.5);
		a.start();
		BufferedImage b = a.next();
		assertNotNull("L'image obtenu est null", b);
		b = a.next();
		assertNotNull("L'image obtenu est null", b);
	}

}
