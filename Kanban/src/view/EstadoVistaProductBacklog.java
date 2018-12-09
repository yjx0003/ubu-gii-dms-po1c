package view;

import java.util.Map;

import controller.Controlador;
import model.ProductBacklog;
import model.Tarea;

public class EstadoVistaProductBacklog extends EstadoVista {

	private static EstadoVistaProductBacklog instancia = null;

	private EstadoVistaProductBacklog() {

	}
	/**
	 * Devuelve la instancia unica.
	 * @return devuelve la unica instancia.
	 */
	public static EstadoVistaProductBacklog getInstancia() {
		if (instancia == null) {
			instancia = new EstadoVistaProductBacklog();
		}
		return instancia;
	}

	@Override
	public void actualizarEstado(InterfazUsuarioTexto ui) { 
		if (opcionUsuario<OpcionesMenu.values().length) {
			ui.cambiarEstado(factoryEstadoControlador.getEstado(opcionUsuario));
		}else {
			EstadoVistaTarea estado= (EstadoVistaTarea)factoryEstadoControlador.getEstado(OpcionesMenu.TAREA);
			Tarea t=ui.getControlador().getModeloKanban().getProductBacklog().getTareas().get(opcionUsuario);
			estado.setTarea(t);
			ui.cambiarEstado(estado);
		
			
		}
	}

	@Override
	public void mostrarMenu(InterfazUsuarioTexto ui) {
		
		ProductBacklog productBacklog = ui.getControlador().getModeloKanban().getProductBacklog(); 
		System.out.println("*******PRODUCT BACKLOG********\n");
		EstadoVistaModificarTarea.getInstancia().setBacklog(productBacklog);
		
		System.out.println("Tareas: ");
		for (Map.Entry<Integer, Tarea> par : productBacklog.getTareas().entrySet()) {
			System.out.println("[" + par.getKey() + "] " + par.getValue().getTitulo());
		}

		this.opcionUsuario = opcionMenu("Elige una opción", productBacklog.getTareas(), OpcionesMenu.ATRAS,
				OpcionesMenu.ANADIR_TAREA);

	}

}
