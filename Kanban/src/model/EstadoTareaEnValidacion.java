package model;

public class EstadoTareaEnValidacion extends EstadoTarea {
	
	
	public EstadoTareaEnValidacion() {
		super();
		this.nombreEstado = "En Validacion"; 
	}

	@Override
	public void actualizarEstado(Tarea t){
		t.cambiarEstado(new EstadoTareaCompletada());
	}

}
