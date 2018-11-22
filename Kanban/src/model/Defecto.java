package model;

public class Defecto extends Requisito {
	
	private Tarea tarea = null; 

	public Defecto(int idRequisito, String titulo, String descripcion, Tarea tarea) {
		super(idRequisito, titulo, descripcion);
		this.tarea = tarea; 
	}
	
	public Defecto(int idRequisito, String titulo, String descripcion) {
		super(idRequisito, titulo, descripcion); 
	}
	
	public void setTarea(Tarea t){
		this.tarea = t; 
	}
	
	public Tarea getTarea(){
		return this.tarea; 
	}
}
