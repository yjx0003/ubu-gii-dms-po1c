package controller;

public class EstadoControladorPrincipal extends EstadoControlador{
	
	private static EstadoControladorPrincipal instancia = null; 
	
	private EstadoControladorPrincipal(){
		
	}
	/**
	 * Devuelve la instancia unica.
	 * @return devuelve la unica instancia.
	 */
	public static EstadoControladorPrincipal getInstancia(){
		if(instancia == null){
			instancia = new EstadoControladorPrincipal(); 
		}
		return instancia; 
	}

	@Override
	public void actualizarEstado(Controlador c) {
		int opcionUsuario = c.getVistaKanban().getOpcionUsuario(); 
		
		c.cambiarEstado(factoryEstadoControlador.getEstado(opcionUsuario));
	}

	@Override
	public void mostrarMenu(Controlador c) {
		c.getVistaKanban().menuPrincipal();
		
	}	

}
