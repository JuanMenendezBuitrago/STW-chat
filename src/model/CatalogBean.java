package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Session Bean implementation class CatalogBean
 */

@Singleton
@Startup
public class CatalogBean {

	private Hashtable<String, List<Product>> catalog = new Hashtable<String, List<Product>>();
	private String[] categories;
    /**
     * Default constructor. 
     */
    public CatalogBean() {
    }
    
    @PostConstruct
    public void init() {
    	categories = new String[2];
    	
    	categories[0] = "cat 1";
    	ArrayList<Product> cat1Products = new ArrayList<Product>();
    	cat1Products.add(new Product(categories[0], "model 1-1"));
    	cat1Products.add(new Product(categories[0], "model 1-2"));
    	cat1Products.add(new Product(categories[0], "model 1-3"));
        catalog.put(categories[0], cat1Products );
        
        categories[1] = "cat 2";
        ArrayList<Product> cat2Products = new ArrayList<Product>();
    	cat2Products.add(new Product(categories[1], "model 2-1"));
    	cat2Products.add(new Product(categories[1], "model 2-2"));
    	cat2Products.add(new Product(categories[1], "model 2-3"));
        catalog.put(categories[1], cat2Products );
    }
    
    public Set<String> getCategories() {
    	
    	return new HashSet<String>(Arrays.asList(categories));
    	
    }
    
    public List<Product> getCategoryProducts(String category) {
    	List<Product> result = new ArrayList<Product>();
    	try{
    		result = catalog.get(category);
    	}catch (NullPointerException e) {
    		
    	}
    	return result;
    		
    }

}
