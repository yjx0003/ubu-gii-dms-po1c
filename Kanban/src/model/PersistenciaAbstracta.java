package model;

import java.util.List;

public abstract class PersistenciaAbstracta {
	
	protected List<MiembroDeEquipo> miembros; 
	protected List<SprintBacklog> sprints; 
	protected ProductBacklog productBacklog; 
	

	public PersistenciaAbstracta() {
	}
	
	
	
	public abstract void leerPersistencia(); 
	
	public abstract void mostrarPersistencia(); 
	

}
