package model;
/**
 * 
 * @author Alicia Olivares Gil
 * @author Yi Peng Ji
 *
 */
public class Defecto extends Requisito {
	
	private int tarea; 
	/**
	 * Constructor.
	 * @param titulo titulo
	 * @param descripcion descripcion
	 * @param tarea tarea
	 */
	public Defecto(String titulo, String descripcion, int tarea) {
		super(titulo, descripcion);
		this.tarea = tarea; 
	}
	

	/**
	 * Devuelve el id de la tarea asociada al defecto
	 * @return id de la tarea
	 */
	public int getTarea(){
		return this.tarea; 
	}
	
	@Override
	public String toString(){
		return "Defecto encontrado en la tarea: "+tarea; 
	}
}
