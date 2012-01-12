package FicheP;
public class Fiche {
 
	String m_nom;
	String [] m_prenoms;
	Adresse m_adresseP;
	String m_mails;
	int m_tel;

//passer par le seter pour les prenoms
	public Fiche(String mNom, Adresse mAdresseP,String mails,int m_tel) {
		super();
		String[] mPrenoms = new String[3];
		m_mails= mails;
		m_nom = mNom;
		m_prenoms = mPrenoms;
		m_adresseP = mAdresseP;
		this.m_tel = m_tel;
	}


	public void setM_prenoms(String prenom, int place) {
		m_prenoms[place] = prenom;
	}


	public  boolean compare(Fiche f){
		boolean result;
		result=(m_nom.equals(f.m_nom));
		for(int i=0;i<m_prenoms.length;i++)
		{
			for(int j=0;j<m_prenoms.length;j++)
			{
				if ((m_prenoms[i].equals(f.m_prenoms[i])==false))
					return false;
			}	
		}
		result=(m_mails.equals(f.m_mails));
		result=(m_tel==(f.m_tel));
		return result;

	}
	
	
	
}
