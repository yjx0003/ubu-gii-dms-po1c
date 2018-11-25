package controller;

public enum OpcionesMenu {
	
	PRODUCT_BACKLOG("Product Backlog"),
	SPRINT_BACKLOG("Sprint Backlog"),
	GESTION_MIEBROS("Gesti�n de Miembros"),
	GUARDAR_Y_CERRAR("Guardar y cerrar"),
	ATRAS("Atras"),
	ACEPTAR("Aceptar"),
	CANCELAR("Cancelar"),
	ANADIR_TAREA("A�adir tarea"),
	MODIFICAR_TAREA("Modificar tarea"),
	MANTENER_MIEMBRO("Mantener miembro"),
	HISTORIA_DE_USUARIO("Historia de Usuario"),
	DEFECTO("Defecto"),
	ANADIR_TAREA_AL_SPRINT("A�adir tarea al Sprint"),
	MOVER_TAREA("Mover tarea"),
	CREAR_NUEVO_SPRINT("Crear nuevo Sprint"),
	ANADIR_MIEMBRO("A�adir Miembro"),
	TAREA("TAREA");

	
	private String nombre;
	OpcionesMenu(String nombre){
		this.nombre=nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
}
