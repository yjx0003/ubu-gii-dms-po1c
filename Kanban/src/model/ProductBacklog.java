package model;

public class ProductBacklog extends Backlog {
	
	private static ProductBacklog instance = null;
	
	
	private ProductBacklog(){
		
		super(); 
	}
	/**
	 * Devuelve la unica instancia de product backlog
	 * @return la instancia de product backlog
	 */
	public static ProductBacklog getInstancia(){
		if(instance == null){
			instance = new ProductBacklog(); 
		}
		return instance; 
	}
	
}
