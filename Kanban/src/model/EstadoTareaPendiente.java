package model;

public class EstadoTareaPendiente extends EstadoTarea{
	
	private static EstadoTareaPendiente instancia = null; 
	
	private EstadoTareaPendiente() {
		super();
		this.nombreEstado = "Pendiente"; 
	}
	
	public static EstadoTareaPendiente getInstancia(){
		if(instancia == null){
			instancia = new EstadoTareaPendiente(); 
		}
		return instancia; 
	}

	@Override
	public void actualizarEstado(Tarea t){
		t.cambiarEstado(EstadoTareaEnProceso.getInstancia());
		
	}

}
