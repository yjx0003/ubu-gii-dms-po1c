package model;

import java.util.Calendar;
import java.util.GregorianCalendar; 

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
	}

	public String getDescripcion() {
		return this.descripcion; 
	}
}