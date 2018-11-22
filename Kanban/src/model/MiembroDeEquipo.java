package model;

public class MiembroDeEquipo {
	private String nombre; 
	


	public MiembroDeEquipo(String nombre){
		this.nombre = nombre; 
	}
	
	public String getNombre() {
		return nombre;
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
}
