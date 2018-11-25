package controller;

public class EstadoControladorAnadirMiembro extends EstadoControlador{
	
	private static EstadoControladorAnadirMiembro instancia = null; 
	
	private EstadoControladorAnadirMiembro(){
		
	}

	/**
	 * Devuelve la instancia unica.
	 * 
	 * @return devuelve la unica instancia.
	 */
	public static EstadoControladorAnadirMiembro getInstancia(){
		if(instancia == null){
			instancia = new EstadoControladorAnadirMiembro(); 
		}
		return instancia; 
	}

	@Override
	public void actualizarEstado(Controlador c) {
		c.cambiarEstado(factoryEstadoControlador.getEstado(OpcionesMenu.GESTION_MIEBROS));
	}

	@Override
	public void mostrarMenu(Controlador c) {
		c.getVistaKanban().menuAnadirMiembro();
	}

}
