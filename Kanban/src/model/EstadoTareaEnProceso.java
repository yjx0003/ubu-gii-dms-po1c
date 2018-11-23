package model;

public class EstadoTareaEnProceso extends EstadoTarea {
	
	private static EstadoTareaEnProceso instancia = null; 
	
	
	private EstadoTareaEnProceso() {
		super();
		this.nombreEstado="En Proceso"; 
	}
	
	public static EstadoTareaEnProceso getInstancia(){
		if(instancia == null){
			instancia = new EstadoTareaEnProceso(); 
		}
		return instancia;
	}

	@Override
	public void actualizarEstado(Tarea t){
		t.cambiarEstado(EstadoTareaEnValidacion.getInstancia());
	}

}
