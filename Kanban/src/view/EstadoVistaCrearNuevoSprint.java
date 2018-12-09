package view;

import controller.Controlador;

public class EstadoVistaCrearNuevoSprint extends EstadoVista{
	
	private static EstadoVistaCrearNuevoSprint instancia = null; 
	
	private EstadoVistaCrearNuevoSprint(){
		
	}
	/**
	 * Devuelve la instancia unica.
	 * @return devuelve la unica instancia.
	 */
	public static EstadoVistaCrearNuevoSprint getInstancia(){
		if(instancia == null){
			instancia = new EstadoVistaCrearNuevoSprint(); 
		}
		return instancia; 
	}

	@Override
	public void actualizarEstado(InterfazUsuarioTexto ui) {
		ui.cambiarEstado(factoryEstadoControlador.getEstado(OpcionesMenu.SPRINT_BACKLOG));
	}

	@Override
	public void mostrarMenu(InterfazUsuarioTexto ui) {
		System.out.println("*******CREAR NUEVO SPRINT********\n");

		System.out.println("Día del mes: ");
		int dia = sc.nextInt();
		System.out.println("Mes: ");
		int mes = sc.nextInt();
		System.out.println("Año: ");
		int ano = sc.nextInt();
		sc.nextLine();
		System.out.println("Descripcion: ");
		String descripcion = sc.nextLine();

		System.out.println("¿Desea crear el nuevo sprint? (no se tendrá acceso al sprint anterior)");

		opcionUsuario = opcionMenu("Elige una opcion: ", OpcionesMenu.ACEPTAR, OpcionesMenu.CANCELAR);

		if (opcionUsuario == OpcionesMenu.ACEPTAR.ordinal()) {
			ui.getControlador().anadirNuevoSprint(dia, mes, ano, descripcion);
		}
		
	}

}
