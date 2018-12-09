package view;

import java.util.EnumMap;
import java.util.Map;
/**
 * Patron factory donde se guardan las opciones de menu a un estado de controlador concreto
 * @author Alicia Olivares Gil
 * @author Yi Peng Ji
 *
 */
public class FactoryEstadoControlador {

	private Map<OpcionesMenu, EstadoVista> estados;
	private static FactoryEstadoControlador instancia = null;

	private FactoryEstadoControlador() {
		estados = new EnumMap<OpcionesMenu, EstadoVista>(OpcionesMenu.class);
		estados.put(OpcionesMenu.ANADIR_MIEMBRO, EstadoVistaAnadirMiembro.getInstancia());
		estados.put(OpcionesMenu.ANADIR_TAREA, EstadoVistaAnadirTarea.getInstancia());
		estados.put(OpcionesMenu.ANADIR_TAREA_AL_SPRINT, EstadoVistaAnadirTareaSprint.getInstancia());
		estados.put(OpcionesMenu.GUARDAR_Y_CERRAR, EstadoVistaCerrar.getInstancia());
		estados.put(OpcionesMenu.CREAR_NUEVO_SPRINT, EstadoVistaCrearNuevoSprint.getInstancia());
		estados.put(OpcionesMenu.GESTION_MIEBROS, EstadoVistaMiembros.getInstancia());
		estados.put(OpcionesMenu.MODIFICAR_TAREA, EstadoVistaModificarTarea.getInstancia());
		estados.put(OpcionesMenu.MOVER_TAREA, EstadoVistaMoverTarea.getInstancia());
		estados.put(OpcionesMenu.ATRAS, EstadoVistaPrincipal.getInstancia());
		estados.put(OpcionesMenu.PRODUCT_BACKLOG, EstadoVistaProductBacklog.getInstancia());
		estados.put(OpcionesMenu.SPRINT_BACKLOG, EstadoVistaSprintBacklog.getInstancia());
		estados.put(OpcionesMenu.TAREA, EstadoVistaTarea.getInstancia());
	}

	/**
	 * Devuelve la instancia unica.
	 * 
	 * @return devuelve la unica instancia.
	 */
	public static FactoryEstadoControlador getInstancia() {
		if (instancia == null) {
			instancia = new FactoryEstadoControlador();
		}
		return instancia;
	}

	/**
	 * Devuelve el estado deñ controlador asociado a OpcionesMenu.
	 * 
	 * @param opcion
	 *            estado asociado a la opcion de menu.
	 * @return estado asociado
	 */
	public EstadoVista getEstado(OpcionesMenu opcion) {
		return estados.get(opcion);
	}

	/**
	 * Devuelve el estado del segun el indice de la opcion de opcionesMenu
	 * 
	 * @param opcion
	 *            opcion
	 * @return estado asociado
	 */
	public EstadoVista getEstado(int opcion) {
		return estados.get(OpcionesMenu.values()[opcion]);
	}

}
