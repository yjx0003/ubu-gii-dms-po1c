package controller;

public class EstadoControladorCrearNuevoSprint extends EstadoControlador{
	
	private static EstadoControladorCrearNuevoSprint instancia = null; 
	
	private EstadoControladorCrearNuevoSprint(){
		
	}
	
	public static EstadoControladorCrearNuevoSprint getInstancia(){
		if(instancia == null){
			instancia = new EstadoControladorCrearNuevoSprint(); 
		}
		return instancia; 
	}

	@Override
	public void actualizarEstado(Controlador c) {
		c.cambiarEstado(EstadoControladorSprintBacklog.getInstancia());
	}

	@Override
	public void mostrarMenu(Controlador c) {
		c.getVistaKanban().menuCrearNuevoSprint();
		
	}

}
