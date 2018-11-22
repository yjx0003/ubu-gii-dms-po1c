package model;

public class HistoriaDeUsuario extends Requisito {
	
	
	private String actor; 
	
	public HistoriaDeUsuario(int idRequisito, String titulo, String descripcion, String actor) {
		super(idRequisito, titulo, descripcion);
		this.actor = actor; 
	}
	
	public String getActor(){
		return this.actor; 
	}

}
