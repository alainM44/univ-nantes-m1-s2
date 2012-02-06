package jonathan;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author E089044M
 */
public class Personne implements Ipersonne  {
    private String nom;
    private String prenom;
    private int age;
    
    Personne(){
        
    }
    Personne(String unNom, String unPrenom, int unAge){
        nom=unNom;
        prenom=unPrenom;
        age = unAge;
    }
    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    @NotSelfArgument
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void uneMethode(Object a, Object b) {
        System.out.println("Mason");
    }
    
    
}
