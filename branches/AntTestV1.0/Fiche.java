package FicheP;
public class Fiche {
 
	String m_nom;
	//String [] m_prenoms;
	Adresse m_adresseP;
	String m_mails;
	int m_tel;

//passer par le seter pour les prenoms
	public Fiche(String mNom, Adresse mAdresseP,String mails,int m_tel) {
		super();
		String[] mPrenoms = new String[3];
		m_mails= mails;
		m_nom = mNom;
	//	m_prenoms = mPrenoms;
		m_adresseP = mAdresseP;
		this.m_tel = m_tel;
	}


	public void setM_prenoms(String prenom, int place) {
	//	m_prenoms[place] = prenom;
	}


	public  boolean compare(Fiche f){
		boolean result;
		result=(m_nom.equals(f.m_nom))&&(m_mails.equals(f.m_mails))&&(m_tel==(f.m_tel));
//		for(int i=0;i<m_prenoms.length;i++)
//		{
//			for(int j=0;j<m_prenoms.length;j++)
//			{
//				if ((m_prenoms[i].equals(f.m_prenoms[i])==false))
//					return false;
//			}	
//		}
//		result=(m_mails.equals(f.m_mails));
//		result=(m_tel==(f.m_tel));
		return result;

	}


	public String getM_nom() {
		return m_nom;
	}


	public void setM_nom(String mNom) {
		m_nom = mNom;
	}


//	public String[] getM_prenoms() {
//		return m_prenoms;
//	}


//	public void setM_prenoms(String[] mPrenoms) {
//		m_prenoms = mPrenoms;
//	}


	public Adresse getM_adresseP() {
		return m_adresseP;
	}


	public void setM_adresseP(Adresse mAdresseP) {
		m_adresseP = mAdresseP;
	}


	public String getM_mails() {
		return m_mails;
	}


	public void setM_mails(String mMails) {
		m_mails = mMails;
	}


	public int getM_tel() {
		return m_tel;
	}


	public void setM_tel(int mTel) {
		m_tel = mTel;
	}
	
	
	
}
