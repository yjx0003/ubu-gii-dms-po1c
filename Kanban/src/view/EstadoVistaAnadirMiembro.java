package view;


public class EstadoVistaAnadirMiembro extends EstadoVista{
	
	private static EstadoVistaAnadirMiembro instancia = null; 
	
	private EstadoVistaAnadirMiembro(){
		
	}

	/**
	 * Devuelve la instancia unica.
	 * 
	 * @return devuelve la unica instancia.
	 */
	public static EstadoVistaAnadirMiembro getInstancia(){
		if(instancia == null){
			instancia = new EstadoVistaAnadirMiembro(); 
		}
		return instancia; 
	}

	@Override
	public void actualizarEstado(InterfazUsuarioTexto ui) {
		ui.cambiarEstado(factoryEstadoControlador.getEstado(OpcionesMenu.GESTION_MIEBROS));
	}

	@Override
	public void mostrarMenu(InterfazUsuarioTexto ui) {
		System.out.println("*******CREAR NUEVO MIEMBRO*******\n");

		System.out.println("Nombre: ");
		String nombre = sc.nextLine();

		System.out.println("¿Desea crear un miembro con el nobmre " + nombre + "?");

		opcionUsuario = opcionMenu("Escoja una opción: ", OpcionesMenu.ACEPTAR, OpcionesMenu.CANCELAR);

		if (opcionUsuario == OpcionesMenu.ACEPTAR.ordinal()) {
			ui.getControlador().anadirNuevoMiembro(nombre);
		}
	}

}
