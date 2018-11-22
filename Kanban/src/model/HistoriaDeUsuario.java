package model;

public class HistoriaDeUsuario extends Requisito {
	
	
	private String actor; 
	
	public HistoriaDeUsuario(String titulo, String descripcion, String actor) {
		super(titulo, descripcion);
		this.actor = actor; 
	}
	
	public String getActor(){
		return this.actor; 
	}
	
	@Override
	public String toString(){
		return "Actor: "+this.actor; 
	}

}
