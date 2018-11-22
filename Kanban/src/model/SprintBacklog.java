package model;

import java.util.Date;

public class SprintBacklog extends Backlog {
	private int idSprint; 
	private Date date; 
	
	public SprintBacklog(Date date, int idSprint){
		this.date = date; 
		this.idSprint = idSprint; 
	}
}