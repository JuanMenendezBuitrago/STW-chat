package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import model.CatalogBean;
import model.Conversation;
import model.ConversationsManager;
import model.Operator;
import model.Product;
import view.UserLoginBean;

@ManagedBean
@SessionScoped
public class UserLoginController {

	@ManagedProperty(value="#{userLoginBean}")
	private UserLoginBean userLoginBean;
	
	@EJB
	private CatalogBean catalog;
	
	@EJB
	protected ConversationsManager conversationsManager;
	
	private String defaultCategory = "cat 2";
	
	private Set<String> categories;	

	private Conversation conversation;
	
	public UserLoginController() {	
	}
	
	@PostConstruct
	private void init() {
		userLoginBean.setSelectedCategory(defaultCategory);
		categories = catalog.getCategories();
	}

	public String getDefaultCategory() {
		return defaultCategory;
	}

	public Set<String> getCategories() {
		return categories;
	}

	public void setUserLoginBean(UserLoginBean userLoginBean) {
		this.userLoginBean = userLoginBean;
	}

	public List<Product> getProducts() {
		return catalog.getCategoryProducts(userLoginBean.getSelectedCategory());
	}
	
	public ArrayList<Product> categoryChanged() {
		return (ArrayList<Product>)getProducts();
	}
	
	public boolean isOperatorAvailable(){
		Operator op = null;
		op = conversationsManager.getAvailableOperator();
		return op != null;
	}
	
	public String createConversation() {
		
		UUID userId = UserLoginController.generateUserId();
		userLoginBean.setUserId(userId);
		
		String category = userLoginBean.getSelectedCategory();
		String product = userLoginBean.getSelectedProduct();
		String subject = userLoginBean.getSubject();
		String userName = userLoginBean.getName();
		
		try{
			conversation = conversationsManager.createConversation(userId, category, product, subject, userName);
			userLoginBean.setLogged(true);
			return "userChat";
		}
		catch (Exception e){
			return "userLogin";
		}
	}
	
	public Operator getOperator() {
		return conversation.getOperator();
	}
	
	private static UUID generateUserId() {
		return UUID.randomUUID();
	}

}
