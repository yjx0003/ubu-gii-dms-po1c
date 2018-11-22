package model;

public class ProductBacklog extends Backlog {
	
	private static ProductBacklog instance = null; 
	
	private ProductBacklog(){
		super(); 
	}
	
	public static ProductBacklog getInstance(){
		if(instance == null){
			instance = new ProductBacklog(); 
		}
		return instance; 
	}
	
}
