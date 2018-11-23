package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class PersistenciaAbstracta {
	
	protected Map<Integer,MiembroDeEquipo> miembros; 
	protected SprintBacklog sprintBacklog; 
	protected ProductBacklog productBacklog; 
	

	public PersistenciaAbstracta() {
		this.miembros=new HashMap<Integer, MiembroDeEquipo>(); 
	}
	
	
	
	public abstract void leerPersistencia(); 
	
	public abstract void commit(); 
	
	public Map<Integer,MiembroDeEquipo> getMiembros(){
		return this.miembros; 
	}
	
	public SprintBacklog getSprintBacklog(){
		return this.sprintBacklog; 
	}
	
	public ProductBacklog getProductBacklog(){
		return this.productBacklog; 
	}
	
	public void setMiembros(Map<Integer, MiembroDeEquipo> miembros) {
		this.miembros = miembros;
	}



	public void setSprintBacklog(SprintBacklog sprintBacklog) {
		this.sprintBacklog = sprintBacklog;
	}



	public void setProductBacklog(ProductBacklog productBacklog) {
		this.productBacklog = productBacklog;
	}



	public void anadirTarea(Tarea t){
		this.productBacklog.getTareas().put(t.getIdTarea(),t); 
	}

}
