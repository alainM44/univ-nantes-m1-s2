package main;

import genTache.AbstractTache;
import genTache.ITache;
import genTache.mainGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import sstr.TasksManager;
import sstr.Writer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class main
{

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		
		TasksManager tm = new TasksManager(mainGenerator.FileGenerator());
		//tm.RM();
	}
}
