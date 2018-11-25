package controller;
/**
 * Enumeracion de las posibles opciones de menu
 * @author Alicia Olivares Gil
 * @author Yi Peng Ji
 *
 */
public enum OpcionesMenu {
	
	PRODUCT_BACKLOG("Product Backlog"),
	SPRINT_BACKLOG("Sprint Backlog"),
	GESTION_MIEBROS("Gestión de Miembros"),
	GUARDAR_Y_CERRAR("Guardar y cerrar"),
	ATRAS("Atras"),
	ACEPTAR("Aceptar"),
	CANCELAR("Cancelar"),
	ANADIR_TAREA("Añadir tarea"),
	MODIFICAR_TAREA("Modificar tarea"),
	MANTENER_MIEMBRO("Mantener miembro"),
	HISTORIA_DE_USUARIO("Historia de Usuario"),
	DEFECTO("Defecto"),
	ANADIR_TAREA_AL_SPRINT("Añadir tarea al Sprint"),
	MOVER_TAREA("Mover tarea"),
	CREAR_NUEVO_SPRINT("Crear nuevo Sprint"),
	ANADIR_MIEMBRO("Añadir Miembro"),
	TAREA("TAREA"), 
	SI("Si"), 
	NO("No"); 

	
	private String nombre;
	OpcionesMenu(String nombre){
		this.nombre=nombre;
	}
	/**
	 * Nombre de la opcion de menu
	 * @return nombre
	 */
	public String getNombre() {
		return this.nombre;
	}
}
