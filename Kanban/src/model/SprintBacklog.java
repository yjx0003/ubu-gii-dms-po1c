package model;

import java.util.Date;

public class SprintBacklog extends Backlog {
	private int idSprint; 
	private String descripcion; 
	private Date date; 
	
	public SprintBacklog(Date date, int idSprint, String descripcion){
		this.date = date; 
		this.idSprint = idSprint; 
		this.descripcion = descripcion; 
	}
}