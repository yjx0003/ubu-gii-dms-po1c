package model;

import java.util.HashMap;

import java.util.Map;
/**
 * Backlog donde se van a guardar las tareas.
 * @author Alicia Olivares Gil
 * @author Yi Peng Ji
 *
 */
public class Backlog {
	protected Map<Integer,Tarea> tareas;
	
	public Backlog(){
		tareas = new HashMap<Integer,Tarea>(); 
	}
	/**
	 * Añade la  tarea al backlog
	 * @param t tarea que se quiere añadir
	 * @return true si se ha podido añadir o false en caso contrario
	 */
	public boolean anadirTarea(Tarea t){
		if(!tareas.containsKey(t.getIdTarea())){
			tareas.put(t.getIdTarea(), t);
			return true; 
		}
		return false; 
	}
	/**
	 * Devuelve el mapa de todas las tareas del backlog
	 * @return tareas 
	 */
	public Map<Integer,Tarea> getTareas(){
		return this.tareas; 
	}

}
