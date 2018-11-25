package controller;


public class EstadoControladorAnadirTarea extends EstadoControlador{
	
	
	private static EstadoControladorAnadirTarea instancia = null; 
	
	private EstadoControladorAnadirTarea(){
	}
	/**
	 * Devuelve la instancia unica.
	 * @return devuelve la unica instancia.
	 */
	public static EstadoControladorAnadirTarea getInstancia(){
		if(instancia == null){
			instancia = new EstadoControladorAnadirTarea(); 
		}
		return instancia; 
	}

	@Override
	public void actualizarEstado(Controlador c) {
		
		c.cambiarEstado(factoryEstadoControlador.getEstado(OpcionesMenu.PRODUCT_BACKLOG));
	}

	@Override
	public void mostrarMenu(Controlador c) {
		c.getVistaKanban().menuAnadirTarea(c.getModeloKanban().getMiembros());
		
	}



}
