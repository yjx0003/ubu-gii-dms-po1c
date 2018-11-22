package model;

public class EstadoTareaPendiente extends EstadoTarea{
	
	
	
	public EstadoTareaPendiente() {
		super();
		this.nombreEstado = "Pendiente"; 
	}

	@Override
	public void actualizarEstado(Tarea t){
		t.cambiarEstado(new EstadoTareaEnProceso());
		
	}

}
