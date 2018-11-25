package model;
/**
 * Estados posibles de una tarea
 * @author Alicia Olivares Gil
 * @author Yi Peng Ji
 *
 */
public abstract class EstadoTarea {
	
	protected String nombreEstado; 
	
	public abstract void actualizarEstado(Tarea t);
	
	@Override
	public String toString(){
		return nombreEstado; 
	}
}
