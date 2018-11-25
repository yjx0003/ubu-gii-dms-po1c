package model;
/**
 * Clase requisito.
 * @author Alicia Olivares Gil
 * @author Yi Peng Ji
 *
 */
public class Requisito {

	private String titulo;
	private String descripcion;
	/**
	 * Constructor de requisto
	 * @param titulo titulo
	 * @param descripcion descripcion
	 */
	public Requisito(String titulo, String descripcion) {
		this.titulo = titulo;
		this.descripcion = descripcion;
	}
	/**
	 * Devuelve el titulo
	 * @return titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * Modifica el titulo
	 * @param titulo titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * Devuelve la descripcion
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * Modifica la descricpcion 
	 * @param descripcion descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Requisito)) {
			return false;
		}
		Requisito r = (Requisito) obj;
		return this.descripcion.equals(r.descripcion) && this.titulo.equals(r.titulo);

	}

}
