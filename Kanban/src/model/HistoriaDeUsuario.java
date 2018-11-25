package model;

public class HistoriaDeUsuario extends Requisito {
	
	
	private String actor; 
	
	public HistoriaDeUsuario(String titulo, String descripcion, String actor) {
		super(titulo, descripcion);
		this.actor = actor; 
	}
	/**
	 * Devuelve el actor (Cliente,Programador etc) de la historia de usuario.
	 * @return actor
	 */
	public String getActor(){
		return this.actor; 
	}
	
	@Override
	public String toString(){
		return "Actor: "+this.actor; 
	}

}
