package view;

import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Session Bean implementation class UserLoginBean
 */
@ManagedBean
@SessionScoped
public class UserLoginBean {

	private String name;
	private String subject;
	private String selectedCategory;
	private String selectedProduct;
	private UUID userId;
	
	/**
     * Default constructor. 
     */
    public UserLoginBean() {
        // TODO Auto-generated constructor stub
    }
	
    public String getName() {
		return name;
	}
    
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getSelectedCategory() {
		return selectedCategory;
	}
	
	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}
	
	public String getSelectedProduct() {
		return selectedProduct;
	}
	
	public void setSelectedProduct(String selectedProduct) {
		this.selectedProduct = selectedProduct;
	}
	
	public UUID getUserId() {
		return userId;
	}
	
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
}
