
public class User implements java.io.Serializable{
private String username;
private String password;


	public User(String name, String password) {
		this.username = name;
		this.password = password;
	}

public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}


}
