package model;
/**
 * Clase de los miembros de equipo.
 * @author Alicia Olivares Gil
 * @author Yi Peng Ji
 *
 */
public class MiembroDeEquipo {
	private static int contadorIds; 
	
	private int idMiembro; 
	private String nombre; 
	

	/**
	 * Constructor de miembro a partir de solo el nombre, el id se asigna automaticamente.
	 * @param nombre nombre del miembro
	 */
	public MiembroDeEquipo(String nombre){
		this.idMiembro = contadorIds++; 
		this.nombre = nombre; 
	}
	/**
	 * Constructor del miembro de equipo.
	 * @param id id del miembro
	 * @param nombre nombre del miembro
	 */
	public MiembroDeEquipo(int id, String nombre){
		this.nombre = nombre; 
		this.idMiembro = id; 
	}
	/**
	 * Devuelve el nombre del miembro.
	 * @return nombre del miembro.
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Devuelve la id del miembro.
	 * @return id id 
	 */
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
