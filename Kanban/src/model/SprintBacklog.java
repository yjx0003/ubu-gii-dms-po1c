package model;

import java.util.Calendar;
import java.util.GregorianCalendar; 

public class SprintBacklog extends Backlog {
	private String descripcion; 
	private Calendar fechaInicio; 
	
	
	public SprintBacklog(Calendar fecha, String descripcion){
		this.fechaInicio = fecha; 
		this.descripcion = descripcion; 
	}

	public void reiniciarSprintBacklog(int dia, int mes, int ano, String descripcion) {
		
		this.fechaInicio = new GregorianCalendar(ano, mes, dia); 
		this.descripcion = descripcion; 
	}

	public String getDescripcion() {
		return this.descripcion; 
	}
}