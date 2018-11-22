package model;

public class Requisito {

	private String titulo;
	private String descripcion;

	public Requisito(String titulo, String descripcion) {
		this.titulo = titulo;
		this.descripcion = descripcion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

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
