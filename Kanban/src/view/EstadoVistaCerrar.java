package view;

import controller.Controlador;

public class EstadoVistaCerrar extends EstadoVista {

	private static EstadoVistaCerrar instancia=null;
	
	private EstadoVistaCerrar() {
		
	}
	/**
	 * Devuelve la instancia unica.
	 * @return devuelve la unica instancia.
	 */
	public static EstadoVistaCerrar getInstancia() {
		if(instancia==null) {
			instancia=new EstadoVistaCerrar();
		}
		return instancia;
	}
	@Override
	public void actualizarEstado(InterfazUsuarioTexto ui) {
		//NO hay mas estados

	}

	@Override
	public void mostrarMenu(InterfazUsuarioTexto ui) {
		System.out.println("cerrando...");
		ui.getControlador().close();

	}



}
