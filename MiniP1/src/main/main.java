package main;

import genTache.AbstractTache;
import genTache.ITache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import sstr.Writer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class main
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
//		AbstractTache[] tab;
//		try
//		{
//			// Instanciation de la classe XStream
//			XStream xstream = new XStream(new DomDriver());
//
//			// Redirection du fichier c:/temp/article.xml vers un flux
//			// d'entrée fichier
//			FileInputStream fis = new FileInputStream(new File("/comptes/E11A932Q/workspace/MiniP1/taches.xml"));
//
//			try
//			{
//				// Désérialisation du fichier c:/temp/article.xml vers un nouvel
//				// objet article
//				AbstractTache[] nouveauTab = (AbstractTache[]) xstream.fromXML(fis);
//
//				// Affichage sur la console du contenu de l'attribut synopsis
//				for(int i =0; i<nouveauTab.length;i++)
//					System.out.println(nouveauTab[i].getId());
//
//			} finally
//			{
//				// On s'assure de fermer le flux quoi qu'il arrive
//				fis.close();
//			}
//
//		} catch (FileNotFoundException e)
//		{
//			e.printStackTrace();
//		} catch (IOException ioe)
//		{
//			ioe.printStackTrace();
//		}
//	}
	Writer mywriter = new Writer("demo.ktr");
	mywriter.addEvent(1, "READY-B",1,"");
	mywriter.addEvent(9,"ARROWDOWN", 2, "e2", "orange");
	mywriter.addEvent(9, "READY-B",2,"");
	mywriter.addEvent(10, "READY-B",2,"");
	mywriter.addEvent(10, "READY-B",2,"");
	mywriter.addEvent(10, "READY-B",2,"");
	mywriter.addEvent(10, "READY-B",2,"");
	mywriter.generateFile();
}
}
