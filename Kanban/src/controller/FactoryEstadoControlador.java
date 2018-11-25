package controller;

import java.util.EnumMap;
import java.util.Map;



public class FactoryEstadoControlador {

	private Map<OpcionesMenu,EstadoControlador> estados;
	private static FactoryEstadoControlador instancia=null;
	
	private FactoryEstadoControlador(){
		estados=new EnumMap<OpcionesMenu,EstadoControlador>(OpcionesMenu.class);
		estados.put(OpcionesMenu.ANADIR_MIEMBRO, EstadoControladorAnadirMiembro.getInstancia());
		estados.put(OpcionesMenu.ANADIR_TAREA, EstadoControladorAnadirTarea.getInstancia());
		estados.put(OpcionesMenu.ANADIR_TAREA_AL_SPRINT, EstadoControladorAnadirTareaSprint.getInstancia());
		estados.put(OpcionesMenu.GUARDAR_Y_CERRAR, EstadoControladorCerrar.getInstancia());
		estados.put(OpcionesMenu.CREAR_NUEVO_SPRINT, EstadoControladorCrearNuevoSprint.getInstancia());
		estados.put(OpcionesMenu.GESTION_MIEBROS, EstadoControladorMiembros.getInstancia());
		estados.put(OpcionesMenu.MODIFICAR_TAREA,EstadoControladorModificarTarea.getInstancia());
		estados.put(OpcionesMenu.MOVER_TAREA, EstadoControladorModificarTarea.getInstancia());
		estados.put(OpcionesMenu.ATRAS, EstadoControladorPrincipal.getInstancia());
		estados.put(OpcionesMenu.PRODUCT_BACKLOG, EstadoControladorProductBacklog.getInstancia());
		estados.put(OpcionesMenu.SPRINT_BACKLOG,EstadoControladorSprintBacklog.getInstancia());
		estados.put(OpcionesMenu.TAREA, EstadoControladorTarea.getInstancia());
	}
	
	
	public static FactoryEstadoControlador getInstancia() {
		if (instancia==null) {
			instancia=new FactoryEstadoControlador();
		}
		return instancia;
	}
	
	public EstadoControlador getEstado(OpcionesMenu opcion) {
		return estados.get(opcion);
	}
	
	public EstadoControlador getEstado(int opcion) {
		return estados.get(OpcionesMenu.values()[opcion]);
	}
	
}
