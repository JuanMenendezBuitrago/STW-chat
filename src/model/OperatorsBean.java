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
		operators.add(new Operator(id, "juan", "juan123", "Juan", "Bar"));
		id = UUID.randomUUID();
		operators.add(new Operator(id, "nico", "nico123", "Nico", "Foo"));
		
		// System.out.println(operators);
	}
	
	public boolean validateOperator(String login,String password) {
		System.out.println("validating " + login + ":" + password);//TODO:delete
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
		System.out.println("validating " + login );//TODO:delete
		System.out.println("operators " + operators );//TODO:delete
		
		for(Operator operator : operators) {
			if(operator.getLogin().equals(login))
				return operator;
		}
		return null;
	}
	
	public Operator getOperatorById(UUID id) {
		return null;
	}

}
