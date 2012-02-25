package main;

import genTache.AbstractTache;
import genTache.ITache;
import genTache.mainGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import sstr.Algorithms;
import sstr.TasksManager;
import sstr.Writer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class main
{

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		
		TasksManager tm = new TasksManager("cours_RMBG.xml"); // TEST RM_BG
		Algorithms al = new Algorithms(tm);
		al.RmBg();
		//TasksManager tm = new TasksManager(mainGenerator.FileGenerator());
		//tm.RM();
	}
}
