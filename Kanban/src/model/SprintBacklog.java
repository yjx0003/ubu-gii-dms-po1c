package model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map; 

public class SprintBacklog extends Backlog {
	private String descripcion; 
	private Calendar fechaInicio; 
	
	private static SprintBacklog instancia = null; 
	
	
	private SprintBacklog(){
	}
	
	public static SprintBacklog getInstancia(){
		if(instancia == null){
			instancia = new SprintBacklog(); 
		}
		return instancia; 
	}
	
	public void iniciar(String descripcion, Calendar fechaInicio){
		this.descripcion= descripcion; 
		this.fechaInicio = fechaInicio; 
	}

	public void reiniciarSprintBacklog(int dia, int mes, int ano, String descripcion) {
		
		this.fechaInicio = new GregorianCalendar(ano, mes, dia); 
		this.descripcion = descripcion; 
		this.tareas = new HashMap<Integer,Tarea>(); 
	}

	public String getDescripcion() {
		return this.descripcion; 
	}
	public Calendar getFechaInicio() {
		return fechaInicio;
	}

}