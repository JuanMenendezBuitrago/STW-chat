package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.faces.bean.ApplicationScoped;

@Singleton
@Startup
public class OperatorsBean {
	
	private List<Operator> operators;
	
	public OperatorsBean() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	private void init() {
		UUID id;
		operators  = new ArrayList<Operator>();
		
		id = UUID.randomUUID();
		operators.add(new Operator(id, "juan", "juan123", "Juan", "Atención al cliente", "Mi nombre es Juan,en qué puedo ayudarte?", "vlcsnap-2016-02-19-13h56m16s314.png"));
		
		id = UUID.randomUUID();
		operators.add(new Operator(id, "nico", "nico123", "Nico", "Atención al cliente", "Mi nombre es Nico, qué me cuentas?","zoidberg_blinking_futurama.gif"));


	}
	
	public boolean validateOperator(String login,String password) {
		Operator operator = getOperatorByLogin(login);
		if(operator != null && operator.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	
	public List<Operator> getOperatorList() {
		return operators;
	}
	
	public Operator getOperatorByLogin(String login) {
		
		for(Operator operator : operators) {
			if(operator.getLogin().equals(login))
				return operator;
		}
		return null;
	}
	
	public Operator getOperatorById(UUID id) {

		System.out.println("Operators stored: " + operators.size());// TODO:delete
		for(Operator operator : operators) {

			if(operator.getId().equals(id))
				return operator;
		}
		return null;	}

}
