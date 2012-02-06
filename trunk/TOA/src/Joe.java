import proxy.NotSelfArgument;


public class Joe implements Ipersonne
{
	private String nom;
	private String prenom;
	private Integer age;
	private Ipersonne Conjoint;

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
	public void setConjoint(Ipersonne p)
	{
		this.Conjoint = p;

	}

}
