package model;

public class MiembroDeEquipo {
	private static int contadorIds; 
	
	private int idMiembro; 
	private String nombre; 
	


	public MiembroDeEquipo(String nombre){
		this.idMiembro = contadorIds++; 
		this.nombre = nombre; 
	}
	
	public MiembroDeEquipo(int id, String nombre){
		this.nombre = nombre; 
		this.idMiembro = id; 
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getIdMiembro(){
		return this.idMiembro; 
	}
	public static void setContadorIds(int contador){
		contadorIds = contador; 
	}
	public static int getContadorIds() {
		return contadorIds;
	}
	
	@Override
	public boolean equals(Object obj){
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MiembroDeEquipo)) {
			return false;
		}
		MiembroDeEquipo m = (MiembroDeEquipo) obj;
		return this.nombre.equals(m.nombre); 
	}
	
	@Override
	public String toString(){
		return this.nombre; 
	}
}
