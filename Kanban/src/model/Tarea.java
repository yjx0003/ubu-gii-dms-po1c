package model;

public class Tarea {
	private static int contadorIds = 0; 
	
	private int idTarea; 
	private String titulo;
	private String descripcion;
	private int coste;
	private int beneficio;
	private Requisito requisito;
	private MiembroDeEquipo miembro;

	private EstadoTarea estado; 

	public Tarea(String titulo, String descripcion, int coste, int beneficio, Requisito requisito,MiembroDeEquipo miembro) {
		this.idTarea= contadorIds++; 
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.coste = coste;
		this.beneficio = beneficio;
		this.requisito = requisito;
		this.miembro = miembro;
		this.estado = new EstadoTareaPendiente(); 
		
	}

	public void asignarMiembro(MiembroDeEquipo miembro) {
		this.miembro = miembro;
	}
	
	public int getIdTarea(){
		return this.idTarea; 
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getCoste() {
		return coste;
	}

	public int getBeneficio() {
		return beneficio;
	}

	public Requisito getRequisito() {
		return requisito;
	}

	public MiembroDeEquipo getMiembro() {
		return miembro;
	}
	
	public void actualizarEstado(){
		estado.actualizarEstado(this); 
	}
	
	public void cambiarEstado(EstadoTarea e){
		this.estado =e; 
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Tarea)) {
			return false;
		}
		Tarea otraTarea = (Tarea) obj;
		return this.idTarea == otraTarea.idTarea; 
//		return this.beneficio == otraTarea.beneficio && this.coste == otraTarea.coste
//				&& this.descripcion == otraTarea.descripcion && this.titulo == otraTarea.titulo
//				&& this.miembro.equals(otraTarea.miembro) && this.requisito.equals(otraTarea.requisito);

	}

}
