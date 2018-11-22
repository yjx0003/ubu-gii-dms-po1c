package model;

public class Requisito {

	private int idRequisito;
	private String titulo;
	private String descripcion;

	public Requisito(int idRequisito, String titulo, String descripcion) {
		this.idRequisito = idRequisito;
		this.titulo = titulo;
		this.descripcion = descripcion;
	}

	public int getIdRequisito() {
		return idRequisito;
	}

	public void setIdRequisito(int idRequisito) {
		this.idRequisito = idRequisito;
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
		return this.idRequisito == r.idRequisito && this.descripcion.equals(r.descripcion)
				&& this.titulo.equals(r.titulo);

	}

}
