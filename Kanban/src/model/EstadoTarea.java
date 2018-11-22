package model;

public abstract class EstadoTarea {
	
	protected String nombreEstado; 
	
	public abstract void actualizarEstado(Tarea t);
	
	@Override
	public String toString(){
		return nombreEstado; 
	}
}
