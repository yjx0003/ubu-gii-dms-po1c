package model;

public class EstadoTareaEnProceso extends EstadoTarea {
	
	
	public EstadoTareaEnProceso() {
		super();
		this.nombreEstado="En Proceso"; 
	}

	@Override
	public void actualizarEstado(Tarea t){
		t.cambiarEstado(new EstadoTareaEnValidacion());
	}

}
