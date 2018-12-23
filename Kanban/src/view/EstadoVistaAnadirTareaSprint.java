package view;

import java.util.Map;
import model.ProductBacklog;
import model.Tarea;

public class EstadoVistaAnadirTareaSprint extends EstadoVista{

	private static EstadoVistaAnadirTareaSprint instancia = null; 
	
	private EstadoVistaAnadirTareaSprint(){
		
	}
	/**
	 * Devuelve la instancia unica.
	 * @return devuelve la unica instancia.
	 */
	public static EstadoVistaAnadirTareaSprint getInstancia(){
		if(instancia == null){
			instancia = new EstadoVistaAnadirTareaSprint(); 
		}
		return instancia; 
	}
	
	@Override
	public void actualizarEstado(InterfazUsuarioTexto ui) {
		ui.cambiarEstado(factoryEstadoControlador.getEstado(OpcionesMenu.SPRINT_BACKLOG));
	}

	@Override
	public void mostrarMenu(InterfazUsuarioTexto ui) {
		ProductBacklog productBacklog = ui.getControlador().getModeloKanban().getProductBacklog(); 
		System.out.println("*******AÑADIR TAREA A SPRINT********\n");
		for (Map.Entry<Integer, Tarea> par : productBacklog.getTareas().entrySet()) {
			System.out.println("[" + par.getKey() + "] " + par.getValue().getTitulo());
		}

		opcionUsuario = opcionMenu("Escoja la tarea que se desea añadir o 0 para salir: ", productBacklog.getTareas(),
				OpcionesMenu.ATRAS);

		if (opcionUsuario != OpcionesMenu.ATRAS.ordinal()) {
			ui.getControlador().anadirTareaSprint(opcionUsuario);
		}
	}
	

}
