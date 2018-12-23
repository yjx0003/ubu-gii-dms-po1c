package view;

import java.util.Map;
import model.SprintBacklog;
import model.Tarea;

public class EstadoVistaSprintBacklog extends EstadoVista {

	private static EstadoVistaSprintBacklog instancia = null;

	private EstadoVistaSprintBacklog() {

	}

	/**
	 * Devuelve la instancia unica.
	 * 
	 * @return devuelve la unica instancia.
	 */
	public static EstadoVistaSprintBacklog getInstancia() {
		if (instancia == null) {
			instancia = new EstadoVistaSprintBacklog();
		}
		return instancia;
	}

	@Override
	public void actualizarEstado(InterfazUsuarioTexto ui) {

		if (opcionUsuario < OpcionesMenu.values().length) {
			ui.cambiarEstado(factoryEstadoControlador.getEstado(opcionUsuario));
		} else {
			EstadoVistaTarea estado = (EstadoVistaTarea) factoryEstadoControlador
					.getEstado(OpcionesMenu.TAREA);
			Tarea t = ui.getControlador().getModeloKanban().getSprintBacklog().getTareas().get(opcionUsuario);
			estado.setTarea(t);
			ui.cambiarEstado(estado);

		}

	}

	@Override
	public void mostrarMenu(InterfazUsuarioTexto ui) {
		SprintBacklog sprintBacklog = ui.getControlador().getModeloKanban().getSprintBacklog(); 
		EstadoVistaModificarTarea.getInstancia().setBacklog(sprintBacklog);

		System.out.println("*******SPRINT BACKLOG********\n");
		System.out.println(sprintBacklog.getDescripcion());
		for (Map.Entry<Integer, Tarea> par : sprintBacklog.getTareas().entrySet()) {
			System.out.println(
					"[" + par.getKey() + "] " + par.getValue().getTitulo() + " Estado: " + par.getValue().getEstado());
		}

		opcionUsuario = opcionMenu("Escoja una opción o el ID de la tarea que quiere visualizar: ",
				sprintBacklog.getTareas(), OpcionesMenu.ANADIR_TAREA_AL_SPRINT, OpcionesMenu.MOVER_TAREA,
				OpcionesMenu.CREAR_NUEVO_SPRINT, OpcionesMenu.ATRAS);
	}

}
