package model;

import javax.ejb.EJB;
import javax.ejb.Stateful;


@Stateful
public class OperatorManager {

	
	@EJB
	private DataBase data;
	
    public boolean isARegisteredUser(String userName, String passwd)
    {
    	boolean logged;
    	String storedPassword;
    	
    	storedPassword = this.data.getAuthorizedUsers().get(userName);
    	logged = ((storedPassword != null) && (storedPassword.equals(passwd)));
    	
    	return logged;
    }
}
