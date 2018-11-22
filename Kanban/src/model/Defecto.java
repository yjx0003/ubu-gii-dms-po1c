package model;

public class Defecto extends Requisito {
	
	private int tarea; 

	public Defecto(String titulo, String descripcion, int tarea) {
		super(titulo, descripcion);
		this.tarea = tarea; 
	}
	

	
	public int getTarea(){
		return this.tarea; 
	}
	
	@Override
	public String toString(){
		return "Defecto encontrado en la tarea: "+tarea; 
	}
}
