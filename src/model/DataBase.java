package model;
import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class DataBase {


	private  Hashtable<String, String> authorizedUsers;

	public DataBase() {
  }

	@PostConstruct
	private void init(){
		this.authorizedUsers = new Hashtable<String, String>();
		this.authorizedUsers.put("nico", "12345");
		this.authorizedUsers.put("juan", "12345");
		this.authorizedUsers.put("mark", "12345");
		this.authorizedUsers.put("marta", "12345");
		this.authorizedUsers.put("lucia", "12345");
		this.authorizedUsers.put("lol", "12345");
	}

	public Hashtable<String, String> getAuthorizedUsers() {
		return authorizedUsers;
	}
}