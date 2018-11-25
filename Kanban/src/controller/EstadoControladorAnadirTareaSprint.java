package controller;

public class EstadoControladorAnadirTareaSprint extends EstadoControlador{

	private static EstadoControladorAnadirTareaSprint instancia = null; 
	
	private EstadoControladorAnadirTareaSprint(){
		
	}
	
	public static EstadoControladorAnadirTareaSprint getInstancia(){
		if(instancia == null){
			instancia = new EstadoControladorAnadirTareaSprint(); 
		}
		return instancia; 
	}
	
	@Override
	public void actualizarEstado(Controlador c) {
		c.cambiarEstado(factoryEstadoControlador.getEstado(OpcionesMenu.SPRINT_BACKLOG));
	}

	@Override
	public void mostrarMenu(Controlador c) {
		c.getVistaKanban().menuAnadirTareaSprint(c.getModeloKanban().getProductBacklog());
		
	}
	

}
