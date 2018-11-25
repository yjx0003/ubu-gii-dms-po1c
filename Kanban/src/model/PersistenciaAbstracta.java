package model;

import java.util.HashMap;

import java.util.Map;
/**
 * Clase abstracta donde se leeran y guardaran los datos de forma persistente.
 * @author Alicia Olivares Gil
 * @author Yi Peng Ji
 *
 */
public abstract class PersistenciaAbstracta {
	
	protected Map<Integer,MiembroDeEquipo> miembros; 
	protected SprintBacklog sprintBacklog; 
	protected ProductBacklog productBacklog; 
	
	
	public PersistenciaAbstracta() {
		this.miembros=new HashMap<Integer, MiembroDeEquipo>(); 
	}
	
	
	/**
	 * Lee los datos de persistencia.
	 */
	public abstract void leerPersistencia(); 
	/**
	 * Guarda todos los datos de foma persistente.
	 */
	public abstract void commit();
	/**
	 * Guarda solo los miembros de equipo.
	 */
	public abstract void commitMiembrosDeEquipo(); 
	/**
	 * Guarda solo los product backlog.
	 */
	public abstract void commitProductBacklog(); 
	/**
	 * Guarda solo los sprint backlog.
	 */
	public abstract void commitSprintBacklog(); 
	/**
	 * Devuelve todos los miembros de equipo.
	 * @return todos lso miembros de equipo
	 */
	public Map<Integer,MiembroDeEquipo> getMiembros(){
		return this.miembros; 
	}
	/**
	 * Devuelve todas las tareas del sprint backlog 
	 * @return todas las tareas del sprint backlog
	 */
	public SprintBacklog getSprintBacklog(){
		return this.sprintBacklog; 
	}
	/**
	 * Devuelve todas las tareas del product backlog
	 * @return todas las taraas del product backlog
	 */
	public ProductBacklog getProductBacklog(){
		return this.productBacklog; 
	}
	/**
	 * Asigna un nuevo mapa de miembros
	 * @param miembros nuevo mapa de miembros
	 */
	public void setMiembros(Map<Integer, MiembroDeEquipo> miembros) {
		this.miembros = miembros;
	}


	/**
	 * Asigna un nuevo mapa de sprint backlog
	 * @param sprintBacklog nuevo mapa de sprint backlog
	 */
	public void setSprintBacklog(SprintBacklog sprintBacklog) {
		this.sprintBacklog = sprintBacklog;
	}


	/**
	 * Asigna un nuevo mapa de product backlog
	 * @param productBacklog Backlog nuevo mapa de product backlog
	 */
	public void setProductBacklog(ProductBacklog productBacklog) {
		this.productBacklog = productBacklog;
	}


	/**
	 * Añade una nueva tarea al product backlog
	 * @param t nueva tarea a añadir
	 */
	public void anadirTarea(Tarea t){
		this.productBacklog.getTareas().put(t.getIdTarea(),t); 
	}

}
