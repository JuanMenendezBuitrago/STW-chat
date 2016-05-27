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
	
	public String createConversation() {
		
		System.out.println("UserLoginController::createConversation()"); //TODO:delete
		
		UUID userId = UserLoginController.generateUserId();
		userLoginBean.setUserId(userId);
		
		String category = userLoginBean.getSelectedCategory();
		String product = userLoginBean.getSelectedProduct();
		String subject = userLoginBean.getSubject();
		String userName = userLoginBean.getName();
		
		try{
			Conversation conversation = conversationsManager.createConversation(userId, category, product, subject, userName);
			System.out.println(conversation);

			System.out.println("-----"); //TODO:delete
			return "userChat";
		}
		catch (Exception e){
			return "noOperatorFound";
		}
	}
	
	private static UUID generateUserId() {
		return UUID.randomUUID();
	}

}
