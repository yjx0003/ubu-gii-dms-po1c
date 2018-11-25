package controller;

import java.util.EnumMap;
import java.util.Map;
/**
 * Patron factory donde se guardan las opciones de menu a un estado de controlador concreto
 * @author Alicia Olivares Gil
 * @author Yi Peng Ji
 *
 */
public class FactoryEstadoControlador {

	private Map<OpcionesMenu, EstadoControlador> estados;
	private static FactoryEstadoControlador instancia = null;

	private FactoryEstadoControlador() {
		estados = new EnumMap<OpcionesMenu, EstadoControlador>(OpcionesMenu.class);
		estados.put(OpcionesMenu.ANADIR_MIEMBRO, EstadoControladorAnadirMiembro.getInstancia());
		estados.put(OpcionesMenu.ANADIR_TAREA, EstadoControladorAnadirTarea.getInstancia());
		estados.put(OpcionesMenu.ANADIR_TAREA_AL_SPRINT, EstadoControladorAnadirTareaSprint.getInstancia());
		estados.put(OpcionesMenu.GUARDAR_Y_CERRAR, EstadoControladorCerrar.getInstancia());
		estados.put(OpcionesMenu.CREAR_NUEVO_SPRINT, EstadoControladorCrearNuevoSprint.getInstancia());
		estados.put(OpcionesMenu.GESTION_MIEBROS, EstadoControladorMiembros.getInstancia());
		estados.put(OpcionesMenu.MODIFICAR_TAREA, EstadoControladorModificarTarea.getInstancia());
		estados.put(OpcionesMenu.MOVER_TAREA, EstadoControladorMoverTarea.getInstancia());
		estados.put(OpcionesMenu.ATRAS, EstadoControladorPrincipal.getInstancia());
		estados.put(OpcionesMenu.PRODUCT_BACKLOG, EstadoControladorProductBacklog.getInstancia());
		estados.put(OpcionesMenu.SPRINT_BACKLOG, EstadoControladorSprintBacklog.getInstancia());
		estados.put(OpcionesMenu.TAREA, EstadoControladorTarea.getInstancia());
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
	public EstadoControlador getEstado(OpcionesMenu opcion) {
		return estados.get(opcion);
	}

	/**
	 * Devuelve el estado del segun el indice de la opcion de opcionesMenu
	 * 
	 * @param opcion
	 *            opcion
	 * @return estado asociado
	 */
	public EstadoControlador getEstado(int opcion) {
		return estados.get(OpcionesMenu.values()[opcion]);
	}

}
