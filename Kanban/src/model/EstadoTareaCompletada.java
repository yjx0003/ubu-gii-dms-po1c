package model;

public class EstadoTareaCompletada extends EstadoTarea{
	
	private static EstadoTareaCompletada instancia = null; 
	
	private EstadoTareaCompletada() {
		super();
		this.nombreEstado = "Completada"; 
	}
	
	public static EstadoTareaCompletada getInstancia(){
		if(instancia == null){
			instancia = new EstadoTareaCompletada(); 
		}
		return instancia;
	}

	@Override
	public void actualizarEstado(Tarea t){

	}

}
