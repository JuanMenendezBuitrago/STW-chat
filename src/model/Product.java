package model;

import javax.faces.bean.ManagedBean;

/**
 * Session Bean implementation class Product
 */
@ManagedBean
public class Product {
	private String category;
	private String model;
	
    /**
     * Default constructor. 
     */
    public Product(String category, String model) {
        this.category = category;
        this.model = model;
    }

	public String getCategory() {
		return category;
	}

	public String getModel() {
		return model;
	}
    
    public String toString() {
    	return model;
    }

}
