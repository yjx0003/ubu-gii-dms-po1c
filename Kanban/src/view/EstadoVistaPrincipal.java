package view;

import controller.Controlador;

public class EstadoVistaPrincipal extends EstadoVista{
	
	private static EstadoVistaPrincipal instancia = null; 
	
	private EstadoVistaPrincipal(){
		
	}
	/**
	 * Devuelve la instancia unica.
	 * @return devuelve la unica instancia.
	 */
	public static EstadoVistaPrincipal getInstancia(){
		if(instancia == null){
			instancia = new EstadoVistaPrincipal(); 
		}
		return instancia; 
	}

	@Override
	public void actualizarEstado(InterfazUsuarioTexto ui) { 
		ui.cambiarEstado(factoryEstadoControlador.getEstado(opcionUsuario));
	}

	@Override
	public void mostrarMenu(InterfazUsuarioTexto ui) {
		System.out.println("********MENÚ SCRUM********\n");

		this.opcionUsuario = opcionMenu("Eliga una opción: ", OpcionesMenu.PRODUCT_BACKLOG, OpcionesMenu.SPRINT_BACKLOG,
				OpcionesMenu.GESTION_MIEBROS, OpcionesMenu.GUARDAR_Y_CERRAR);
		
	}	

}
