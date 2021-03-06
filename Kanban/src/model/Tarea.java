package model;
/**
 * Clase tarea
 * @author Alicia Olivares Gil
 * @author Yi Peng Ji
 *
 */
public class Tarea {
	private static int contadorIds; 
	
	private int idTarea; 
	private int coste;
	private int beneficio;
	private Requisito requisito;
	private MiembroDeEquipo miembro;

	private EstadoTarea estado; 
	/**
	 * Constructor de tarea.
	 * @param coste coste
	 * @param beneficio beneficio
	 * @param requisito requisito
	 * @param miembro miembro
	 */
	public Tarea(int coste, int beneficio, Requisito requisito,MiembroDeEquipo miembro) {
		this.idTarea= contadorIds++; 
		this.coste = coste;
		this.beneficio = beneficio;
		this.requisito = requisito;
		this.miembro = miembro;
		this.estado = EstadoTareaPendiente.getInstancia(); 
		
	}
	/**
	 * Constructor de tarea.
	 * @param idTarea idTarea
	 * @param coste coste
	 * @param beneficio beneficio
	 * @param requisito requisito
	 * @param miembro miembro
	 */
	public Tarea(int idTarea, int coste, int beneficio, Requisito requisito,MiembroDeEquipo miembro) {
		this.idTarea = idTarea; 
		this.coste = coste;
		this.beneficio = beneficio;
		this.requisito = requisito;
		this.miembro = miembro;
		this.estado = EstadoTareaPendiente.getInstancia(); 
		
	}

	public void asignarMiembro(MiembroDeEquipo miembro) {
		this.miembro = miembro;
	}
	
	public int getIdTarea(){
		return this.idTarea; 
	}

	public String getTitulo() {
		return this.requisito.getTitulo();
	}

	public String getDescripcion() {
		return this.requisito.getDescripcion();
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
	
	public EstadoTarea getEstado() {
		return estado;
	}
	public static int getContadorIds() {
	
		return contadorIds;
	}
	public void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}

	public void setBeneficio(int beneficio) {
		this.beneficio = beneficio;
	}

	public void setRequisito(Requisito requisito) {
		this.requisito = requisito;
	}

	public void setMiembro(MiembroDeEquipo miembro) {
		this.miembro = miembro;
	}

	public static void setContadorIds(int contador){
		contadorIds = contador; 
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
	}

	public void reiniciarEstado() {
		estado = EstadoTareaPendiente.getInstancia(); 
	}



}
