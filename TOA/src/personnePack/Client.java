package personnePack;

public class Client implements IPersonne
{
	private String nom;
	private String prenom;
	private Integer age;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String presentation() 
	{
		return "Presentation : \nNom : " + nom + "\nPrenom : " + prenom + "\nAge : " + age;
	}
	
}