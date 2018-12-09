package view;

import java.util.Map;

import controller.Controlador;
import model.EstadoTarea;
import model.EstadoTareaCompletada;
import model.EstadoTareaEnProceso;
import model.EstadoTareaEnValidacion;
import model.SprintBacklog;
import model.Tarea;

public class EstadoVistaMoverTarea extends EstadoVista{
	
	private static EstadoVistaMoverTarea instancia = null; 
	
	private EstadoVistaMoverTarea(){
		
	}
	/**
	 * Devuelve la instancia unica.
	 * @return devuelve la unica instancia.
	 */
	public static EstadoVistaMoverTarea getInstancia(){
		if(instancia == null){
			instancia = new EstadoVistaMoverTarea(); 
		}
		return instancia; 
	}

	@Override
	public void actualizarEstado(InterfazUsuarioTexto ui) {
		ui.cambiarEstado(factoryEstadoControlador.getEstado(OpcionesMenu.SPRINT_BACKLOG));
	}

	@Override
	public void mostrarMenu(InterfazUsuarioTexto ui) {
		SprintBacklog sprintBacklog = ui.getControlador().getModeloKanban().getSprintBacklog(); 
		System.out.println("*******MOVER TAREA********\n");
		for (Map.Entry<Integer, Tarea> par : sprintBacklog.getTareas().entrySet()) {
			System.out.println(
					"[" + par.getKey() + "] " + par.getValue().getTitulo() + "Estado: " + par.getValue().getEstado());
		}

		opcionUsuario = opcionMenu("Escoja la tarea que se desea mover o 0 para salir: ", sprintBacklog.getTareas(),
				OpcionesMenu.ATRAS);

		if (opcionUsuario != OpcionesMenu.ATRAS.ordinal()) {
			if (ui.getControlador().getModeloKanban().getSprintBacklog().getTareas().get(opcionUsuario)
					.getEstado() instanceof EstadoTareaEnValidacion) {
				EstadoTareaEnValidacion.getInstancia().setSiguienteEstado(pedirSiguienteEstado());
			}
			ui.getControlador().moverTarea(opcionUsuario);

		}	
	}
	
	/**
	 * Menu de pedir siguiente estado
	 * @return nuevo estado
	 */
	public EstadoTarea pedirSiguienteEstado() {
		System.out.println("¿Es necesario que la tarea vuelva al estado \"En Proceso\"?");
		int respuesta = opcionMenu("Escoja una opción: ", OpcionesMenu.SI, OpcionesMenu.NO);
		if (respuesta == OpcionesMenu.SI.ordinal()) {
			return EstadoTareaEnProceso.getInstancia();
		} else {
			return EstadoTareaCompletada.getInstancia();
		}

	}

}
