package controller;

public class EstadoControladorMoverTarea extends EstadoControlador{
	
	private static EstadoControladorMoverTarea instancia = null; 
	
	private EstadoControladorMoverTarea(){
		
	}
	/**
	 * Devuelve la instancia unica.
	 * @return devuelve la unica instancia.
	 */
	public static EstadoControladorMoverTarea getInstancia(){
		if(instancia == null){
			instancia = new EstadoControladorMoverTarea(); 
		}
		return instancia; 
	}

	@Override
	public void actualizarEstado(Controlador c) {
		c.cambiarEstado(factoryEstadoControlador.getEstado(OpcionesMenu.SPRINT_BACKLOG));
	}

	@Override
	public void mostrarMenu(Controlador c) {
		c.getVistaKanban().menuMoverTarea(c.getModeloKanban().getProductBacklog(),c.getModeloKanban().getSprintBacklog());	
	}

}
