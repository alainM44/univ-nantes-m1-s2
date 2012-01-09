import com.thoughtworks.xstream.XStream;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XStream xstream = new XStream();
		
		Fiche[] carnet_adresses = new Fiche [3];
		Fiche f1 = new Fiche("Dupond", new Adresse(12, "rue de la cote", 44000, "France"), "dupond@yahoo.fr",1478964512);
		f1.setM_prenoms("Jacque", 0);
		f1.setM_prenoms("Jean", 1);
		f1.setM_prenoms("Joseph", 2);

		Fiche f2= new Fiche("Dupont", new Adresse(12, "rue de la cote", 44000, "France"), "dupond@yahoo.fr",1478964512);
		f2.setM_prenoms("Jacque", 0);
		f2.setM_prenoms("Jean", 1);
		f2.setM_prenoms("Joseph", 2);
		Fiche f3= new Fiche("Durand", new Adresse(8, "rue de la cote", 44000, "France"), "dupond@yahoo.fr",1478964512);
		f3.setM_prenoms("George", 0);
		f3.setM_prenoms("Ramir", 1);
	
	//	xstream.alias("Fiche", Fiche.class);
		carnet_adresses[0]=f1;
		carnet_adresses[1]=f2;
		carnet_adresses[2]=f3;
	//	String xml = xstream.toXML(f1);
		  System.out.println(xstream.toXML(carnet_adresses));


//		System.out.println(f1.compare(f2));
//		System.out.println(f1.compare(f3));
		


	}

}
