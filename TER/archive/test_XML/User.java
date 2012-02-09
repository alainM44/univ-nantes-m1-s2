package xml_tests;

public class User {

    private String login;
    private String password;
	
    public User() {
	    this("anonymous", "");
    }
	
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
	
    public String getLogin() {
        return login;
    }
	
    public void setLogin(String login) {
        this.login = login;
    }
	
    public String getPassword() {
        return password;
    }
	
    public void setPassword(String password) {
        this.password = password;
    }
	
    public String toString() {
        return login;
    }
    
  
    
    public static void main(String[] args) {
        try {
            User user = new User("admin", "azerty");
            XMLTools.encodeToFile(user, "user.xml");
            System.out.println(user);
            user = new User("newAdmin", "123456"); 
            System.out.println(user);
            user = (User) XMLTools.decodeFromFile("user.xml");
            System.out.println(user);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    
}
