package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Backlog {
	protected Map<Integer,Tarea> tareas;
	
	public Backlog(){
		tareas = new HashMap<Integer,Tarea>(); 
	}
	
	public boolean anadirTarea(Tarea t){
		if(!tareas.containsKey(t.getIdTarea())){
			tareas.put(t.getIdTarea(), t);
			return true; 
		}
		return false; 
	}
	
	public Map<Integer,Tarea> getTareas(){
		return this.tareas; 
	}

}
