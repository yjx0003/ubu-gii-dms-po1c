package model;

public class EstadoTareaEnValidacion extends EstadoTarea {
	
	private static EstadoTareaEnValidacion instancia = null;
	
	private EstadoTarea siguienteEstado = EstadoTareaCompletada.getInstancia(); 
	
	
	private EstadoTareaEnValidacion() {
		super();
		this.nombreEstado = "En Validacion"; 
	}
	
	public static EstadoTareaEnValidacion getInstancia(){
		if(instancia == null){
			instancia = new EstadoTareaEnValidacion(); 
		}
		return instancia; 
	}

	@Override
	public void actualizarEstado(Tarea t){
		t.cambiarEstado(siguienteEstado);
	}
	
	public void setSiguienteEstado(EstadoTarea siguienteEstado){
		this.siguienteEstado = siguienteEstado; 
	}

}
