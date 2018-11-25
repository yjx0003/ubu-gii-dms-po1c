package controller;

import model.Tarea;

public class EstadoControladorProductBacklog extends EstadoControlador {

	private static EstadoControladorProductBacklog instancia = null;

	private EstadoControladorProductBacklog() {

	}
	/**
	 * Devuelve la instancia unica.
	 * @return devuelve la unica instancia.
	 */
	public static EstadoControladorProductBacklog getInstancia() {
		if (instancia == null) {
			instancia = new EstadoControladorProductBacklog();
		}
		return instancia;
	}

	@Override
	public void actualizarEstado(Controlador c) {
		int opcionUsuario = c.getVistaKanban().getOpcionUsuario(); 
		
		if (opcionUsuario<OpcionesMenu.values().length) {
			c.cambiarEstado(factoryEstadoControlador.getEstado(opcionUsuario));
		}else {
			EstadoControladorTarea estado= (EstadoControladorTarea)factoryEstadoControlador.getEstado(OpcionesMenu.TAREA);
			Tarea t=c.getModeloKanban().getProductBacklog().getTareas().get(opcionUsuario);
			estado.setTarea(t);
			c.cambiarEstado(estado);
		
			
		}
	}

	@Override
	public void mostrarMenu(Controlador c) {
		c.getVistaKanban().menuProductBacklog(c.getModeloKanban().getProductBacklog());

	}

}
