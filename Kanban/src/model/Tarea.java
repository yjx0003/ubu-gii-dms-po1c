package model;

public class Tarea {
	private String titulo; 
	private String descripcion; 
	private int coste; 
	private int beneficio; 
	private Requisito requisito; 
	private MiembroDeEquipo miembro; 
	
	public Tarea(Requisito requisito){
		this.requisito = requisito; 
	}
	
	public void asignarMiembro(MiembroDeEquipo miembro){
		this.miembro = miembro; 
	}
}
